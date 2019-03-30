package co.borama.roomrelations

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.PrimaryKey

@Entity(foreignKeys = [(ForeignKey(
                entity = VideoData::class,
                parentColumns = ["path"],
                childColumns = ["path"],
                onDelete = CASCADE))])
data class VideoBookmark (
        @PrimaryKey(autoGenerate = true) var id: Long = 0,
        val path: String,
        val position: Long)
