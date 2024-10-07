// MainActivity.java: КЛАСС ********** Пример использования SyncManager для запуска синхронизации
package com.example.jupiterreporting;

import static com.example.jupiterreporting.R.*;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Запуск фоновой синхронизации
        com.example.jupiterreporting.sync.SyncManager.startSync(this);
    }
}