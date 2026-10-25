package com.example.gravityfalls;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button btnMorning, btnDay, btnEvening, btnNight;
    private ImageView imgDay, imgEvening, imgNight;
    private ConstraintLayout layoutMorning;

    private NotificationManager notificationManager;
    private static final int NOTIFY_ID = 101;
    private static final String CHANNEL_ID = "GRAVITY_FALLS_CHANNEL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        btnMorning = findViewById(R.id.btnMorning);
        btnDay = findViewById(R.id.btnDay);
        btnEvening = findViewById(R.id.btnEvening);
        btnNight = findViewById(R.id.btnNight);

        imgDay = findViewById(R.id.imageDay);
        imgEvening = findViewById(R.id.imageEvening);
        imgNight = findViewById(R.id.imageNight);
        layoutMorning = findViewById(R.id.layoutMorning);

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        createChannelIfNeeded(notificationManager);
    }
    public static void createChannelIfNeeded(NotificationManager manager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Gravity Falls Notifications",
                    NotificationManager.IMPORTANCE_HIGH
            );
            manager.createNotificationChannel(notificationChannel);
        }
    }
    private void showNotification(String title, String message) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);


        PendingIntent pendingIntent = PendingIntent.getActivity(
                getApplicationContext(),
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setAutoCancel(true)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH);


        notificationManager.notify(NOTIFY_ID, builder.build());
    }
    public void btnMorningClick(View view) {
        layoutMorning.setVisibility(View.VISIBLE);
        imgDay.setVisibility(View.INVISIBLE);
        imgEvening.setVisibility(View.INVISIBLE);
        imgNight.setVisibility(View.INVISIBLE);

        showNotification("Утро в Хижине", "Пора проверить ловушки гномов и открыть сувенирную лавку!");
    }
    public void btnDayClick(View view) {
        layoutMorning.setVisibility(View.INVISIBLE);
        imgDay.setVisibility(View.VISIBLE);
        imgEvening.setVisibility(View.INVISIBLE);
        imgNight.setVisibility(View.INVISIBLE);

        showNotification("День приключений", "Диппер и Мэйбл отправляются на поиски тайн в лес!");
    }
    public void btnEveningClick(View view) {
        layoutMorning.setVisibility(View.INVISIBLE);
        imgDay.setVisibility(View.INVISIBLE);
        imgEvening.setVisibility(View.VISIBLE);
        imgNight.setVisibility(View.INVISIBLE);

        showNotification("Тихий вечер", "Дядя Стэн считает дневную выручку. Берегитесь летучих мышей!");
    }
    public void btnNightClick(View view) {
        layoutMorning.setVisibility(View.INVISIBLE);
        imgDay.setVisibility(View.INVISIBLE);
        imgEvening.setVisibility(View.INVISIBLE);
        imgNight.setVisibility(View.VISIBLE);

        showNotification("Опасная ночь", "Билл Шифр следит за вами... Помни: реальность — иллюзия!");
    }
    public void onDipperClick(View view) {
        showToast("Диппер Пайнс: одержим разгадкой тайн городка!");
    }
    public void onMabelClick(View view) {
        showToast("Мэйбл Пайнс: мастер абордажного крюка и любитель свинок!");
    }
    public void onShackClick(View view) {
        showToast("Хижина Чудес: самая странная туристическая ловушка в Орегоне.");
    }
    public void onJournalClick(View view) {
        showToast("Дневник №3: «Никому не доверяй в этом лесу!»");
    }
    private void showToast(String text) {
        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM, 0, 100);
        toast.show();
    }

}