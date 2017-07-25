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
        adapter.addFragment(new TimeLineFragment(), null);
        adapter.addFragment(new ProfileFragment(), null);
        adapter.addFragment(new PopularFragment(), null);
        adapter.addFragment(new AttentionFragment(), null);


        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.timeline);
        tabLayout.getTabAt(1).setIcon(R.drawable.star);
        tabLayout.getTabAt(2).setIcon(R.drawable.heart);
        tabLayout.getTabAt(3).setIcon(R.drawable.profile);



    }
}

