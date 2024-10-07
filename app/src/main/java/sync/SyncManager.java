// SyncManager.java: КЛАСС SyncManager для запуска синхронизации данных
package com.example.jupiterreporting.sync;

import android.content.Context;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import java.util.concurrent.TimeUnit;

public class SyncManager {
    public static void startSync(Context context) {
        OneTimeWorkRequest syncRequest = new OneTimeWorkRequest.Builder(SyncWorker.class)
                .setInitialDelay(1, TimeUnit.MINUTES) // Задержка перед началом синхронизации
                .build();
        WorkManager.getInstance(context).enqueue(syncRequest);
    }
}