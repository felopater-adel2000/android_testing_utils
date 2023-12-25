package com.restart.androidtesting.intents

import android.content.Intent
import android.net.Uri
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.restart.androidtesting.R
import org.hamcrest.core.AllOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class StartIntentActivityTest
{
    @get:Rule
    val activityIntentsScenario = ActivityScenarioRule(StartIntentActivity::class.java)

    val uri = Uri.parse("tel: 01277811402")

    @Test
    fun callPhone()
    {
        Intents.init()
        onView(withId(R.id.btn_call)).perform(click())

        // check Intent Has Action Call and data
        Intents.intended(AllOf.allOf(IntentMatchers.hasAction(Intent.ACTION_DIAL), IntentMatchers.hasData(uri)))
    }

    @Test
    fun test()
    {
        onView(withId(R.id.tv_result)).check(matches(withText(R.string.init_result)))

        onView(withId(R.id.btn_get_result)).perform(click())


    }
}