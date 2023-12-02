package com.restart.androidtesting.utils

import org.hamcrest.Description
import org.hamcrest.Matcher

class CustomMatcher<T> : Matcher<T>
{
    override fun describeTo(description: Description?) {

    }

    override fun matches(actual: Any?): Boolean {
        return true
    }

    override fun describeMismatch(actual: Any?, mismatchDescription: Description?) {
    }

    override fun _dont_implement_Matcher___instead_extend_BaseMatcher_() {

    }
}