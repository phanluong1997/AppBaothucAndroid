package com.example.appbaothuc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {
    //BroadcastReceiver nhận các intent từ hệ thống hoặc  trao đổi dữ liệu giữ nhiều ứng dụng vs nhau.
    @Override
    public void onReceive(Context context, Intent intent) {

        //trước hết phải đăng ký Receiver trong manifest.
       // Log.e("tôi","Xin chao");
        String chuoi = intent.getExtras().getString("extra");
        Log.e("Bạn truyền  key",chuoi);

            Intent myIntent = new Intent(context,Music.class);
            myIntent.putExtra("extra",chuoi);
            context.startService(myIntent);
    }
}
