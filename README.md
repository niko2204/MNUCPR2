# MNUCPR
## 참여연구원
 * 이영호, 김선경, 최종명, 고영혜, 박건우, 양효정
 * 목포대학교 재난안전사업단
## 제작 목적
1. 이미지 뷰어를 이용하여 슬라이드 보여주기
2. 각 이미지에 정해진 시간 만큼 보여주고 다음 슬라이드로 넘어가기
3. 정해진 슬라이드가 보여질때 notification 발생시키기 --> 스마트 워치 진동 발생
4. 110 BPM 비프음 발생
## 타이틀 바 없애기
- style.xml에 다음 추가
```
<item name="windowActionBar">false</item>
<item name="windowNoTitle">true</item>
```
## notification 생성 및 제거

```
private void createNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("CPR Alarm");
        builder.setContentText("Emergency");
        builder.setColor(Color.RED);
        builder.setAutoCancel(true);

        // 알림 표시
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(new NotificationChannel("default", "기본 채널", NotificationManager.IMPORTANCE_HIGH));
        }

        notificationManager.notify(1, builder.build());
    }

    private void removeNotification() {
        NotificationManagerCompat.from(this).cancel(1);
    }
 ```
 ## Thread 와 Handler
 ```
 Thread thread;
 boolean isThread = true;
 ```
 onCreat 함수 안에 다음과 같이 thread를 생성한다.
 ```
 thread = new Thread(){
            @Override
            public void run() {
                while(isThread){
                    try {
                        sleep(interval[indexofinterval]);


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.sendEmptyMessage(indexofinterval);
                    indexofinterval++;
                }
            }
        };
        thread.start();
 ```
 다음은 handler코드이다.
 ```
 private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            mDemoSlider.moveNextPosition();
            if(vibration[msg.what] == 1) {
             ///   removeNotification();
              //  createNotification();
            }

        }
    };
 ```
## 시나리오
* 1 번 슬라이드 2초 (진동)
* 2 번 슬라이드 2초
* 3 번 슬라이드 2초
* 4 번 슬라이드 6초 (진동)
* 5 번 슬라이드 18초 (진동) 110/min (속도의 알람음)
* 6 번 슬라이드 2초 (진동) 
* 7 번 슬라이드 3초
* 8 번 슬라이드 2초
* 9 번 슬라이드 3초
* 10 번 슬라이드 18초 (진동) 110/min (속도의 알람음)
* 11 번 슬라이드 2초 (진동)
* 12 번 슬라이드 3초
* 13 번 슬라이드 2초
* 14 번 슬라이드 3초
* 15 번 슬라이드 65초 (진동) 
* 16 번 슬라이드 18초 (진동) 110/min (속도의 알람음)
* 17 번 슬라이드 2초 (진동)
* 18 번 슬라이드 3초
* 19 번 슬라이드 2초
* 20 번 슬라이드 3초
* 21 번 슬라이드 18초 (진동) 110/min (속도의 알람음)
* 22 번 슬라이드 2초 (진동)
* 23 번 슬라이드 3초
* 24 번 슬라이드 2초
* 25 번 슬라이드 3초
* 26 번 슬라이드 10초 (진동)


## 2022년 5월 (새로운 CPR 훈련을 위한 변경)
 * 슬라이드를 시작하는 버튼을 두개 제작. CPR훈련, 자가학습
 * CPR 훈련은 충분한 시간 후 슬라이드 넘어감
 * 자가학습은 기존의 그대로


