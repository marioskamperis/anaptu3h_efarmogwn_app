package com.example.protereotitapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, new HomeFragment()).commit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

//    // `onPostCreate` called when activity start-up is complete after `onStart()`
//    // NOTE! Make sure to override the method with only a single `Bundle` argument
//    @Override
//    protected void onPostCreate(Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_info) {
//            // Handle the camera action
//            Toast.makeText(getApplicationContext(), "Item Pressed", Toast.LENGTH_SHORT).show();
//        } else if (id == R.id.nav_live) {
//
//            Toast.makeText(getApplicationContext(), "Item Pressed", Toast.LENGTH_SHORT).show();
//        } else if (id == R.id.nav_manage) {
//
//            Toast.makeText(getApplicationContext(), "Item Pressed", Toast.LENGTH_SHORT).show();
//        } else if (id == R.id.nav_myTickets) {
//
//            Toast.makeText(getApplicationContext(), "Item Pressed", Toast.LENGTH_SHORT).show();
//        } else if (id == R.id.nav_ticket) {
//
//            Toast.makeText(getApplicationContext(), "Item Pressed", Toast.LENGTH_SHORT).show();
//        } else if (id == R.id.nav_view) {
//            Toast.makeText(getApplicationContext(), "Item Pressed", Toast.LENGTH_SHORT).show();
//        }
//
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
        selectDrawerItem(item);
        return true;
    }

    public void selectDrawerItem(MenuItem menuItem) {
//        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass = null;
        boolean loggout = false;
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                fragmentClass = HomeFragment.class;
                break;

            case R.id.nav_ticket:
                fragmentClass = BookFragment.class;
                break;

            case R.id.nav_myTicket:
                //TODO this is just an example
                fragmentClass = CurrentTicketFragment.class;
                break;

            case R.id.nav_loggout:
                loggout = true;
                break;

            case R.id.nav_manage:
                fragmentClass = HomeFragment.class;
                break;


            case R.id.nav_view:
                fragmentClass = HomeFragment.class;
                break;

            default:
                fragmentClass = HomeFragment.class;
        }
        if (loggout) {
            SessionManager session = new SessionManager(getApplicationContext());
            session.setLogin(false);
            Intent intent = new Intent(this,
                    LoginActivity.class);
            startActivity(intent);
            finish();

        } else {
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }

//         Insert the fragment by replacing any existing fragment


            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();


            // Highlight the selected item has been done by NavigationView
            menuItem.setChecked(true);
            // Set action bar title
            setTitle(menuItem.getTitle());


            // Close the navigation drawer
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        }

    }


    // ...
}
