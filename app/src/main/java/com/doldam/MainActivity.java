package com.doldam;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        MainAdapter adapter = new MainAdapter(getSupportFragmentManager());
        adapter.addFragment(new TimeLineFragment(), "TIMELINE");
        adapter.addFragment(new ProfileFragment(), "PROFILE");
        adapter.addFragment(new PopularFragment(), "POPULAR");
        adapter.addFragment(new AttentionFragment(), "ATTENTION");


        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);




    }
}

