package co.borama.roomrelations

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation

data class VideoMetaData (
    @Embedded
    var videoData: VideoData = VideoData(""),
    @Relation(parentColumn = "path", entityColumn = "path", entity = VideoBookmark::class)
    var bookmarks: List<VideoBookmark> = emptyList()
)