  package com.example.apprecycleviewhorizontal;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class OrderDetail extends AppCompatActivity {
    String[] fruits={"Ahmad","Muhammad","Musab","Kader","Amaida"};
    int images[] = {R.drawable.ahmad, R.drawable.muhammad, R.drawable.musab, R.drawable.kader, R.drawable.amaida };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        Spinner spin = (Spinner) findViewById(R.id.spinner2);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(OrderDetail.this, "You Select Position: "+position+" "+fruits[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SpinnerAdapter customAdapter = new SpinnerAdapter(getApplicationContext(),images,fruits);
        spin.setAdapter(customAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                Intent intent = new Intent(OrderDetail.this, DetailOrderKonfirmasi.class);
                startActivity(intent);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail Order");

        TextView tvDetailKaos = findViewById(R.id.tvDetailKaos);
        tvDetailKaos.setText(getIntent().getStringExtra("tv"));
        ImageView gambarKaos = findViewById(R.id.ivKaos);
        String s = getIntent().getStringExtra("gambar");
     //   ImageView iv1 = findViewById(R.id.ivKaos);
        Glide.with(this)
                .asBitmap()
                .load(s)
                .into(gambarKaos);

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

