// ReportDatabase.java: КЛАСС ********** База данных для хранения отчетов
package com.example.jupiterreporting.data;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Report.class}, version = 1)
public abstract class ReportDatabase extends RoomDatabase {
    private static ReportDatabase instance;

    public abstract ReportDao reportDao();

    public static synchronized ReportDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            ReportDatabase.class, "report_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}