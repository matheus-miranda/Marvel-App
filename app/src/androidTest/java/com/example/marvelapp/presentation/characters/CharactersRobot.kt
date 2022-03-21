package com.example.marvelapp.presentation.characters

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.marvelapp.R

class CharactersRobot {

    operator fun invoke(block: CharactersRobot.() -> Unit) = block(this)

    fun checkCharactersRvIsDisplayed() {
        onView(withId(R.id.rv_characters)).check(matches(isDisplayed()))
    }
}
