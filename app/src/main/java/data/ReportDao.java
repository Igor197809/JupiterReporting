// ReportDao.java: КЛАСС ********** DAO для работы с таблицей отчетов
package com.example.jupiterreporting.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface ReportDao {
    @Insert
    void insert(Report report);

    @Update
    void update(Report report);

    @Query("SELECT * FROM reports WHERE synced = 0")
    List<Report> getUnsyncedReports();
}