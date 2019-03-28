package co.borama.roomrelations

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import junit.framework.Assert.assertEquals
import org.junit.*

import org.junit.runner.RunWith
import org.junit.rules.TestRule
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


@RunWith(AndroidJUnit4::class)
class ListCategoryDaoTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private lateinit var database: Database
    private lateinit var videoBookmarkDao: VideoBookmarkDao
    private lateinit var videoDataDao: VideoDataDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getTargetContext(), Database::class.java)
                .allowMainThreadQueries().build()
        videoBookmarkDao = database.videoBookmarkDao()
        videoDataDao = database.videoDataDao()
    }

    @Test
    fun addAndRetrieveVideoDataAndBookmarks() {

        val path = "path"
        val inputBookmarks = listOf(
                VideoBookmark(1, path, 1),
                VideoBookmark(2, path, 2),
                VideoBookmark(3, path, 3)
        )
        val inputVideoData = VideoData("path")
        inputBookmarks.forEach {
            videoBookmarkDao.insert(it)
        }
        videoDataDao.insert(inputVideoData)
        val videoMetaData = videoDataDao.getVideoMetaData(path).blockingObserve()
        assertEquals(path, videoMetaData!!.videoData.path)
        assertEquals(inputBookmarks.size, videoMetaData.bookmarks.size)
        assertEquals(inputBookmarks[0], videoMetaData.bookmarks[0])
    }

    @After
    fun tearDown() {
        database.close()
    }

    fun <T> LiveData<T>.blockingObserve(): T? {
        var value: T? = null
        val latch = CountDownLatch(1)
        val innerObserver = Observer<T> {
            value = it
            latch.countDown()
        }
        observeForever(innerObserver)
        latch.await(2, TimeUnit.SECONDS)
        return value
    }
}