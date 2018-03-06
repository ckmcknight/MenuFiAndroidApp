package com.ckmcknight.android.menufi.controller;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.ckmcknight.android.menufi.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_menu) NavigationView mNav;
    ActionBarDrawerToggle mActionBarDrawerToggle;
    private Switch allergySwitch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        allergySwitch = findViewById(R.id.allergy_filter);

        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.in, R.string.out);

        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return selectFragment(item);
            }
        });

        selectFragment(mNav.getMenu().getItem(0));
    }

    /** Swaps fragments in the main content view */
    private boolean selectFragment(MenuItem menuItem) {
        // Create a new fragment and specify the planet to show based on position
        Fragment fragment;
        switch(menuItem.getItemId()) {
            case (R.id.nav_NearbyMenus):
                fragment = new NearbyMenuFragment();
                break;
            case (R.id.nav_profile):
                fragment = new ProfileFragment();
                break;
            default:
                fragment = new Fragment();
                break;
        }
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentLocLayout, fragment)
                .addToBackStack(null)
                .commit();
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        mDrawerLayout.closeDrawers();

        Intent aIntent = new Intent(this, MenuItemFragment.class);
        aIntent.putExtra("Allergy", allergySwitch.isChecked());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mActionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}
