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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TimeLineFragment extends Fragment{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timeline, null);

        ArrayList<Data> item_list = new ArrayList<>();

        item_list.add(new Data("DolDam","인하대학교","컴퓨터공학과","작품을 한줄로 설명하는 공간입니다.작품을 한줄로 설명하는 공간입니다.",R.drawable.cat1,true));
        item_list.add(new Data("DolDam2","인하대학교","컴퓨터공학과","작품을 한줄로 설명하는 공간입니다.작품을 한줄로 설명하는 공간입니다.",R.drawable.cat2));

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

            final Data data = components_list.get(position);

            // 이미지 삽입
            ImageView img = (ImageView) convertView.findViewById(R.id.project_img);
            img.setBackground(ContextCompat.getDrawable(convertView.getContext(),data.getImg()));

            // 좋아요 버튼 상태
            final ImageButton like_btn = (ImageButton)convertView.findViewById(R.id.like_btn);
            if(data.isLike()){
                like_btn.setBackground(ContextCompat.getDrawable(convertView.getContext(),R.drawable.heart_on));
            }
            else{
                like_btn.setBackground(ContextCompat.getDrawable(convertView.getContext(),R.drawable.heart_off));
            }

            // 좋아요 버튼 클릭
            final View finalConvertView = convertView;
            like_btn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    data.setLike(!data.isLike());
                    if(data.isLike()){
                        like_btn.setBackground(ContextCompat.getDrawable(finalConvertView.getContext(),R.drawable.heart_on));
        }
                    else{
            like_btn.setBackground(ContextCompat.getDrawable(finalConvertView.getContext(),R.drawable.heart_off));
        }
                    Toast.makeText(getActivity(),"상태"+data.isLike(),Toast.LENGTH_SHORT).show();
    }
});


            // 프로젝트 이름
            TextView pj_name = (TextView)convertView.findViewById(R.id.pj_name_txt);
            pj_name.setText(data.getPj_name());

            // 학교
            TextView uni_txt = (TextView)convertView.findViewById(R.id.uni_txt);
            uni_txt.setText(data.getUniversity());

            // 학과
            TextView major = (TextView)convertView.findViewById(R.id.major);
            major.setText(data.getMajor());

            // 맴버
            TextView member = (TextView)convertView.findViewById(R.id.member1);
            String _member="";
            for(int i=0;i<data.memberLength();i++){
                _member+=data.getMember(i);
                _member+=" ";
            }
            member.setText(_member);

            // 요약
            TextView summary = (TextView)convertView.findViewById(R.id.summary_txt);
            summary.setText(data.getSummary());

            // 기술
            TextView tech = (TextView)convertView.findViewById(R.id.used_tech_txt1);
            String _tech="";
            for(int i=0;i<data.techLength();i++){
                _tech+=data.getTech(i);
                _tech+=" ";
            }
            tech.setText(_tech);


            return convertView;
        }
    }


}

