package com.adyen.android.assignment

import android.util.Log
import com.adyen.android.assignment.data.repo.PlanetaryRepository
import com.adyen.android.assignment.data.response.AstronomyResponse
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject


@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class PlanetaryRepositoryTest {
    private val TAG = "PlanetaryServiceTest"

    @get:Rule
    var hiltAndroidRule = HiltAndroidRule(this)

    @Inject
    lateinit var repository: PlanetaryRepository

    @Before
    fun init() {
        hiltAndroidRule.inject()
    }


    @Test
    fun testOrderByTitle() = runTest {
        repository.sortByTitle(preparePlanetFeatures())
            .catch { exception ->
                Log.i(TAG, "Exception : ${exception.message}")
                assert(false)
            } // exception
            .collect { response ->
                Log.i(TAG, "Response : $response")
                assert(response == preparePlanetFeaturesByTitle())
            } // collect
    }


    @Test
    fun testOrderByDate() = runTest {
        repository.sortByDate(preparePlanetFeatures())
            .catch { exception ->
                Log.i(TAG, "Exception : ${exception.message}")
                assert(false)
            } // exception
            .collect { response ->
                Log.i(TAG, "Response : $response")
                assert(response == preparePlanetFeaturesByDate())
            } // collect
    }

    private fun preparePlanetFeatures(): ArrayList<AstronomyResponse> {
        var list: ArrayList<AstronomyResponse> = arrayListOf()

        list.add(
            AstronomyResponse(
                "", "2018-07-12", "", "", "",
                "", "the milky way", ""
            )
        )
        list.add(
            AstronomyResponse(
                "", "2010-07-12", "", "", "",
                "", "ahmed", ""
            )
        )
        list.add(
            AstronomyResponse(
                "", "2028-07-12", "", "", "",
                "", "future", ""
            )
        )
        list.add(
            AstronomyResponse(
                "", "2018-07-12", "", "", "",
                "", "zzzzzzz test", ""
            )
        )

        return list
    }

    private fun preparePlanetFeaturesByTitle(): ArrayList<AstronomyResponse> {
        var list: ArrayList<AstronomyResponse> = arrayListOf()

        list.add(
            AstronomyResponse(
                "", "2010-07-12", "", "", "",
                "", "ahmed", ""
            )
        )

        list.add(
            AstronomyResponse(
                "", "2028-07-12", "", "", "",
                "", "future", ""
            )
        )

        list.add(
            AstronomyResponse(
                "", "2018-07-12", "", "", "",
                "", "the milky way", ""
            )
        )

        list.add(
            AstronomyResponse(
                "", "2018-07-12", "", "", "",
                "", "zzzzzzz test", ""
            )
        )

        return list
    }

    private fun preparePlanetFeaturesByDate(): ArrayList<AstronomyResponse> {
        var list: ArrayList<AstronomyResponse> = arrayListOf()

        list.add(
            AstronomyResponse(
                "", "2010-07-12", "", "", "",
                "", "ahmed", ""
            )
        )

        list.add(
            AstronomyResponse(
                "", "2018-07-12", "", "", "",
                "", "the milky way", ""
            )
        )

        list.add(
            AstronomyResponse(
                "", "2018-07-12", "", "", "",
                "", "zzzzzzz test", ""
            )
        )

        list.add(
            AstronomyResponse(
                "", "2028-07-12", "", "", "",
                "", "future", ""
            )
        )


        return list
    }
}
