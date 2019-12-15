package com.example.mycocktails

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withClassName
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class CreateCocktail {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun createCocktail() {
        val appCompatImageButton = onView(
            allOf(
                withContentDescription("Open navigation drawer"),
                childAtPosition(
                    allOf(
                        withId(R.id.action_bar),
                        childAtPosition(
                            withId(R.id.action_bar_container),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())

        // Klik op "Add Cocktail" (2de item Navigation View)
        val navigationMenuItemView = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.design_navigation_view),
                        childAtPosition(
                            withId(R.id.ActivityMain_NavigationView),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        navigationMenuItemView.perform(click())

        // Klik op cocktailname textinput
        val textInputEditText = onView(
            allOf(
                withId(R.id.CreateCocktailFragment_TextView_CocktailName),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    2
                )
            )
        )
        textInputEditText.perform(scrollTo(), click())

        // Vul in "Test1"
        val textInputEditText2 = onView(
            allOf(
                withId(R.id.CreateCocktailFragment_TextView_CocktailName),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    2
                )
            )
        )
        textInputEditText2.perform(scrollTo(), replaceText("Test1"), closeSoftKeyboard())

        // Klik op ingredientamount1 textinput
        val textInputEditText3 = onView(
            allOf(
                withId(R.id.CreateCocktailFragment_TextInput_IngredientAmount1),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout3),
                        childAtPosition(
                            withId(R.id.CreateCocktailFragment_NestedScrollView),
                            0
                        )
                    ),
                    0
                )
            )
        )
        textInputEditText3.perform(scrollTo(), click())

        // Vul in "Amount 1"
        val textInputEditText4 = onView(
            allOf(
                withId(R.id.CreateCocktailFragment_TextInput_IngredientAmount1),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout3),
                        childAtPosition(
                            withId(R.id.CreateCocktailFragment_NestedScrollView),
                            0
                        )
                    ),
                    0
                )
            )
        )
        textInputEditText4.perform(scrollTo(), replaceText("Amount1"), closeSoftKeyboard())

        // Klik op ingredient2 textinput
        val textInputEditText5 = onView(
            allOf(
                withId(R.id.CreateCocktailFragment_TextInput_Ingredient1),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout3),
                        childAtPosition(
                            withId(R.id.CreateCocktailFragment_NestedScrollView),
                            0
                        )
                    ),
                    1
                )
            )
        )
        textInputEditText5.perform(scrollTo(), replaceText("Ingredient1"), closeSoftKeyboard())

        // Klik op de Add Ingredient Button
        val appCompatButton = onView(
            allOf(
                withId(R.id.CreateCocktailFragment_Button_AddIngredient),
                withText("Add ingredient"),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout3),
                        childAtPosition(
                            withId(R.id.CreateCocktailFragment_NestedScrollView),
                            0
                        )
                    ),
                    30
                )
            )
        )
        appCompatButton.perform(scrollTo(), click())

        // Klik op ingredientamount2 textinput en vul "Amount 2" in
        val textInputEditText12 = onView(
            allOf(
                withId(R.id.CreateCocktailFragment_TextInput_IngredientAmount2),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout3),
                        childAtPosition(
                            withId(R.id.CreateCocktailFragment_NestedScrollView),
                            0
                        )
                    ),
                    2
                )
            )
        )
        textInputEditText12.perform(scrollTo(), replaceText("Amount2"), closeSoftKeyboard())

        // Klik op ingredient2 textinput en vul "Ingredient 2" in
        val textInputEditText13 = onView(
            allOf(
                withId(R.id.CreateCocktailFragment_TextInput_Ingredient2),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout3),
                        childAtPosition(
                            withId(R.id.CreateCocktailFragment_NestedScrollView),
                            0
                        )
                    ),
                    3
                )
            )
        )
        textInputEditText13.perform(scrollTo(), replaceText("Ingredient2"), closeSoftKeyboard())

        // Klik op instructions en vul "Instructions" in
        val textInputEditText15 = onView(
            allOf(
                withId(R.id.CreateCocktailFragment_TextInput_CocktailInstructions)
            )
        )
        textInputEditText15.perform(scrollTo(), replaceText("Instruction"))

        // Klik op de "Add Cocktail" button
        val appCompatButton2 = onView(
            allOf(
                withId(R.id.CreateCocktailFragment_Button_AddCocktail)
            )
        )
        appCompatButton2.perform(scrollTo(), click())

        // Check of form leeg is na toevoegen van een cocktail

        // Is de cocktail name textfield leeg (= hint)?
        val editText = onView(
            allOf(
                withId(R.id.CreateCocktailFragment_TextView_CocktailName)
            )
        )
        editText.check(matches(withText("")))

        // Is de ingredient 1 textfield leeg (= hint)?
        val editText3 = onView(
            allOf(
                withId(R.id.CreateCocktailFragment_TextInput_Ingredient1)
            )
        )
        editText3.check(matches(withText("")))

        // Is de instructions textfield leeg (= hint)?
        val editText4 = onView(
            allOf(
                withId(R.id.CreateCocktailFragment_TextInput_CocktailInstructions)
            )
        )
        editText4.check(matches(withText("")))

        // Terug naar het menu
        val appCompatImageButton2 = onView(
            allOf(
                withContentDescription("Navigate up"),
                childAtPosition(
                    allOf(
                        withId(R.id.action_bar),
                        childAtPosition(
                            withId(R.id.action_bar_container),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageButton2.perform(click())

        // Controleer of cocktail bestaat

        // Klik op Search bar
        val textInputEditText17 = onView(
            allOf(
                withId(R.id.SearchFragment_TextInput_SearchBar),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout5),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            2
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textInputEditText17.perform(click())

        // Type "Test1" in de searchbar
        val textInputEditText18 = onView(
            allOf(
                withId(R.id.SearchFragment_TextInput_SearchBar),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout5),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            2
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textInputEditText18.perform(replaceText("Test1"), closeSoftKeyboard())

        // Klik op searchbutton?
        val appCompatImageButton3 = onView(
            allOf(
                withId(R.id.SearchFragment_Button_StartSearch), withContentDescription("Search"),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout5),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            2
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatImageButton3.perform(click())

        // Wachten op cocktails
        Thread.sleep(5000)

        // Klik op Test1
        val appCompatButton3 = onView(
            allOf(
                withId(R.id.CocktailLayout_Button_CocktailName), withText("Test1"),
                childAtPosition(
                    allOf(
                        withId(R.id.CocktailLayout_LinearLayout),
                        childAtPosition(
                            withId(R.id.CocktailLayout_CardView),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatButton3.perform(click())

        // Terug naar het menu
        val appCompatImageButton4 = onView(
            allOf(
                withContentDescription("Navigate up"),
                childAtPosition(
                    allOf(
                        withId(R.id.action_bar),
                        childAtPosition(
                            withId(R.id.action_bar_container),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageButton4.perform(click())

        val appCompatImageButton5 = onView(
            allOf(
                withContentDescription("Navigate up"),
                childAtPosition(
                    allOf(
                        withId(R.id.action_bar),
                        childAtPosition(
                            withId(R.id.action_bar_container),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageButton5.perform(click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>,
        position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent) &&
                        view == parent.getChildAt(position)
            }
        }
    }
}
