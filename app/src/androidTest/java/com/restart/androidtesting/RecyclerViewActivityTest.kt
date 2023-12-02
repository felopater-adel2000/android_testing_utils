package com.restart.androidtesting

import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.restart.androidtesting.databinding.ItemRecyclerBinding
import com.restart.androidtesting.utils.AccessRecyclerViewItem
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.content.Context

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
        onView(withId(R.id.rv)).perform(AccessRecyclerViewItem.atPosition<RecyclerAdapter.CustomRecyclerViewHolder>(position){
            it?.let {
                val binding = ItemRecyclerBinding.bind(it)

                binding.root.performClick()
            }
        })

        onView(withId(R.id.tv_result)).check(matches(withText(context.getString(R.string.item_text, position))))
    }

}