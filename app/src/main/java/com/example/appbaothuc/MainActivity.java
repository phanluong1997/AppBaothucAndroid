package com.example.appbaothuc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button btnHenGio, btnDunglai;
    TextView txtHienthi;
    TimePicker timePicker;
    Calendar calendar = Calendar.getInstance();
    AlarmManager alarmManager ;
    PendingIntent pendingIntent ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();
        addEvents();


    }

    private void addEvents() {
        final Intent intent = new Intent(MainActivity.this,AlarmReceiver.class);

        btnHenGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // lấy ngày h hiện tại
                calendar.set(Calendar.HOUR_OF_DAY,timePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE,timePicker.getCurrentMinute());
                int gio = timePicker.getCurrentHour();
                int phut = timePicker.getCurrentMinute();

                String string_gio =String.valueOf(gio);

                String string_phut = String.valueOf(phut);
                intent.putExtra("extra","on");
                pendingIntent = PendingIntent.getBroadcast(MainActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),pendingIntent);

                txtHienthi.setText("hẹn giờ: "+string_gio+":"+string_phut);
            }
        });
        btnDunglai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtHienthi.setText("Bạn đã dừng lại");
                alarmManager.cancel(pendingIntent);
                intent.putExtra("extra","off");
                sendBroadcast(intent);

            }
        });
    }

    private void addControl() {
        btnHenGio = findViewById(R.id.btnHenGio);
        btnDunglai = findViewById(R.id.btnDunglai);
        txtHienthi = findViewById(R.id.txtHienThi);
        timePicker = findViewById(R.id.timePicker);

        // alaemManager cho phép truy cập hệ thống báo động của máy .. ALARM_SERVICE là báo thức.
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);




    }
}
