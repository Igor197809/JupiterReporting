// SyncWorker.java: КЛАСС SyncWorker для работы с WorkManager
package com.example.jupiterreporting.sync;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.jupiterreporting.data.Report;
import com.example.jupiterreporting.data.ReportDatabase;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;

import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class SyncWorker extends Worker {
    private static final String SPREADSHEET_ID = "1J5wxqk1_nCPEUBnilSHI8RgnWU7CUV-vxrAVGl6LX5M";
    private static final String RANGE = "Sheet1!A1:D10";

    public SyncWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            // Получение токена доступа пользователя
            String accessToken = getAccessToken(); // Реализуйте метод для получения актуального токена

            // Создаем GoogleCredentials с токеном доступа
            AccessToken token = new AccessToken(accessToken, null);
            GoogleCredentials credentials = GoogleCredentials.create(token);

            // Создаем Sheets API клиент
            JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
            HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(credentials);
            Sheets sheetsService = new Sheets.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    jsonFactory,
                    requestInitializer)
                    .setApplicationName("Jupiter_Reporting")
                    .build();

            // Получение отчетов из локальной базы данных
            ReportDatabase db = ReportDatabase.getInstance(getApplicationContext());
            List<Report> unsyncedReports = db.reportDao().getUnsyncedReports();

            // Формирование данных для отправки в Google Sheets
            List<List<Object>> values = new ArrayList<>();
            for (Report report : unsyncedReports) {
                List<Object> row = new ArrayList<>();
                row.add(report.getId());
                row.add(report.getDate());
                row.add(report.getDescription());
                row.add(report.getStatus());
                values.add(row);
            }

            if (!values.isEmpty()) {
                ValueRange body = new ValueRange().setValues(values);
                sheetsService.spreadsheets().values()
                        .append(SPREADSHEET_ID, RANGE, body)
                        .setValueInputOption("RAW")
                        .execute();

                // Обновление статуса синхронизации отчетов
                for (Report report : unsyncedReports) {
                    report.setSynced(true);
                    db.reportDao().update(report);
                }
            }

            return Result.success();
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
            return Result.retry();
        }
    }

    // Метод для получения актуального токена доступа
    private String getAccessToken() {
        // Реализуйте логику получения и обновления токена доступа
        // Возможно, храните токен в SharedPreferences или другом безопасном хранилище
        return "your_access_token_here";
    }
}
