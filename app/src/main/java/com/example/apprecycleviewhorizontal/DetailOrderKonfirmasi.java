package com.example.apprecycleviewhorizontal;

import android.content.Intent;
import android.net.MailTo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DetailOrderKonfirmasi extends AppCompatActivity {
    int jumlah = 1;
    int harga = 100000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_order_konfirmasi);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Konfirmasi Pemesanan");
    }

    public void tampilkanJumlah(int jumlah) {
        TextView tvJumlah = findViewById(R.id.tvJumlah);
        tvJumlah.setText("" + jumlah);
    }

    public void increment(View view) {
        if(jumlah == 10) {
            Toast.makeText(this,"Pesanan Maksimum Adalah 10", Toast.LENGTH_SHORT).show();
            return;
        }
        jumlah = jumlah + 1;
        tampilkanJumlah(jumlah);
    }

    public void decrement(View view) {
        if (jumlah == 1) {
            Toast.makeText(this,"Pesanan Minimum Adalah 1", Toast.LENGTH_SHORT).show();
            return;
        }
        jumlah = jumlah - 1;
        tampilkanJumlah(jumlah);
    }

    public void pesansekarang(View view) {
       /* Fragment2 dFragment = Fragment2.newInstance("Apakah Anda Sudah Yakin ?");
        dFragment.show(getFragmentManager(), "dialog");*/
        EditText mEditTextPemesanan = findViewById(R.id.editText);
        String namaPemesanan = mEditTextPemesanan.getText().toString();
        EditText mEditTextAlamat = findViewById(R.id.editText2);
        String namaAlamat = mEditTextAlamat.getText().toString();
        EditText mEditTextEmail = findViewById(R.id.editText3);
        String namaEmail = mEditTextEmail.getText().toString();
        EditText mEditTextNoHp = findViewById(R.id.editText4);
        String namaNoHp = mEditTextNoHp.getText().toString();


        String info = "Nama Pemesan : " + namaPemesanan + "\n\nAlamat : " + namaAlamat +
                "\n\nEmail : " + namaEmail + "\n\nNo HP : " + namaNoHp +
                "\n\nHarga yang harus Anda bayar untuk pembelian : " + jumlah + "Item";
        info = info + " \n\nadalah : Rp. " + (jumlah * harga);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"firehands987@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Konfirmasi Pemesanan");
        intent.putExtra(Intent.EXTRA_TEXT, info);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

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
