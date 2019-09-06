package com.example.apprecycleviewhorizontal;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Objects;

import static java.lang.System.load;

public class DetailsActivity extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ImageView iv = findViewById(R.id.imageView);
        TextView textView = findViewById(R.id.tv_deskripsi_detail);
        Glide.with(this)
                .asBitmap()
                .load(getIntent().getStringExtra("image"))
                .into(iv);
        String deskripsiDetail = getIntent().getStringExtra("title");
        deskripsiDetail += "\n\n" + getIntent().getStringExtra("deskripsi");
        textView.setText(deskripsiDetail);


        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
