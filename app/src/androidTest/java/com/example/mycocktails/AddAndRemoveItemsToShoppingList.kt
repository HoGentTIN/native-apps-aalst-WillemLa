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
class AddAndRemoveItemsToShoppingList {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun addAndRemoveItemsToShoppingList() {
        pressBack()
        
        val appCompatImageButton = onView(
allOf(withContentDescription("Open navigation drawer"),
childAtPosition(
allOf(withId(R.id.action_bar),
childAtPosition(
withId(R.id.action_bar_container),
0)),
1),
isDisplayed()))
        appCompatImageButton.perform(click())
        
        val navigationMenuItemView = onView(
allOf(childAtPosition(
allOf(withId(R.id.design_navigation_view),
childAtPosition(
withId(R.id.ActivityMain_NavigationView),
0)),
3),
isDisplayed()))
        navigationMenuItemView.perform(click())
        
        val textInputEditText = onView(
allOf(withId(R.id.ShoppingFragment_TextInput_SearchBar),
childAtPosition(
allOf(withId(R.id.ShoppingFragment_LinearLayout),
childAtPosition(
withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
1)),
1),
isDisplayed()))
        textInputEditText.perform(click())
        
        val textInputEditText2 = onView(
allOf(withId(R.id.ShoppingFragment_TextInput_SearchBar),
childAtPosition(
allOf(withId(R.id.ShoppingFragment_LinearLayout),
childAtPosition(
withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
1)),
1),
isDisplayed()))
        textInputEditText2.perform(replaceText("Test1"), closeSoftKeyboard())
        
        val appCompatImageButton2 = onView(
allOf(withId(R.id.ShoppingFragment_AddItem_Button), withContentDescription("Add item to shoppinglist"),
childAtPosition(
allOf(withId(R.id.ShoppingFragment_LinearLayout),
childAtPosition(
withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
1)),
0),
isDisplayed()))
        appCompatImageButton2.perform(click())
        
        val textInputEditText3 = onView(
allOf(withId(R.id.ShoppingFragment_TextInput_SearchBar),
childAtPosition(
allOf(withId(R.id.ShoppingFragment_LinearLayout),
childAtPosition(
withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
1)),
1),
isDisplayed()))
        textInputEditText3.perform(replaceText("Test2"), closeSoftKeyboard())
        
        pressBack()
        
         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
Thread.sleep(160)
        
        val appCompatImageButton3 = onView(
allOf(withId(R.id.ShoppingFragment_AddItem_Button), withContentDescription("Add item to shoppinglist"),
childAtPosition(
allOf(withId(R.id.ShoppingFragment_LinearLayout),
childAtPosition(
withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
1)),
0),
isDisplayed()))
        appCompatImageButton3.perform(click())
        
        val appCompatButton = onView(
allOf(withId(R.id.ShoppingItemLayout_Button_ShoppingItemlName), withText("Test2"),
childAtPosition(
allOf(withId(R.id.ShoppingItemLayout_LinearLayout),
childAtPosition(
withId(R.id.ShoppingItemLayout_CardView),
0)),
0),
isDisplayed()))
        appCompatButton.perform(click())
        
        val appCompatButton2 = onView(
allOf(withClassName(`is`("androidx.appcompat.widget.AppCompatButton")), withText("Cancel"),
childAtPosition(
childAtPosition(
withId(R.id.buttonPanel),
0),
2)))
        appCompatButton2.perform(scrollTo(), click())
        
        val appCompatButton3 = onView(
allOf(withId(R.id.ShoppingItemLayout_Button_ShoppingItemlName), withText("Test2"),
childAtPosition(
allOf(withId(R.id.ShoppingItemLayout_LinearLayout),
childAtPosition(
withId(R.id.ShoppingItemLayout_CardView),
0)),
0),
isDisplayed()))
        appCompatButton3.perform(click())
        
        val appCompatButton4 = onView(
allOf(withClassName(`is`("androidx.appcompat.widget.AppCompatButton")), withText("Yes"),
childAtPosition(
childAtPosition(
withId(R.id.buttonPanel),
0),
3)))
        appCompatButton4.perform(scrollTo(), click())
        
        val appCompatButton5 = onView(
allOf(withId(R.id.ShoppingFragment_ClearList_Button), withText("Clear List"),
childAtPosition(
childAtPosition(
withClassName(`is`("android.widget.FrameLayout")),
0),
3),
isDisplayed()))
        appCompatButton5.perform(click())
        
        val appCompatButton6 = onView(
allOf(withClassName(`is`("androidx.appcompat.widget.AppCompatButton")), withText("Cancel"),
childAtPosition(
childAtPosition(
withId(R.id.buttonPanel),
0),
2)))
        appCompatButton6.perform(scrollTo(), click())
        
        val button = onView(
allOf(withId(R.id.ShoppingItemLayout_Button_ShoppingItemlName),
childAtPosition(
allOf(withId(R.id.ShoppingItemLayout_LinearLayout),
childAtPosition(
withId(R.id.ShoppingItemLayout_CardView),
0)),
0),
isDisplayed()))
        button.check(matches(isDisplayed()))
        
        val appCompatButton7 = onView(
allOf(withId(R.id.ShoppingFragment_ClearList_Button), withText("Clear List"),
childAtPosition(
childAtPosition(
withClassName(`is`("android.widget.FrameLayout")),
0),
3),
isDisplayed()))
        appCompatButton7.perform(click())
        
        val appCompatButton8 = onView(
allOf(withClassName(`is`("androidx.appcompat.widget.AppCompatButton")), withText("Yes"),
childAtPosition(
childAtPosition(
withId(R.id.buttonPanel),
0),
3)))
        appCompatButton8.perform(scrollTo(), click())
        
        val appCompatImageButton4 = onView(
allOf(withContentDescription("Navigate up"),
childAtPosition(
allOf(withId(R.id.action_bar),
childAtPosition(
withId(R.id.action_bar_container),
0)),
1),
isDisplayed()))
        appCompatImageButton4.perform(click())
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
