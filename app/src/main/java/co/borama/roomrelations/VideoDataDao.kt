package co.borama.roomrelations

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface VideoDataDao {
    @Insert
    fun insert(videoData: VideoData)

    @Query("SELECT * FROM videodata WHERE path = :path")
    fun getVideoMetaData(path: String): LiveData<VideoMetaData>
}