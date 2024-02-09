package com.restart.androidtesting

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest{

    @get:Rule
    val activity = ActivityScenarioRule<MainActivity>(MainActivity::class.java)

    var idlingResource: IdlingResource? = null

    @Before
    fun init()
    {
        // register Idling Resource
        activity.scenario.onActivity {
            idlingResource = it.getIdlingResource()
            IdlingRegistry.getInstance().register(idlingResource)
        }
    }

    @After
    fun finish()
    {
        if(idlingResource != null) IdlingRegistry.getInstance().unregister(idlingResource)
    }

    //This Tet Will Fail if i not register Idling Resource
    @Test
    fun changeText_sameActivity()
    {
        val typedText = "Felopater Adel"
        onView(withId(R.id.et_text)).perform(typeText(typedText))
        closeSoftKeyboard()

        onView(withId(R.id.bnt_change_text)).perform(click())

        onView(withId(R.id.tv_title)).check(matches(withText(typedText)))
    }
}