package com.doldam;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class TimeLineFragment extends Fragment{

    watch watcher;
    Handler mhandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timeline, null);

        ArrayList<Data> item_list = new ArrayList<>();

//        item_list.add(new Data("DolDam","인하대학교","컴퓨터공학과","작품을 한줄로 설명하는 공간입니다.작품을 한줄로 설명하는 공간입니다.",R.drawable.cat1,true));
//        item_list.add(new Data("DolDam2","인하대학교","컴퓨터공학과","작품을 한줄로 설명하는 공간입니다.작품을 한줄로 설명하는 공간입니다.",R.drawable.cat2));

        item_list.add(new Data("DolDam","인하대학교","컴퓨터공학과","작품을 한줄로 설명하는 공간입니다.작품을 한줄로 설명하는 공간입니다.",R.drawable.cat1,"발표자료주소","비디오주소",true));
        item_list.add(new Data("DolDam2","인하대학교","컴퓨터공학과","작품을 한줄로 설명하는 공간입니다.작품을 한줄로 설명하는 공간입니다.",R.drawable.cat2,"발표자료주소","비디오주소"));
// 생성자 바껴서 참고해


        item_list.get(0).addMember("맴버1");
        item_list.get(0).addMember("맴버2");
        item_list.get(0).addMember("맴버3");

        item_list.get(1).addMember("이하늘");
        item_list.get(1).addMember("김하늘");
        item_list.get(1).addMember("박하늘");


        item_list.get(0).addTech("#tech1");
        item_list.get(0).addTech("#tech2");
        item_list.get(0).addTech("#tech3");
        item_list.get(0).addTech("#tech4");



        item_list.get(1).addTech("#안드로이드");
        item_list.get(1).addTech("#리스트뷰");
        item_list.get(1).addTech("#관리자페이지");


        final myAdapter Adapter = new myAdapter(view.getContext(), R.layout.item, item_list);
        ListView list = (ListView)view.findViewById(R.id.lst_work);

        list.setAdapter(Adapter);
        list.setTextFilterEnabled(true);

        mhandler = new Handler(){
            public void handleMessege(Message msg) {
                if(msg.what ==0) {
                    Adapter.filter(MainActivity.search);
                }
            }
        };

        watcher = new watch(Adapter);
        watcher.start();

        return view;
    }

    public class myAdapter extends BaseAdapter{
        Context con;
        LayoutInflater inflater;
        ArrayList<Data> components_list;
        ArrayList<Data> searched_list;
        int layout;
        myAdapter(Context context, int layout, ArrayList<Data> components_list) {
            con = context;
            this.layout = layout;
            this.components_list = components_list;
            searched_list = new ArrayList<>();
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

            // 레이아웃 전체 클릭시 디테일 페이지 이동
//            LinearLayout item_layout =(LinearLayout)convertView.findViewById(R.id.item_layout);
//            item_layout.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(getActivity(), DetailActivity.class);
//                    startActivity(intent);
//                }
//            });

            // 이미지 클릭시 디테일 페이지 이동
            img.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), DetailActivity.class);
                    intent.putExtra("project_img",data.getImg());
                    intent.putExtra("like_btn",data.isLike());
                    intent.putExtra("pj_name",data.getPj_name());
                    intent.putExtra("uni_txt",data.getUniversity());
                    intent.putExtra("major",data.getMajor());
                    intent.putExtra("presentation",data.getPresentation());
                    intent.putExtra("video",data.getVideo());
                    intent.putExtra("member1",data.getMembers());
                    intent.putExtra("used_tech_txt1",data.getTechs());
                    startActivity(intent);
                }
            });

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
            LinearLayout tech_layout = (LinearLayout)convertView.findViewById(R.id.tech_layout);
            ArrayList<TextView> tech = new ArrayList<>();
            Toast.makeText(getActivity(),position+"번째"+data.techLength(),Toast.LENGTH_SHORT).show();

            for(int i=0;i<data.techLength();i++){
                tech.add(new TextView(getActivity()));
                tech.get(i).setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                tech.get(i).setTextSize(15);
                tech.get(i).setText(data.getTech(i)+" ");

                tech.get(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //헤시테그
                    }
                });
            }
            for(int i=0;i<data.techLength();i++) {
                tech_layout.addView(tech.get(i));
            }
//            TextView tech = (TextView)convertView.findViewById(R.id.used_tech_txt1);
//
//            String _tech="";
//            for(int i=0;i<data.techLength();i++){
//                _tech+=data.getTech(i);
//                _tech+=" ";
//            }
//            tech.setText(_tech);
//

            return convertView;
        }

        public void filter(String search){
            if(search == ""){
                searched_list = components_list;
            }
            else{
                Data temp;
                searched_list.clear();
                for(int i=0;i<components_list.size();i++){
                    temp = components_list.get(i);
                    String all="";
                    all+=temp.getPj_name();
                    all+=temp.getUniversity();
                    all+=temp.getMajor();
                    for(int j=0;j<temp.memberLength();j++){
                        all+=temp.getMembers().get(j);
                    }
                    all+=temp.getSummary();
                    for(int j=0;j<temp.techLength();j++){
                        all+=temp.getTechs().get(i);
                    }

                    if(all.contains(search)){
                        searched_list.add(temp);
                    }
                }
            }
            notifyDataSetChanged();
        }
    }

    class watch extends Thread{
        myAdapter adapter;
        String str;

        public watch(myAdapter adapter){
            this.adapter = adapter;
            str = "";
        }

        public void run(){
            while (true){
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(str!=MainActivity.search){
                    Log.e(this.getClass().getName(),"!!");
                    mhandler.sendEmptyMessage(0);
                }
            }
        }
    }

}

