package com.vrlocal.android.baseproject.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.stream.JsonReader
import com.vrlocal.android.baseproject.util.VConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import timber.log.Timber

class SeedDatabaseWorker(
        context: Context,
        workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = coroutineScope {
        withContext(Dispatchers.IO) {

            try {
                applicationContext.assets.open(VConstants.DATA_FILENAME).use { inputStream ->
                    JsonReader(inputStream.reader()).use { _ ->
                        /*todo :seeding data from json file*/
//                        val type = object : TypeToken<List<LegoSet>>() {}.type
//                        val list: List<LegoSet> = Gson().fromJson(jsonReader, type)
//                        AppDatabase.getInstance(applicationContext).legoSetDao().insertAll(list)

                        Result.success()
                    }
                }
            } catch (e: Exception) {
                Timber.e(e, "Error seeding database")
                Result.failure()
            }
        }
    }
}