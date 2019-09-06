package com.example.apprecycleviewhorizontal;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static android.os.Environment.getExternalStorageDirectory;
import static android.support.constraint.Constraints.TAG;


public class ProfileFragment extends Fragment {

    public static final int GALLERY_REQUEST_CODE = 100;
    private static final String PACKAGE_NAME = "com.jodemy.changeprofilefromgalleryorcamera";
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
                "/Android/data/com.example.apprecycleviewhorizontal/files/Pictures" + "/profile.jpg");
        if (file.exists()) {
            //Do something
            currentPhotoPath = file.getAbsolutePath();
            setPic();
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
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
                        "com.example.apprecycleviewhorizontal.fileprovider",
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
                "/Android/data/com.example.apprecycleviewhorizontal/files/Pictures" + "/profile.jpg");

        civ.setImageBitmap(bitmap);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            setPic();
        }
    }
}