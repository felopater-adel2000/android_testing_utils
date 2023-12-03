package com.restart.androidtesting.utils

import android.view.View
import android.widget.TextView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

class MyBoundedMatcher
{
    companion object{
        fun withIdAndText(idMatcher: Matcher<Int>, textMatcher: Matcher<String>): Matcher<View>
        {
            return object: BoundedMatcher<View, TextView>(TextView::class.java){
                override fun describeTo(description: Description?) {
                    description?.appendText("Error TextView")
                    idMatcher.describeTo(description)
                    textMatcher.describeTo(description)
                }

                override fun matchesSafely(item: TextView?): Boolean {
                    return textMatcher.matches(item?.text.toString()) && idMatcher.matches(item?.id)
                }

            }
        }
    }
}