import app.cash.turbine.test
import com.hardi.skynews.data.api.APIService
import com.hardi.skynews.data.model.Enclosure
import com.hardi.skynews.data.model.FeedItems
import com.hardi.skynews.data.model.RssFeed
import com.hardi.skynews.data.repository.HomeFeedRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.doThrow
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeFeedRepositoryTest {

    @Mock
    private lateinit var apiService: APIService

    private lateinit var homeFeedRepository: HomeFeedRepository

    @Before
    fun setUp() {
        homeFeedRepository = HomeFeedRepository(apiService)
    }

    // success
    @Test
    fun getHomeFeed_whenResponse_success() {
        runTest {
            val feedItems = listOf(
                FeedItems(
                    title = "Test title 1",
                    link = "Test link 1",
                    description = "Test description 1",
                    enclosure = Enclosure(url = "Test url 1")
                ),
                FeedItems(
                    title = "Test title 2",
                    link = "Test link 2",
                    description = "Test description 2",
                    enclosure = Enclosure(url = "Test url 2")
                )
            )
            val rssHomeFeed = RssFeed(feedList = feedItems)

            doReturn(rssHomeFeed)
                .`when`(apiService)
                .getHomeFeed()

            homeFeedRepository.getHomeFeed().test {
                assertEquals(feedItems, awaitItem())
                cancelAndIgnoreRemainingEvents()
            }

            verify(apiService, times(1)).getHomeFeed()
        }
    }

    // error
    @Test
    fun getHomeFeed_whenResponse_failed_error() {
        runTest {
            val error = "UnknownHostException"

            doThrow(RuntimeException(error))
                .`when`(apiService)
                .getHomeFeed()

            homeFeedRepository.getHomeFeed().test {
                assertEquals(error, awaitError().message)
                cancelAndIgnoreRemainingEvents()
            }

            verify(apiService, times(1)).getHomeFeed()
        }
    }
}