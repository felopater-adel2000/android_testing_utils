package com.restart.androidtesting.drawer

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.DrawerMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.restart.androidtesting.R
import com.restart.androidtesting.utils.ToastMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DrawerActivityTest
{
    @get:Rule
    var activityScenario = ActivityScenarioRule(DrawerActivity::class.java)

    @Test
    fun openDrawer()
    {

        Espresso.onView(ViewMatchers.withId(R.id.drawer_layout)).perform(DrawerActions.open())
            .check(ViewAssertions.matches(DrawerMatchers.isOpen()))
    }


    @Test
    fun checkToast1()
    {
        Espresso.onView(ViewMatchers.withId(R.id.drawer_layout)).perform(DrawerActions.open()).check(ViewAssertions.matches(DrawerMatchers.isOpen()))

        Espresso.onView(ViewMatchers.withText(R.string.option1)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withText(R.string.option1)).inRoot(ToastMatcher()).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.drawer_layout)).check(ViewAssertions.matches(DrawerMatchers.isClosed()))
    }
}