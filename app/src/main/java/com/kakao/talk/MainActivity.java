package com.kakao.talk;

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
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


public class MainActivity extends AppCompatActivity {

    Thread thread, beepThread;
    boolean isThread = true;
    boolean isbeepThread = true;
    boolean soundOn = false;
    ToneGenerator tone;
    //  private SlideHandler handler = new SlideHandler();
    private Beephandler beephandler = new Beephandler();
    ImageView imageView;
    Button btnLeft, btnRight;


    int[] interval = {1500,1500,1500,
            22500,22500,23000,27000,22500, 39000,26000,26000,22500,39000,
            26000,26000,26000,26000,36000,26000,36000,28000,31000,24000,
            22500,39000,26000,26000,22500,39000,26000,26000,26000};


    int indexofinterval=0;

    int [] ImageId = { R.drawable.s03, R.drawable.s04, R.drawable.s05,R.drawable.s06,R.drawable.s07,R.drawable.s08,
            R.drawable.s09,R.drawable.s10,R.drawable.s11,R.drawable.s12,R.drawable.s13,R.drawable.s14,R.drawable.s15,R.drawable.s16,
            R.drawable.s17,R.drawable.s18,R.drawable.s19,R.drawable.s20,R.drawable.s21,R.drawable.s22,R.drawable.s23,R.drawable.s24,
            R.drawable.s25,R.drawable.s26,R.drawable.s27,R.drawable.s28,R.drawable.s29,R.drawable.s30,R.drawable.s31};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView1);
        btnLeft = (Button) findViewById(R.id.buttonLeft);
        btnRight=(Button)findViewById(R.id.buttonRight);

        tone = new ToneGenerator(AudioManager.STREAM_MUSIC,ToneGenerator.MAX_VOLUME);

        btnLeft.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                indexofinterval-=1;
                handler.sendEmptyMessage(indexofinterval);

                if(indexofinterval == ImageId.length) indexofinterval = 0;
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                indexofinterval+=1;
                handler.sendEmptyMessage(indexofinterval);

                if(indexofinterval == ImageId.length) indexofinterval = 0;
            }
        });

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

        beepThread = new Thread(){
            @Override
            public void run() {
                while(isbeepThread){
                    try {
                        sleep(545);


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    beephandler.sendEmptyMessage(indexofinterval);

                }
            }
        };
        beepThread.start();

    }

    @Override
    protected void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        // mDemoSlider.stopAutoCycle();
        isThread =  false;
        isbeepThread = false;
        super.onStop();
    }





    private void createNotification() {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default");

        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("CPR Alarm");
        builder.setContentText("Emergency");

        builder.setColor(Color.RED);
        // ???????????? ?????? ???????????? ?????? ??????
        builder.setAutoCancel(true);


        // ?????? ??????
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(new NotificationChannel("default", "?????? ??????", NotificationManager.IMPORTANCE_HIGH));
        }

        // id??????
        // ?????????????????? ??? ????????? ????????? int???
        notificationManager.notify(1, builder.build());
    }

    private void removeNotification() {

        // Notification ??????
        NotificationManagerCompat.from(this).cancel(1);
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            Log.d("ylee", String.valueOf(msg.what));

            switch (msg.what){
                case 0: //3
                    imageView.setImageResource(R.drawable.s00);
                    soundOn = false;
                    break;
                case 1: //2
                    imageView.setImageResource(R.drawable.s01);
                    soundOn = false;
                    break;
                case 2: //1
                    imageView.setImageResource(R.drawable.s02);
                    soundOn = false;
                    break;
                case 3:
                    //createNotification();
                    //removeNotification();
                    vibration();
                    imageView.setImageResource(R.drawable.s03);
                    soundOn = false;
                    break;
                case 4:
                    imageView.setImageResource(R.drawable.s04);
                    soundOn = false;
                    break;
                case 5:
                    imageView.setImageResource(R.drawable.s05);
                    soundOn = false;
                    break;
                case 6:
                    //createNotification();
                    //removeNotification();
                    vibration();
                    imageView.setImageResource(R.drawable.s06);
                    soundOn = false;
                    break;
                case 7:
                    //createNotification();
                    //removeNotification();
                    vibration();
                    imageView.setImageResource(R.drawable.s07);
                    soundOn = false;
                    break;
                case 8:
                    //createNotification();
                    //removeNotification();
                    vibration();
                    imageView.setImageResource(R.drawable.s08);
                    soundOn = true;
                    break;
                case 9:
                    imageView.setImageResource(R.drawable.s09);
                    soundOn = false;
                    break;
                case 10:
                    imageView.setImageResource(R.drawable.s10);
                    soundOn = false;
                    break;
                case 11:
                    //createNotification();
                    //removeNotification();
                    vibration();
                    imageView.setImageResource(R.drawable.s11);
                    break;
                case 12:
                    imageView.setImageResource(R.drawable.s12);
                    soundOn = true;
                    break;
                case 13:
                    //createNotification();
                    //removeNotification();
                    vibration();
                    imageView.setImageResource(R.drawable.s13);
                    soundOn = false;
                    break;
                case 14:
                    imageView.setImageResource(R.drawable.s14);
                    break;
                case 15:
                    //createNotification();
                    //removeNotification();
                    vibration();
                    imageView.setImageResource(R.drawable.s15);
                    break;
                case 16:
                    //createNotification();
                    //removeNotification();
                    vibration();
                    imageView.setImageResource(R.drawable.s16);
                    break;
                case 17:
                    //createNotification();
                    //removeNotification();
                    vibration();
                    imageView.setImageResource(R.drawable.s17);
                    break;
                case 18:
                    //createNotification();
                    //removeNotification();
                    vibration();
                    imageView.setImageResource(R.drawable.s18);
                    soundOn = false;
                    break;
                case 19:
                    //createNotification();
                    //removeNotification();
                    vibration();
                    imageView.setImageResource(R.drawable.s19);
                    soundOn = false;
                    break;
                case 20:
                    //createNotification();
                    //removeNotification();
                    vibration();
                    imageView.setImageResource(R.drawable.s20);
                    soundOn = true;
                    break;
                case 21:
                    //createNotification();
                    //removeNotification();
                    vibration();
                    imageView.setImageResource(R.drawable.s21);
                    soundOn = false;
                    break;
                case 22:
                    //createNotification();
                    //removeNotification();
                    vibration();
                    imageView.setImageResource(R.drawable.s22);
                    soundOn = false;
                    break;
                case 23:
                    //createNotification();
                    //removeNotification();
                    vibration();
                    imageView.setImageResource(R.drawable.s23);
                    soundOn = false;
                    break;
                case 24:

                    imageView.setImageResource(R.drawable.s24);
                    soundOn = true;
                    break;
                case 25:
                    //createNotification();
                    //removeNotification();
                    vibration();
                    imageView.setImageResource(R.drawable.s25);
                    soundOn = false;
                    break;
                case 26:
                    imageView.setImageResource(R.drawable.s26);
                    break;
                case 27:
                    //createNotification();
                    //removeNotification();
                    vibration();
                    imageView.setImageResource(R.drawable.s27);
                    break;
                case 28:

                    imageView.setImageResource(R.drawable.s28);
                    soundOn = true;

                    break;
                case 29:
                    //createNotification();
                    //removeNotification();
                    vibration();
                    imageView.setImageResource(R.drawable.s29);
                    soundOn = false;
                    break;
                case 30:
                    imageView.setImageResource(R.drawable.s30);

                    break;
                case 31:
                    //createNotification();
                    //removeNotification();
                    vibration();
                    imageView.setImageResource(R.drawable.s31);
                    isThread =  false;
                    isbeepThread = false;
                    break;
            }




        }
    };

    private class Beephandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {

            if(soundOn) {
                tone.startTone(ToneGenerator.TONE_PROP_BEEP,500);

            }

        }
    }

    private void vibration(){

        createNotification();
        removeNotification();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}