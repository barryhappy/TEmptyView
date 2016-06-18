package com.barryzhang.temptyviewdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.barryzhang.temptyview.TEmptyView;
import com.barryzhang.temptyview.TViewUtil;

import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    ViewPager viewPager;
    TabLayout tabLayout;
    TextView textViewDescription;

    List<BaseFragment> fragmentList = new ArrayList<>();
    List<String> titles = new ArrayList<>();


    View.OnClickListener onFloatButtonClickListener ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TViewUtil.EmptyViewBuilder emptyViewConfig = TViewUtil.EmptyViewBuilder.getInstance(this)
                .setShowText(true)
                .setEmptyText("没有数据")
                .setShowButton(false)
                .setShowIcon(true);
        TEmptyView.init(emptyViewConfig);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        View.OnClickListener action = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                if(onFloatButtonClickListener !=  null){
                    onFloatButtonClickListener.onClick(view);
                }
            }
        };
        fab.setOnClickListener(action);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        init();
    }

    private void init() {

        viewPager = (ViewPager) findViewById(R.id.viewpagerMain);
        tabLayout = (TabLayout) findViewById(R.id.tableLayout);
        textViewDescription = (TextView) findViewById(R.id.textViewDescription);

        fragmentList.add(FragmentListView.newInstance(0, R.layout.fragment_item_list));
        titles.add("ListView in LinearLayout");
        fragmentList.add(FragmentListView.newInstance(1, R.layout.fragment_listview_relativelayout));
        titles.add("ListView in RelativeLayout");
        fragmentList.add(FragmentListView.newInstance(2, R.layout.fragment_listview_framelayout));
        titles.add("ListView in FrameLayout");
        fragmentList.add(FragmentListView.newInstance(3, R.layout.fragment_listview_framelayout));
        titles.add("ListView in FrameLayout");
        fragmentList.add(FragmentListView.newInstance(4, R.layout.fragment_item_grid_view));
        titles.add("GridView in LinearLayout");
        fragmentList.add(FragmentRecyclerView.newInstance(5, R.layout.fragment_item_recycler_view));
        titles.add("Recycler in LinearLayout");


        final FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if(position < titles.size()){
                    return titles.get(position);
                }
                return "No Title";
            }
        };
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                textViewDescription.setText(titles.get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
    }


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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
