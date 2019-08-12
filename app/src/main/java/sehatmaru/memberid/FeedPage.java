package sehatmaru.memberid;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import sehatmaru.memberid.adapters.ItemAdapter;
import sehatmaru.memberid.realms.ItemRealm;

public class FeedPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ItemAdapter.OnClickItemListener {

    Realm realm;

    RecyclerView recyclerView;

    ItemAdapter adapter;
    ScaleInAnimationAdapter scaleInAnimationAdapter;

    List<ItemRealm> listItem = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Realm.init(this);
        realm = Realm.getDefaultInstance();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        recyclerView = findViewById(R.id.rcList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        populateData();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.feed_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_filter) {
            Intent intent = new Intent(getApplicationContext(), FilterPage.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent intent = new Intent(getApplicationContext(), FeedPage.class);
            startActivity(intent);
        } else if (id == R.id.nav_card) {

        } else if (id == R.id.nav_profile) {

        } else if (id == R.id.nav_logout) {
            Intent intent = new Intent(getApplicationContext(), WelcomePage.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void OnClickItem(String id) {

    }

    private void populateData(){
        realm.executeTransactionAsync(realm1 -> listItem = realm1.copyFromRealm(realm1.where(ItemRealm.class).findAll()), () -> {
            if (!listItem.isEmpty()) {
                adapter = new ItemAdapter(this, listItem, this);
                scaleInAnimationAdapter = new ScaleInAnimationAdapter(adapter);
                recyclerView.setAdapter(scaleInAnimationAdapter);
            }
        });
    }
}
