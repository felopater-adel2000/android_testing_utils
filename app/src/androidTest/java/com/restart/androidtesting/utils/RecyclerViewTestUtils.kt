package com.restart.androidtesting.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

class RecyclerViewTestUtils
{
    companion object{
        inline fun <reified T: RecyclerView.ViewHolder> accessViewAtPosition(pos: Int, crossinline action: (View?) -> Unit): ViewAction
        {
            val viewAction = object: ViewAction{
                override fun getDescription(): String {
                    return ""
                }

                override fun getConstraints(): Matcher<View> {
                    return CustomMatcher<View>()
                }

                override fun perform(uiController: UiController?, view: View?) {
                    action(view)
                }
            }
            return RecyclerViewActions.actionOnItemAtPosition<T>(pos, viewAction)
        }

        fun checkViewAtPosition(position: Int, itemMatcher: Matcher<View>): Matcher<View>
        {
            return object: BoundedMatcher<View, RecyclerView>(RecyclerView::class.java){
                override fun describeTo(description: Description?) {
                    itemMatcher.describeTo(description)
                }

                override fun matchesSafely(item: RecyclerView?): Boolean {
                    val viewHolder = item?.findViewHolderForAdapterPosition(position)
                    return viewHolder != null && itemMatcher.matches(viewHolder.itemView)
                }

            }
        }
    }
}