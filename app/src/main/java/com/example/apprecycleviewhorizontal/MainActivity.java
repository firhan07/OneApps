package com.example.apprecycleviewhorizontal;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        toolbar = getSupportActionBar();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                Intent intent = new Intent(MainActivity.this, AccountActivity.class);
                startActivity(intent);
            }
        });

        // load the store fragment by default
        toolbar.setTitle("OneApps");
        loadFragment(new HorizontalFragment());
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_store:
                    toolbar.setTitle("OneApps");
                    fragment = new HorizontalFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_types:
                    toolbar.setTitle("OneApps");
                    fragment = new TypesFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_search:
                    toolbar.setTitle("OneApps");
                    fragment = new StaggeredFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_chatting:
                    toolbar.setTitle("OneApps");
                    fragment = new ChattingFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_profile:
                    toolbar.setTitle("OneApps");
                    fragment = new ProfileFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    KaosDataModel kaosModel;
    ImageView iv1;
    TextView tv2;
    private RecyclerView recyclerView;
    private ArrayList<KaosDataModel> imageModelArrayList;
    private KaosAdapter adapter;
    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
            int position = viewHolder.getAdapterPosition();
            kaosModel = imageModelArrayList.get(position);
            iv1 = findViewById(R.id.iv1);
            iv1.setImageResource(kaosModel.getImage_drawable());
            tv2 = findViewById(R.id.tvHarga);
            tv2.setText(myPriceList[position]);
        }
    };

    private int[] myImageList = new int[]{R.drawable.banyuwangi, R.drawable.jember};
    private String[] myImageNameList = new String[]{"Yemen", "Riyadh", "Jakarta", "Banyuwangi", "Jember", "Malang"};
    private String[] myPriceList = new String[]{"100000", "150000", "80000", "95000", "90000", "99000"};


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu1, menu);

        // Get the search menu.
        MenuItem searchMenu = menu.findItem(R.id.app_bar_menu_search);

        // Get SearchView object.
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchMenu);

        // Get SearchView autocomplete object.
        final SearchView.SearchAutoComplete searchAutoComplete = (SearchView.SearchAutoComplete)searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchAutoComplete.setBackgroundColor(Color.WHITE);
        searchAutoComplete.setTextColor(Color.BLACK);
        searchAutoComplete.setDropDownBackgroundResource(android.R.color.background_light);

        // Create a new ArrayAdapter and add data to search auto complete object.
        String dataArr[] = {"Firhan","Ama Ida","Nazih","Shofa","Ami Yahya","Ama Efa","Maram","Ahmad","Aziz","Amani","Asraf","Mubarak",
                "Muhammad","Manal","Marwah","Musab","Maryam","Kader","Hamudi","Shobrin","Shobah","Najlah","Najmi","Umik Cik",
        };

        ArrayAdapter<String> newsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, dataArr);
        searchAutoComplete.setAdapter(newsAdapter);

        // Listen to search view item on click event.
        searchAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int itemIndex, long id) {
                String queryString=(String)adapterView.getItemAtPosition(itemIndex);
                searchAutoComplete.setText("" + queryString);
                Toast.makeText(MainActivity.this, "you clicked " + queryString, Toast.LENGTH_LONG).show();
            }
        });

        // Below event is triggered when submit search query.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setMessage("Search keyword is " + query);
                alertDialog.show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        MenuItem shareMenuItem = menu.findItem(R.id.share);
        // Because it's actionProviderClass is ShareActionProvider, so after below settings
        // when click this menu item A sharable applications list will popup.
        // User can choose one application to share.
        ShareActionProvider shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareMenuItem);
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/*");
        shareActionProvider.setShareIntent(shareIntent);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBodyText = "One Apps is on Going";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
                startActivity(Intent.createChooser(sharingIntent, "Share to . . ."));
                if (item.getItemId() == R.id.new_item) {
                    startActivity(new Intent(this, TermurahActivity.class));
                } else if (item.getItemId() == R.id.upload_item) {
                    startActivity(new Intent(this, TermahalActivity.class));
                } else if (item.getItemId() == R.id.copy_item) {
                    startActivity(new Intent(this, TrendingActivity.class));
                } else if (item.getItemId() == R.id.share_item) {
                    startActivity(new Intent(this, JualActivity.class));
                } else if (item.getItemId() == R.id.bookmark_item) {
                    startActivity(new Intent(this, BeliActivity.class));
                } else if (item.getItemId() == R.id.list_item) {
                    startActivity(new Intent(this, PengaturanActivity.class));
                }

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
