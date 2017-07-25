package com.doldam;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class TimeLineFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timeline, null);

        ArrayList<Data> item_list = new ArrayList<>();

        item_list.add(new Data("DolDam","인하대학교","컴퓨터공학과","작품을 한줄로 설명하는 공간입니다.작품을 한줄로 설명하는 공간입니다."));
        item_list.add(new Data("DolDam2","인하대학교","컴퓨터공학과","작품을 한줄로 설명하는 공간입니다.작품을 한줄로 설명하는 공간입니다."));

        item_list.get(0).addMember("맴버1");
        item_list.get(0).addMember("맴버2");
        item_list.get(0).addMember("맴버3");

        item_list.get(1).addMember("이하늘");
        item_list.get(1).addMember("김하늘");
        item_list.get(1).addMember("박하늘");


        item_list.get(0).addTech("#tech1");
        item_list.get(0).addTech("#tech2");
        item_list.get(0).addTech("#tech3");


        item_list.get(1).addTech("#안드로이드");
        item_list.get(1).addTech("#리스트뷰");
        item_list.get(1).addTech("#관리자페이지");

        myAdapter Adapter = new myAdapter(view.getContext(), R.layout.item, item_list);
        ListView list = (ListView)view.findViewById(R.id.lst_work);

        list.setAdapter(Adapter);


        return view;
    }

    public class myAdapter extends BaseAdapter {
        Context con;
        LayoutInflater inflater;
        ArrayList<Data> components_list;
        int layout;
        myAdapter(Context context, int layout, ArrayList<Data> components_list) {
            con = context;
            this.layout = layout;
            this.components_list = components_list;
            inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            // 멤버변수 초기화
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return components_list.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return components_list.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            if (null == convertView) {
                convertView = inflater.inflate(layout, parent, false);
                //cells 를 뷰화시켜서 아이템목록으로 삽입
            }
            ImageView img = (ImageView) convertView.findViewById(R.id.project_img);

            img.setBackground(ContextCompat.getDrawable(convertView.getContext(),R.drawable.cat1));


            return convertView;
        }

        public View getView2(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            if (null == convertView) {
                convertView = inflater.inflate(layout, parent, false);
                //cells 를 뷰화시켜서 아이템목록으로 삽입
            }
            ImageView img = (ImageView) convertView.findViewById(R.id.project_img);

            img.setBackground(ContextCompat.getDrawable(convertView.getContext(),R.drawable.cat1));


            return convertView;
        }
    }


}

