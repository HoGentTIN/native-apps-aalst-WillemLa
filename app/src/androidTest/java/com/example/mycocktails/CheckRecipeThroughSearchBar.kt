package com.example.mycocktails


import androidx.test.espresso.DataInteraction
import androidx.test.espresso.ViewInteraction
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent

import androidx.test.InstrumentationRegistry.getInstrumentation
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*

import com.example.mycocktails.R

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anything
import org.hamcrest.Matchers.`is`

@LargeTest
@RunWith(AndroidJUnit4::class)
class CheckRecipeThroughSearchBar {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun checkRecipeThroughSearchBar() {
        val textInputEditText = onView(
allOf(withId(R.id.SearchFragment_TextInput_SearchBar),
childAtPosition(
allOf(withId(R.id.linearLayout5),
childAtPosition(
withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
2)),
1),
isDisplayed()))
        textInputEditText.perform(click())
        
        val textInputEditText2 = onView(
allOf(withId(R.id.SearchFragment_TextInput_SearchBar),
childAtPosition(
allOf(withId(R.id.linearLayout5),
childAtPosition(
withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
2)),
1),
isDisplayed()))
        textInputEditText2.perform(replaceText("Margarita"), closeSoftKeyboard())
        
         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
Thread.sleep(160)
        
        val appCompatImageButton = onView(
allOf(withId(R.id.SearchFragment_Button_StartSearch), withContentDescription("Search"),
childAtPosition(
allOf(withId(R.id.linearLayout5),
childAtPosition(
withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
2)),
0),
isDisplayed()))
        appCompatImageButton.perform(click())
        
         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
Thread.sleep(160)
        
        val appCompatButton = onView(
allOf(withId(R.id.CocktailLayout_Button_CocktailName), withText("Margarita"),
childAtPosition(
allOf(withId(R.id.CocktailLayout_LinearLayout),
childAtPosition(
withId(R.id.CocktailLayout_CardView),
0)),
0),
isDisplayed()))
        appCompatButton.perform(click())
        
        val textView = onView(
allOf(withId(R.id.RecipeFragment_TextView_CocktailTitle), withText("Margarita"),
childAtPosition(
allOf(withId(R.id.linearLayout),
childAtPosition(
withId(R.id.ActivityMain_NavHostFragment),
0)),
0),
isDisplayed()))
        textView.check(matches(withText("Margarita")))
        
        val appCompatImageButton2 = onView(
allOf(withContentDescription("Navigate up"),
childAtPosition(
allOf(withId(R.id.action_bar),
childAtPosition(
withId(R.id.action_bar_container),
0)),
1),
isDisplayed()))
        appCompatImageButton2.perform(click())
        
        val appCompatImageButton3 = onView(
allOf(withContentDescription("Navigate up"),
childAtPosition(
allOf(withId(R.id.action_bar),
childAtPosition(
withId(R.id.action_bar_container),
0)),
1),
isDisplayed()))
        appCompatImageButton3.perform(click())
        }
    
    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
    }
