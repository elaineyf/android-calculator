package com.example.calculator


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.longClick
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {
    @Rule
    @JvmField
    var mActivityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun multipleDigitsDisplay() {
        onView(withId(R.id.one)).perform(click())
        onView(withId(R.id.two)).perform(click())
        onView(withId(R.id.zero)).perform(click())
        onView(withId(R.id.three)).perform(click())
        onView(withId(R.id.bar)).check(matches(withText("1203")))
        onView(withId(R.id.plus)).perform(click())
        onView(withId(R.id.five)).perform(click())
        onView(withId(R.id.zero)).perform(click())
        onView(withId(R.id.eight)).perform(click())
        onView(withId(R.id.bar)).check(matches(withText("508")))
    }

    @Test
    fun simplePlusEquals() {
        onView(withId(R.id.two)).perform(click())
        onView(withId(R.id.bar)).check(matches(withText("2")))
        onView(withId(R.id.plus)).perform(click())
        onView(withId(R.id.five)).perform(click())
        onView(withId(R.id.bar)).check(matches(withText("5")))
        onView(withId(R.id.equal)).perform(click())
        onView(withId(R.id.bar)).check(matches(withText("7")))
    }

    @Test
    fun simpleMinusEquals() {
        onView(withId(R.id.two)).perform(click())
        onView(withId(R.id.bar)).check(matches(withText("2")))
        onView(withId(R.id.minus)).perform(click())
        onView(withId(R.id.five)).perform(click())
        onView(withId(R.id.equal)).perform(click())
        onView(withId(R.id.bar)).check(matches(withText("-3")))
    }

    @Test
    fun consecutiveOperations() {
        onView(withId(R.id.two)).perform(click())
        onView(withId(R.id.minus)).perform(click())
        onView(withId(R.id.plus)).perform(click())
        onView(withId(R.id.five)).perform(click())
        onView(withId(R.id.equal)).perform(click())
        onView(withId(R.id.bar)).check(matches(withText("7")))
    }

    @Test
    fun continuedOperations() {
        onView(withId(R.id.two)).perform(click())
        onView(withId(R.id.plus)).perform(click())
        onView(withId(R.id.five)).perform(click())
        onView(withId(R.id.minus)).perform(click())
        onView(withId(R.id.bar)).check(matches(withText("7")))
        onView(withId(R.id.equal)).perform(click())
        onView(withId(R.id.equal)).perform(click())
        onView(withId(R.id.bar)).check(matches(withText("7")))
    }

    @Test
    fun clearScreen() {
        onView(withId(R.id.two)).perform(click())
        onView(withId(R.id.plus)).perform(click())
        onView(withId(R.id.five)).perform(click())
        onView(withId(R.id.equal)).perform(click())
        onView(withId(R.id.bar)).check(matches(withText("7")))
        onView(withId(R.id.clear)).perform(click())
        onView(withId(R.id.bar)).check(matches(withText("")))
    }

    @Test
    fun longPressEqual() {
        onView(withId(R.id.two)).perform(click())
        onView(withId(R.id.plus)).perform(click())
        onView(withId(R.id.equal)).perform(longClick())
        onView(withId(R.id.bar)).check(matches(withText("")))
        onView(withId(R.id.two)).perform(click())
        onView(withId(R.id.bar)).check(matches(withText("2")))
    }

}