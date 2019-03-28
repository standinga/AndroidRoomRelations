package co.borama.roomrelations

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert

@Dao
interface VideoBookmarkDao {
    @Insert
    fun insert(bookmark: VideoBookmark)
}