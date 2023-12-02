package com.restart.androidtesting

import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.restart.androidtesting.utils.AccessView
import org.hamcrest.core.Is.`is`
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest
{
    @get:Rule
    val activityScenario = ActivityScenarioRule<MainActivity>(MainActivity::class.java)

    @Test
    fun clickOnBtnAndCheckTest()
    {
        onView(withId(R.id.iv_arrow_up)).perform(click()).check(matches(withText("Increment")))
        onView(withId(R.id.tv_text)).check(matches(withText("displayNumber: 1")))
    }

    @Test
    fun clickOnBtnAndCheckTextWithAccessView()
    {
        AccessView.withId<ImageView>(R.id.iv_arrow_up){accessButton -> accessButton.performClick()}

        AccessView.withId<TextView>(R.id.tv_text) { accessTextView ->
            val displayedText = accessTextView.text.toString()
             assertThat(displayedText, `is`("displayNumber: 1"))
        }
    }

    @Test
    fun clickOnBtnWithTextAndCheckTextViewWithAccessView()
    {
        /** This Line Throw Exception Because this id not Button but ImageView **/
        AccessView.withId<Button>(R.id.iv_arrow_up){accessButton -> accessButton.performClick()}
    }
}