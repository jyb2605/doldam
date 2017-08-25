package com.doldam;


import android.app.Fragment;
import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    static public String search="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        final MainAdapter adapter = new MainAdapter(getSupportFragmentManager());
        adapter.addFragment(new TimeLineFragment(), null);
        adapter.addFragment(new PopularFragment(), null);
        adapter.addFragment(new AttentionFragment(), null);
        adapter.addFragment(new ProfileFragment(), null);




        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.timeline);
        tabLayout.getTabAt(1).setIcon(R.drawable.star);
        tabLayout.getTabAt(2).setIcon(R.drawable.heart);
        tabLayout.getTabAt(3).setIcon(R.drawable.profile);



        TabLayout.Tab tab = tabLayout.getTabAt(0);
        int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);


        tabLayout.setOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {

                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
                        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        super.onTabUnselected(tab);
                        int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.inActive);
                        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        super.onTabReselected(tab);
                    }
                }
        );
//        final ListView list = (ListView)adapter.getItem(0).getActivity().findViewById(R.id.lst_work);
//        list.setTextFilterEnabled(true);
//
//        });

        final EditText edittext = (EditText)findViewById(R.id.editText);
        edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                search = edittext.getText().toString();
            }
            });
//        Fragment timelineFragement = (Fragment)getFragmentManager().findFragmentById(R.id.viewpager);
//        ListView list = (ListView)timelineFragement.getActivity().findViewById(R.id.lst_work);

//        LayoutInflater layoutInflater = (LayoutInflater)((MainActivity)adapter.getItem(0).getActivity()).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = layoutInflater.inflate(R.layout.fragment_timeline, null);



    }


}

