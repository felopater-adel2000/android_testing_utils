package com.restart.androidtesting

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.restart.androidtesting.utils.AccessView
import com.restart.androidtesting.utils.MyBoundedMatcher
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

    @Test
    fun testBackground()
    {
        AccessView.withId<TextView>(R.id.tv_login){
            val currentBackground = it.background as GradientDrawable

            val expectedBackground = ContextCompat.getDrawable(context, R.drawable.background_inactive_button) as GradientDrawable

            assertThat(currentBackground.color , `is`(expectedBackground.color))
            assertThat(currentBackground.cornerRadius , `is`(expectedBackground.cornerRadius))
        }
    }

    @Test
    fun testWithMyBoundedMatcher()
    {
        onView(MyBoundedMatcher.withIdAndText(
            `is`(R.id.tv_login),
            `is`(context.getString(R.string.login))
        )).check { view, noViewFoundException ->

            val currentBackground = view.background as GradientDrawable
            val expectedBackground = ContextCompat.getDrawable(context, R.drawable.background_inactive_button) as GradientDrawable

            assertThat(currentBackground.color , `is`(expectedBackground.color))
        }
    }
}