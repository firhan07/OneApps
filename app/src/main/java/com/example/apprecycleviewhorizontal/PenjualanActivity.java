package com.example.apprecycleviewhorizontal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PenjualanActivity extends AppCompatActivity {
    String[] penjualan = {"Account", "Penjualan", "Pembelian", "Keuangan", "Saldo", "Transaksi", "Pengiriman", "Bantuan"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penjualan);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Penjualan");

        ListView listView = findViewById(android.R.id.list);
        listView.setAdapter(new ArrayAdapter<String>(PenjualanActivity.this,
                android.R.layout.simple_list_item_1, penjualan));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
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