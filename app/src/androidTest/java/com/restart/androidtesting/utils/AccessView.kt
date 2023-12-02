package com.restart.androidtesting.utils

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import java.lang.Exception

class AccessView {
    companion object{

        inline fun <reified T : View> withId(id: Int, crossinline action: (T) -> Unit)
        {
            Espresso.onView(ViewMatchers.withId(id)).check { view, noViewFoundException ->
                if(view is T)
                {
                    action(view)
                }
                else
                {
                    throw Exception("Can not Cast View With id to ${T::class.java.name}")
                }
            }
        }

        inline fun <reified T : View> withText(text: String, crossinline acction: (T) -> Unit)
        {
            Espresso.onView(ViewMatchers.withText(text)).check { view, noViewFoundException ->
                if(view is T)
                {
                    acction(view)
                }
                else
                {
                    throw Exception("Can not Cast View With Text to ${T::class.java.name}")
                }
            }
        }

        inline fun <reified T : View> withText(textId: Int, crossinline action: (T) -> Unit)
        {
            Espresso.onView(ViewMatchers.withText(textId)).check { view, noViewFoundException ->
                if(view is T)
                {
                    action(view)
                }
                else
                {
                    throw Exception("Can not Cast View With id to ${T::class.java.name}")
                }
            }
        }
    }
}