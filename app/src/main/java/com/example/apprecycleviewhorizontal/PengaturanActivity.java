package com.example.apprecycleviewhorizontal;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PengaturanActivity extends AppCompatActivity {

    String[] pengaturan = {"Account", "Penjualan", "Pembelian", "Keuangan", "Saldo", "Transaksi", "Pengiriman", "Bantuan"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Pengaturan");

        ListView listView = findViewById(android.R.id.list);
        listView.setAdapter(new ArrayAdapter<String>(PengaturanActivity.this,
                android.R.layout.simple_list_item_1, pengaturan));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PengaturanActivity.this, AccountActivity.class);
                startActivity(intent);
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
