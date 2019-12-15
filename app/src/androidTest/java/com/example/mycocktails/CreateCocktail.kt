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
class CreateCocktail {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun createCocktail() {
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
        
        pressBack()
        
        val navigationMenuItemView = onView(
allOf(childAtPosition(
allOf(withId(R.id.design_navigation_view),
childAtPosition(
withId(R.id.ActivityMain_NavigationView),
0)),
2),
isDisplayed()))
        navigationMenuItemView.perform(click())
        
        val textInputEditText = onView(
allOf(withId(R.id.CreateCocktailFragment_TextView_CocktailName),
childAtPosition(
childAtPosition(
withClassName(`is`("android.widget.ScrollView")),
0),
2)))
        textInputEditText.perform(scrollTo(), click())
        
        val textInputEditText2 = onView(
allOf(withId(R.id.CreateCocktailFragment_TextView_CocktailName),
childAtPosition(
childAtPosition(
withClassName(`is`("android.widget.ScrollView")),
0),
2)))
        textInputEditText2.perform(scrollTo(), replaceText("Test1"), closeSoftKeyboard())
        
         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
Thread.sleep(700)
        
        val textInputEditText3 = onView(
allOf(withId(R.id.CreateCocktailFragment_TextInput_IngredientAmount1),
childAtPosition(
allOf(withId(R.id.linearLayout3),
childAtPosition(
withId(R.id.CreateCocktailFragment_NestedScrollView),
0)),
0)))
        textInputEditText3.perform(scrollTo(), click())
        
        val textInputEditText4 = onView(
allOf(withId(R.id.CreateCocktailFragment_TextInput_IngredientAmount1),
childAtPosition(
allOf(withId(R.id.linearLayout3),
childAtPosition(
withId(R.id.CreateCocktailFragment_NestedScrollView),
0)),
0)))
        textInputEditText4.perform(scrollTo(), replaceText("Amount1"), closeSoftKeyboard())
        
        pressBack()
        
        val textInputEditText5 = onView(
allOf(withId(R.id.CreateCocktailFragment_TextInput_Ingredient1),
childAtPosition(
allOf(withId(R.id.linearLayout3),
childAtPosition(
withId(R.id.CreateCocktailFragment_NestedScrollView),
0)),
1)))
        textInputEditText5.perform(scrollTo(), replaceText("Ingrzdient"), closeSoftKeyboard())
        
         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
Thread.sleep(700)
        
        val textInputEditText6 = onView(
allOf(withId(R.id.CreateCocktailFragment_TextInput_Ingredient1), withText("Ingrzdient"),
childAtPosition(
allOf(withId(R.id.linearLayout3),
childAtPosition(
withId(R.id.CreateCocktailFragment_NestedScrollView),
0)),
1)))
        textInputEditText6.perform(scrollTo(), replaceText("Ingrzdient1"))
        
        val textInputEditText7 = onView(
allOf(withId(R.id.CreateCocktailFragment_TextInput_Ingredient1), withText("Ingrzdient1"),
childAtPosition(
allOf(withId(R.id.linearLayout3),
childAtPosition(
withId(R.id.CreateCocktailFragment_NestedScrollView),
0)),
1),
isDisplayed()))
        textInputEditText7.perform(closeSoftKeyboard())
        
        val textInputEditText8 = onView(
allOf(withId(R.id.CreateCocktailFragment_TextInput_Ingredient1), withText("Ingrzdient1"),
childAtPosition(
allOf(withId(R.id.linearLayout3),
childAtPosition(
withId(R.id.CreateCocktailFragment_NestedScrollView),
0)),
1)))
        textInputEditText8.perform(scrollTo(), click())
        
        val textInputEditText9 = onView(
allOf(withId(R.id.CreateCocktailFragment_TextInput_Ingredient1), withText("Ingrzdient1"),
childAtPosition(
allOf(withId(R.id.linearLayout3),
childAtPosition(
withId(R.id.CreateCocktailFragment_NestedScrollView),
0)),
1)))
        textInputEditText9.perform(scrollTo(), click())
        
        val textInputEditText10 = onView(
allOf(withId(R.id.CreateCocktailFragment_TextInput_Ingredient1), withText("Ingrzdient1"),
childAtPosition(
allOf(withId(R.id.linearLayout3),
childAtPosition(
withId(R.id.CreateCocktailFragment_NestedScrollView),
0)),
1)))
        textInputEditText10.perform(scrollTo(), replaceText("Ingredient1"))
        
        val textInputEditText11 = onView(
allOf(withId(R.id.CreateCocktailFragment_TextInput_Ingredient1), withText("Ingredient1"),
childAtPosition(
allOf(withId(R.id.linearLayout3),
childAtPosition(
withId(R.id.CreateCocktailFragment_NestedScrollView),
0)),
1),
isDisplayed()))
        textInputEditText11.perform(closeSoftKeyboard())
        
        pressBack()
        
        val appCompatButton = onView(
allOf(withId(R.id.CreateCocktailFragment_Button_AddIngredient), withText("Add ingredient"),
childAtPosition(
allOf(withId(R.id.linearLayout3),
childAtPosition(
withId(R.id.CreateCocktailFragment_NestedScrollView),
0)),
30)))
        appCompatButton.perform(scrollTo(), click())
        
        val textInputEditText12 = onView(
allOf(withId(R.id.CreateCocktailFragment_TextInput_IngredientAmount2),
childAtPosition(
allOf(withId(R.id.linearLayout3),
childAtPosition(
withId(R.id.CreateCocktailFragment_NestedScrollView),
0)),
2)))
        textInputEditText12.perform(scrollTo(), replaceText("Amount2"), closeSoftKeyboard())
        
        pressBack()
        
        val textInputEditText13 = onView(
allOf(withId(R.id.CreateCocktailFragment_TextInput_Ingredient2),
childAtPosition(
allOf(withId(R.id.linearLayout3),
childAtPosition(
withId(R.id.CreateCocktailFragment_NestedScrollView),
0)),
3)))
        textInputEditText13.perform(scrollTo(), replaceText("Ingredient2"), closeSoftKeyboard())
        
        pressBack()
        
         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
Thread.sleep(700)
        
        pressBack()
        
        val textInputEditText14 = onView(
allOf(withId(R.id.CreateCocktailFragment_TextInput_CocktailInstructions),
childAtPosition(
childAtPosition(
withClassName(`is`("android.widget.ScrollView")),
0),
5)))
        textInputEditText14.perform(scrollTo(), replaceText("Instr"), closeSoftKeyboard())
        
        pressBack()
        
        val textInputEditText15 = onView(
allOf(withId(R.id.CreateCocktailFragment_TextInput_CocktailInstructions), withText("Instr"),
childAtPosition(
childAtPosition(
withClassName(`is`("android.widget.ScrollView")),
0),
5)))
        textInputEditText15.perform(scrollTo(), replaceText("Instruvtions"))
        
        val textInputEditText16 = onView(
allOf(withId(R.id.CreateCocktailFragment_TextInput_CocktailInstructions), withText("Instruvtions"),
childAtPosition(
childAtPosition(
withClassName(`is`("android.widget.ScrollView")),
0),
5),
isDisplayed()))
        textInputEditText16.perform(closeSoftKeyboard())
        
        val appCompatButton2 = onView(
allOf(withId(R.id.CreateCocktailFragment_Button_AddCocktail), withText("Add Cocktail"),
childAtPosition(
childAtPosition(
withClassName(`is`("android.widget.ScrollView")),
0),
4)))
        appCompatButton2.perform(scrollTo(), click())
        
        val editText = onView(
allOf(withId(R.id.CreateCocktailFragment_TextView_CocktailName), withText("Cocktail name"),
childAtPosition(
childAtPosition(
IsInstanceOf.instanceOf(android.widget.ScrollView::class.java),
0),
1),
isDisplayed()))
        editText.check(matches(withText("Cocktail name")))
        
        val editText2 = onView(
allOf(withId(R.id.CreateCocktailFragment_TextInput_IngredientAmount1), withText("Amount 1"),
childAtPosition(
allOf(withId(R.id.linearLayout3),
childAtPosition(
withId(R.id.CreateCocktailFragment_NestedScrollView),
0)),
0),
isDisplayed()))
        editText2.check(matches(withText("Amount 1")))
        
        val editText3 = onView(
allOf(withId(R.id.CreateCocktailFragment_TextInput_Ingredient1), withText("Ingredient 1"),
childAtPosition(
allOf(withId(R.id.linearLayout3),
childAtPosition(
withId(R.id.CreateCocktailFragment_NestedScrollView),
0)),
1),
isDisplayed()))
        editText3.check(matches(withText("Ingredient 1")))
        
        val editText4 = onView(
allOf(withId(R.id.CreateCocktailFragment_TextInput_CocktailInstructions), withText("Instructions"),
childAtPosition(
childAtPosition(
IsInstanceOf.instanceOf(android.widget.ScrollView::class.java),
0),
3),
isDisplayed()))
        editText4.check(matches(withText("Instructions")))
        
         // Added a sleep statement to match the app's execution delay.
 // The recommended way to handle such scenarios is to use Espresso idling resources:
  // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
Thread.sleep(700)
        
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
        
        val textInputEditText17 = onView(
allOf(withId(R.id.SearchFragment_TextInput_SearchBar),
childAtPosition(
allOf(withId(R.id.linearLayout5),
childAtPosition(
withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
2)),
1),
isDisplayed()))
        textInputEditText17.perform(click())
        
        val textInputEditText18 = onView(
allOf(withId(R.id.SearchFragment_TextInput_SearchBar),
childAtPosition(
allOf(withId(R.id.linearLayout5),
childAtPosition(
withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
2)),
1),
isDisplayed()))
        textInputEditText18.perform(replaceText("Test1"), closeSoftKeyboard())
        
        val appCompatImageButton3 = onView(
allOf(withId(R.id.SearchFragment_Button_StartSearch), withContentDescription("Search"),
childAtPosition(
allOf(withId(R.id.linearLayout5),
childAtPosition(
withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
2)),
0),
isDisplayed()))
        appCompatImageButton3.perform(click())
        
        val appCompatButton3 = onView(
allOf(withId(R.id.CocktailLayout_Button_CocktailName), withText("Test1"),
childAtPosition(
allOf(withId(R.id.CocktailLayout_LinearLayout),
childAtPosition(
withId(R.id.CocktailLayout_CardView),
0)),
0),
isDisplayed()))
        appCompatButton3.perform(click())
        
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
        
        val appCompatImageButton5 = onView(
allOf(withContentDescription("Navigate up"),
childAtPosition(
allOf(withId(R.id.action_bar),
childAtPosition(
withId(R.id.action_bar_container),
0)),
1),
isDisplayed()))
        appCompatImageButton5.perform(click())
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
