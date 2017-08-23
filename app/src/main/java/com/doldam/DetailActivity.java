package com.doldam;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by user on 2017-08-23.
 */

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView data = (TextView) findViewById(R.id.data);
        data.setText(Html.fromHtml("<a href=\"http://www.naver.com\">doldam.pdf</a>"));
        data.setMovementMethod(LinkMovementMethod.getInstance());

        TextView video = (TextView) findViewById(R.id.video);
        video.setText(Html.fromHtml("<a href=\"http://www.naver.com\">doldam.avi</a>"));
        video.setMovementMethod(LinkMovementMethod.getInstance());


    }


}