package com.example.marvelapp.presentation.characters

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.marvelapp.extension.asJsonString
import com.example.marvelapp.framework.di.BaseUrlModule
import com.example.marvelapp.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@UninstallModules(BaseUrlModule::class)
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class CharactersFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var server: MockWebServer

    private val robot by lazy { CharactersRobot() }

    @Before
    fun setUp() {
        server = MockWebServer().apply { start(port = 8080) }
        launchFragmentInHiltContainer<CharactersFragment>()
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun whenViewIsCreated_thenShowCharacters() {
        server.enqueue(MockResponse().setBody("characters_p1.json".asJsonString()))

        robot {
            checkCharactersRvIsDisplayed()
        }
    }

    @Test
    fun whenNewPageIsRequested_thenLoadMoreCharacters() {
        with(server) {
            enqueue(MockResponse().setBody("characters_p1.json".asJsonString()))
            enqueue(MockResponse().setBody("characters_p2.json".asJsonString()))
        }

        robot {
            scrollRecyclerViewToSecondPage()
            checkIfCharacterAmoraIsLoaded()
        }
    }

    @Test
    fun whenReceivesAnApiError_thenShowErrorView() {
        server.enqueue(MockResponse().setResponseCode(404))

        robot {
            checkForErrorView()
        }
    }
}
