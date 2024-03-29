package com.example.apprecycleviewhorizontal;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static android.os.Environment.getExternalStorageDirectory;
import static android.support.constraint.Constraints.TAG;


public class ProfileFragment extends Fragment {

    public static final int GALLERY_REQUEST_CODE = 100;
    private static final String PACKAGE_NAME = "com.example.apprecycleviewhorizontal";
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1000;
    String imageName;
    String picturePath;
    Bitmap bitmap;
    static final int REQUEST_TAKE_PHOTO = 1;
    private static final String TAG = "lokasi photo: ";
    ImageView imageView;
    ImageButton imageButton;
    String currentPhotoPath;
    private static final int Image_Capture_Code = 1;
    CircleImageView civ;
    FloatingActionButton fab;

    public ProfileFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        civ = view.findViewById(R.id.image);
        fab = view.findViewById(R.id.btnpicture);


        File file = new File(getExternalStorageDirectory() +
                "/Android/data/"+PACKAGE_NAME+"/files/Pictures" + "/profile.jpg");
        if (file.exists()) {
            //Do something
            currentPhotoPath = file.getAbsolutePath();
            setPic();
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dispatchTakePictureIntent();
                showPictureDialog();
            }
        });
        return view;
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String imageFileName = "profile";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = new File(storageDir, imageFileName + ".jpg");

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                ex.getMessage();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getActivity(),
                        PACKAGE_NAME+".fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private void setPic() {

        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath);
        //Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        Log.i(TAG, "setPic: " + currentPhotoPath + "\n");
        Log.i(TAG, "setPic: " + getExternalStorageDirectory() +
                "/Android/data/"+PACKAGE_NAME+"/files/Pictures" + "/profile.jpg");

        civ.setImageBitmap(bitmap);
    }

    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            setPic();
        }
    }*/

    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(getContext());
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                pickFromGallery();
                                break;
                            case 1:
                                dispatchTakePictureIntent();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    private void pickFromGallery() {
        //Create an Intent with action as ACTION_PICK
        Intent intent = new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        // Launching the Intent
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Result code is RESULT_OK only if the user selects an Image
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case GALLERY_REQUEST_CODE:
                    //data.getData returns the content URI for the selected Image
                    Uri selectedImage = data.getData();
                    Log.i("URI", "URInya apa:" + selectedImage);
                    picturePath = getRealPathFromURI(selectedImage, (Activity) getContext());
                    //imageView.setImageURI(selectedImage);
                    Log.i(TAG, "setPic00: " + picturePath + "\n");
                    setPicGallery();
                    break;

                case REQUEST_TAKE_PHOTO:
                    setPicCamera();
                    break;
            }
    }

    public String getRealPathFromURI(Uri contentURI, Activity context) {
        String[] projection = {MediaStore.Images.Media.DATA};
        @SuppressWarnings("deprecation")
        Cursor cursor = context.managedQuery(contentURI, projection, null,
                null, null);
        if (cursor == null)
            return null;
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        if (cursor.moveToFirst()) {
            String s = cursor.getString(column_index);
            // cursor.close();
            return s;
        }
        // cursor.close();
        return null;
    }

    private void setPicCamera() {

        bitmap = BitmapFactory.decodeFile(currentPhotoPath);
        //Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        Log.i(TAG, "setPic: " + currentPhotoPath + "\n");
        Log.i(TAG, "setPic: " + getExternalStorageDirectory() +
                "/Android/data/" + PACKAGE_NAME + "/files/Pictures" + "/profile.jpg");

        civ.setImageBitmap(bitmap);

    }

    private void setPicGallery() {
        if (!TextUtils.isEmpty(picturePath)) {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                //Toast.makeText(this, "Tidak ada ijin", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "setPic0: " + picturePath + "\n");
            }

            File f = new File(picturePath);
            imageName = f.getName();
            Log.i(TAG, "setPic15: " + picturePath + "\n");
            if (f.exists()) {
                bitmap = BitmapFactory.decodeFile(picturePath);
            } else {
                Log.i(TAG, "setPic22: File tidak ada");
            }

            // setFileName("fromGallery");
            Log.i(TAG, "setPic1: " + picturePath + "\n");
            // Log.i(TAG, "setPic2: " + getExternalStorageDirectory() + "/DCIM/Camera/" + imageName);
            Log.i(TAG, "setPic3: " + imageName);


        }

        civ.setImageBitmap(bitmap);

        //copy file picturepath ke File file = new File(getExternalStorageDirectory() +
        //                "/Android/data/com.jodemy.ambilphotodarikamera/files/Pictures" + "/profile.jpg");
        File file = new File(getExternalStorageDirectory() +
                "/Android/data/" + PACKAGE_NAME + "/files/Pictures" + "/profile.jpg");
        try {
            copyPicture(new File(picturePath), file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(getContext(), picturePath, Toast.LENGTH_LONG).show();

    }

    private void copyPicture(File sourceFile, File destFile) throws IOException {
        if (!sourceFile.exists()) {
            return;
        }

        FileChannel source = null;
        FileChannel destination = null;
        source = new FileInputStream(sourceFile).getChannel();
        destination = new FileOutputStream(destFile).getChannel();
        if (destination != null && source != null) {
            destination.transferFrom(source, 0, source.size());
        }
        if (source != null) {
            source.close();
        }
        if (destination != null) {
            destination.close();
        }
    }
}