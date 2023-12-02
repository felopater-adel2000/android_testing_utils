package com.restart.androidtesting.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import org.hamcrest.Matcher

class AccessRecyclerViewItem
{
    companion object{
        inline fun <reified T: RecyclerView.ViewHolder> atPosition(pos: Int, crossinline action: (View?) -> Unit): ViewAction
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
    }
}