package com.example.marvelapp.presentation.characters

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.marvelapp.R
import com.example.marvelapp.presentation.characters.adapters.CharactersViewHolder

class CharactersRobot {

    operator fun invoke(block: CharactersRobot.() -> Unit) = block(this)

    fun checkCharactersRvIsDisplayed() {
        onView(withId(R.id.rv_characters)).check(matches(isDisplayed()))
    }

    fun scrollRecyclerViewToSecondPage() {
        onView(
            withId(R.id.rv_characters)
        ).perform(
            RecyclerViewActions
                .scrollToPosition<CharactersViewHolder>(20)
        )
    }

    fun checkIfCharacterAmoraIsLoaded() {
        onView(withText("Amora")).check(matches(isDisplayed()))
    }

    fun checkForErrorView() {
        onView(withId(R.id.text_initial_loading_error)).check(matches(isDisplayed()))
    }
}
