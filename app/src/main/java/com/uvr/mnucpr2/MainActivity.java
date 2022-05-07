package com.uvr.mnucpr2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int[] interval = {22500,22500,23000,27000,22500, 39000,26000,26000,22500,39000,
            26000,26000,26000,26000,36000,26000,36000,28000,31000,24000,
            22500,39000,26000,26000,22500,39000,26000,26000,26000};

    Thread thread;
    boolean isThread = true;
    int indexofinterval=0;

    int [] ImageId = { R.drawable.s03, R.drawable.s04, R.drawable.s05,R.drawable.s06,R.drawable.s07,R.drawable.s08,
            R.drawable.s09,R.drawable.s10,R.drawable.s11,R.drawable.s12,R.drawable.s13,R.drawable.s14,R.drawable.s15,R.drawable.s16,
            R.drawable.s17,R.drawable.s18,R.drawable.s19,R.drawable.s20,R.drawable.s21,R.drawable.s22,R.drawable.s23,R.drawable.s24,
            R.drawable.s25,R.drawable.s26,R.drawable.s27,R.drawable.s28,R.drawable.s29,R.drawable.s30,R.drawable.s31};
    ImageView iv;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView)findViewById(R.id.imageView1);
        tv = (TextView)findViewById(R.id.textView1);

        // res/drawable 폴더에 있는 이미지로 셋팅하기
        iv.setImageResource(R.drawable.s02);

        iv.setOnClickListener(new MyListener());

        thread = new Thread(){
            @Override
            public void run() {
                while(isThread){
                    try {


                        Thread.sleep(interval[indexofinterval]);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Log.d("ylee","interrupt" );
                    }


                    //   handler.sendMessage(indexofinterval);
                    Log.d("ylee", indexofinterval+"+" +String.valueOf(interval[indexofinterval]));
                    indexofinterval++;
                    handler.sendEmptyMessage(indexofinterval);


                }
            }
        };
        thread.start();

    } // end onCreate

    class MyListener implements View.OnClickListener {

        int i = 0, page=0;
        int length = ImageId.length;
      // final TextView tv = (TextView)findViewById(R.id.textView1);

        @Override
        public void onClick(View v) {
            page=indexofinterval+1;
            iv.setImageResource(ImageId[indexofinterval]);
            tv.setText("번호: " + page+"/"+length);

            indexofinterval+=1;
            if(indexofinterval == ImageId.length) indexofinterval = 0;
        } // end onClick


    } // end MyListener()
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            Log.d("ylee", String.valueOf(msg.what));

            int page=0;
            page=indexofinterval+1;
            iv.setImageResource(ImageId[indexofinterval]);
            tv.setText("번호: " + page+"/"+ImageId.length);

//            switch (msg.what){
//                case 0: //3
//                    iv.setImageResource(R.drawable.s00);
//
//                    break;
//                case 1: //2
//                    iv.setImageResource(R.drawable.s01);
//
//                    break;
//
//            }




        }
    };
} //end Class

