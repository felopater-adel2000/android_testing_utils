package com.restart.androidtesting

import android.content.Context
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.restart.androidtesting.utils.AccessView
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LoginActivityTest
{
    @get:Rule
    var androidScenario = ActivityScenarioRule(LoginActivity::class.java)

    lateinit var context: Context

    @Before
    fun init()
    {
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun test()
    {
        onView(withId(R.id.tv_login)).check(matches(isNotEnabled()))

        onView(withId(R.id.et_email)).perform(ViewActions.typeText("Hello"))
        onView(withId(R.id.et_password)).perform(ViewActions.typeText("12345678"))

        closeSoftKeyboard()
        onView(withId(R.id.tv_login)).check(matches(isNotEnabled()))
    }

    @Test
    fun test2()
    {
        onView(withId(R.id.tv_login)).check(matches(isNotEnabled()))

        onView(withId(R.id.et_email)).perform(ViewActions.typeText("Hello@World"))
        onView(withId(R.id.et_password)).perform(ViewActions.typeText("12345678"))

        closeSoftKeyboard()
        onView(withId(R.id.tv_login)).check(matches(isEnabled()))
    }
}