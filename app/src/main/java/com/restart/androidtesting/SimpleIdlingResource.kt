package com.restart.androidtesting

import androidx.test.espresso.IdlingResource
import java.util.concurrent.atomic.AtomicBoolean

class SimpleIdlingResource : IdlingResource
{
    private var mCallback: IdlingResource.ResourceCallback? = null

    private val mIsIdleNow = AtomicBoolean(true)
    override fun getName(): String {
        return this.javaClass.name
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        mCallback = callback
    }

    override fun isIdleNow(): Boolean {
        return mIsIdleNow.get()
    }

    fun setIdleState(isIdleNow: Boolean)
    {
        mIsIdleNow.set(isIdleNow)
        if(isIdleNow && mCallback != null)
        {
            mCallback?.onTransitionToIdle()
        }
    }
}