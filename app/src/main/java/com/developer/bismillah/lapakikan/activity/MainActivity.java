package com.developer.bismillah.lapakikan.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.developer.bismillah.lapakikan.R;
import com.developer.bismillah.lapakikan.fragment.BerandaFragment;
import com.developer.bismillah.lapakikan.fragment.LapakkuFragment;
import com.developer.bismillah.lapakikan.fragment.PesanFragment;
import com.developer.bismillah.lapakikan.fragment.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    private Toolbar toolbar;

    // Index to identify current nav menu item
    public static int navItemIndex = 0;

    // Tags used to attach the fragments
    private static final String TAG_BERANDA = "beranda";
    private static final String TAG_PROFILE = "profil";
    private static final String TAG_LAPAKKU = "lapakku";
    private static final String TAG_PESAN = "pesan";
    public static String CURRENT_TAG = TAG_BERANDA;

    // Toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    // Flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mHandler = new Handler();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        // Navigation view header
        navHeader = navigationView.getHeaderView(0);

        // Load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        // Initializing navigation menu
        setUpNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_BERANDA;
            loadHomeFragment();
        }
    }

    public void setCurrentTag(int navItemIndexHome, String CURRENT_TAG_BERANDA) {
        navItemIndex = navItemIndexHome;
        CURRENT_TAG = CURRENT_TAG_BERANDA;
        loadHomeFragment();
    }

    private void setUpNavigationView() {
        // Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                // Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    // Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_beranda:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_BERANDA;
                        break;
                    case R.id.nav_profile:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_PROFILE;
                        break;
                    case R.id.nav_lapakku:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_LAPAKKU;
                        break;
                    case R.id.nav_pesan:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_PESAN;
                        break;
                    case R.id.nav_settings:
                        // Launch new intent instead of loading fragment
                        // startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                        // drawer.closeDrawers();
                        return true;
                    case R.id.nav_about:
                        // Launch new intent instead of loading fragment
                        // startActivity(new Intent(MainActivity.this, AboutActivity.class));
                        // drawer.closeDrawers();
                        return true;
                    case R.id.nav_logout:
                        // Launch function to account logout
                        return true;
                    default:
                        navItemIndex = 0;
                }

                // Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        // Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);

        // Calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    private void loadHomeFragment() {
        // Selecting appropriate nav menu item
        selectNavMenu();

        // Set toolbar title
        setToolbarTitle();

        // If user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();

            return;
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        // Closing drawer on item click
        drawer.closeDrawers();

        // Refresh toolbar menu
        invalidateOptionsMenu();
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                // Beranda fragment
                BerandaFragment berandaFragment = new BerandaFragment();
                return berandaFragment;
            case 1:
                // Profile fragment
                ProfileFragment profileFragment = new ProfileFragment();
                return profileFragment;
            case 2:
                // Lapakku fragment
                LapakkuFragment lapakkuFragment = new LapakkuFragment();
                return lapakkuFragment;
            case 3:
                // Pesan fragment
                PesanFragment pesanFragment = new PesanFragment();
                return pesanFragment;
            default:
                return new BerandaFragment();

        }
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        if (shouldLoadHomeFragOnBackPress) {
            // checking if user is on other navigation menu
            // rather than home
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_BERANDA;
                loadHomeFragment();
                return;
            }
        }

        super.onBackPressed();
    }
}
