package com.restart.androidtesting

import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.restart.androidtesting.databinding.ItemRecyclerBinding
import com.restart.androidtesting.utils.RecyclerViewTestUtils
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.content.Context
import androidx.test.espresso.Espresso
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.restart.androidtesting.utils.ToastMatcher
import org.hamcrest.Matchers

@RunWith(AndroidJUnit4::class)
class RecyclerViewActivityTest
{
    @get:Rule
    val activityScenario = ActivityScenarioRule(RecyclerViewActivity::class.java)

    lateinit var context: Context

    private var position = 70

    @Before
    fun init()
    {
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun test1()
    {
        onView(withId(R.id.rv)).perform(RecyclerViewTestUtils.accessViewAtPosition<RecyclerAdapter.CustomRecyclerViewHolder>(position){
            it?.let {
                val binding = ItemRecyclerBinding.bind(it)

                binding.root.performClick()
            }
        })

        onView(withId(R.id.tv_result)).check(matches(withText(context.getString(R.string.item_text, position))))
    }

    @Test
    fun testToastMessage()
    {
        onView(withId(R.id.rv)).perform(RecyclerViewTestUtils.accessViewAtPosition<RecyclerAdapter.CustomRecyclerViewHolder>(40){
            it?.let {
                val binding = ItemRecyclerBinding.bind(it)
                binding.btn.performClick()
            }
        })

        onView(withText("position: 40")).inRoot(ToastMatcher()).check(matches(isDisplayed()))
    }
}