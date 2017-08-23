package com.doldam;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ProfileFragment extends Fragment{

    final int REQ_CODE_SELECT_IMAGE=100;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, null);

        ImageView imag = (ImageView) view.findViewById(R.id.profile_imageview);

        Bitmap bm1 = BitmapFactory.decodeResource(getResources(), R.drawable.profileimage);
        Bitmap bm2 = getRoundedCornerBitmap(bm1);
        imag.setImageBitmap(bm2);

        imag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);

                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);

                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);



            }
        });



        return view;
    }


    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = 12;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQ_CODE_SELECT_IMAGE)
        {
            if(resultCode== Activity.RESULT_OK)
            {
                try {
                    //Uri에서 이미지 이름을 얻어온다.
                    //String name_Str = getImageNameToUri(data.getData());
                    //이미지 데이터를 비트맵으로 받아온다.
                    Bitmap image_bitmap 	= MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
                    ImageView image = (ImageView) getView().findViewById(R.id.profile_imageview);
                    //배치해놓은 ImageView에 set
                    image.setImageBitmap(image_bitmap);





                    //Toast.makeText(getBaseContext(), "name_Str : "+name_Str , Toast.LENGTH_SHORT).show();





                } catch (FileNotFoundException e) {

                    // TODO Auto-generated catch block

                    e.printStackTrace();

                } catch (IOException e) {

                    // TODO Auto-generated catch block

                    e.printStackTrace();

                } catch (Exception e)

                {

                    e.printStackTrace();

                }

            }

        }

    }


    public String getImageNameToUri(Uri data)

    {

        String[] proj = { MediaStore.Images.Media.DATA };

        Cursor cursor = getActivity().managedQuery(data, proj, null, null, null);

        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);



        cursor.moveToFirst();



        String imgPath = cursor.getString(column_index);

        String imgName = imgPath.substring(imgPath.lastIndexOf("/")+1);



        return imgName;

    }



}

