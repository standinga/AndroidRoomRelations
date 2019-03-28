package co.borama.roomrelations

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [VideoData::class, VideoBookmark::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract fun videoDataDao(): VideoDataDao
    abstract fun videoBookmarkDao(): VideoBookmarkDao
}