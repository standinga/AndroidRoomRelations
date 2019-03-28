package co.borama.roomrelations

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity()
data class VideoBookmark (
        @PrimaryKey(autoGenerate = true) var id: Long = 0,
        val path: String,
        val position: Long)
