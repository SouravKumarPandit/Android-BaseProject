package com.vrlocal.android.baseproject.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.vrlocal.android.baseproject.ui.screens.legoset.data.LegoSet
import com.vrlocal.android.baseproject.ui.screens.legoset.data.LegoSetDao
import com.vrlocal.android.baseproject.ui.screens.legotheme.data.LegoTheme
import com.vrlocal.android.baseproject.ui.screens.legotheme.data.LegoThemeDao
import com.vrlocal.android.baseproject.ui.screens.login.data.User
import com.vrlocal.android.baseproject.ui.screens.login.data.UserDao
import com.vrlocal.android.baseproject.util.VConstants
import com.vrlocal.android.baseproject.worker.SeedDatabaseWorker

/**
 * The Room database for this app
 */
@Database(entities = [LegoTheme::class,
    LegoSet::class,User::class],
        version = 1, exportSchema = false)
@TypeConverters(Converters::class)
    abstract class AppDatabase : RoomDatabase() {

    abstract fun legoSetDao(): LegoSetDao

    abstract fun legoThemeDao(): LegoThemeDao

    abstract fun userDao(): UserDao




    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, VConstants.DATABASE_NAME)
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
                            WorkManager.getInstance(context).enqueue(request)
                        }
                    })
                    .build()
        }
    }
}
