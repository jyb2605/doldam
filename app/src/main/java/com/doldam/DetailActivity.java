package com.doldam;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by user on 2017-08-23.
 */

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        ImageView project_img = (ImageView)findViewById(R.id.project_img);
        project_img.setBackground(ContextCompat.getDrawable(getApplicationContext(),intent.getExtras().getInt("project_img")));

        ImageView like_btn = (ImageView)findViewById(R.id.like_btn);
        boolean like = intent.getExtras().getBoolean("like_btn");
        if(like){
            like_btn.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.heart_on));
        }
        else{
            like_btn.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.heart_off));
        }
        TextView pj_name = (TextView)findViewById(R.id.pj_name);
        pj_name.setText(intent.getExtras().getString("pj_name"));
        TextView uni = (TextView)findViewById(R.id.uni_txt);
        uni.setText(intent.getExtras().getString("uni_txt"));
        TextView major = (TextView)findViewById(R.id.major);
        major.setText(intent.getExtras().getString("major"));
        TextView presentation = (TextView)findViewById(R.id.presentation);
        presentation.setText(intent.getExtras().getString("presentation"));
        TextView video = (TextView)findViewById(R.id.video);
        video.setText(intent.getExtras().getString("video"));
        TextView member1 = (TextView)findViewById(R.id.member1);
        String member="";
        for(int i=0;i<intent.getExtras().getStringArrayList("member1").size();i++){
            member+=intent.getExtras().getStringArrayList("member1").get(i);
            member+=" ";
        }
        member1.setText(member);
        TextView tech = (TextView)findViewById(R.id.used_tech_txt1);
        String techs="";
        for(int i=0;i<intent.getExtras().getStringArrayList("used_tech_txt1").size();i++){
            techs+=intent.getExtras().getStringArrayList("used_tech_txt1").get(i);
            techs+=" ";
        }
        tech.setText(techs);


        presentation.setText(Html.fromHtml("<a href=\""+ presentation.getText()+"\">발표자료이름</a>"));
        presentation.setMovementMethod(LinkMovementMethod.getInstance());

        video.setText(Html.fromHtml("<a href=\""+video.getText()+"\">동영상자료이름</a>"));
        video.setMovementMethod(LinkMovementMethod.getInstance());
    }


}