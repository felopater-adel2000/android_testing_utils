package com.restart.androidtesting

import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource
{
    private val RESOURCE_VAL = "GLOBAL"

    private val countingIdlingResource = CountingIdlingResource(RESOURCE_VAL, true)

    fun increment() = countingIdlingResource.increment()

    fun decrement() = countingIdlingResource.decrement()

    fun getIdlingResource() = countingIdlingResource
}