package co.borama.roomrelations

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity()
data class VideoData(@PrimaryKey
                    val path: String) {
    var lastPlaybackPosition: Long = 0
}