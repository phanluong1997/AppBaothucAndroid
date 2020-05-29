package com.example.appbaothuc;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class Music extends Service {
    MediaPlayer mediaPlayer;
    int id;

    @Override
    public IBinder onBind(Intent intent) {


        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("tôi","Xin chao");
            String nhankey = intent.getExtras().getString("extra");
            Log.e("Music nhan key",nhankey);

            if (nhankey.equals("on"))
            {
                id=1;
            }
            else if (nhankey.equals("off")){
                id=0;

            }
               if (id ==1)
               {
                   mediaPlayer = MediaPlayer.create(this,R.raw.songgio);
                   mediaPlayer.start();
                   id =0;
               }
               else if (id==0){
                   mediaPlayer.stop();
                   mediaPlayer.reset();
               }
        return START_NOT_STICKY;
    }
}
