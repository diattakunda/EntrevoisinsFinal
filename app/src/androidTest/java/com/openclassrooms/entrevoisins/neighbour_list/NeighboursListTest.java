
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;



/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;

    private ListNeighbourActivity mActivity;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(Matchers.allOf(ViewMatchers.isDisplayed(),ViewMatchers.withId(R.id.list_neighbours)))
                .check(matches(hasMinimumChildCount(1)));
    }

    //when we Click on an item, ActivityDetail is launched
    @Test
    public void ClickOnItem() {

        onView(Matchers.allOf(ViewMatchers.isDisplayed(), withId(R.id.list_neighbours)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        // the second activity is launched
        onView(withId(R.id.activityDetail)).check(matches(isDisplayed()));

    }

    // TextView indicate the name on the new screen
    @Test
    public void TextViewWithName() {
        onView(Matchers.allOf(ViewMatchers.isDisplayed(), withId(R.id.list_neighbours)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        //neighbourActivityDetail is launched
        onView(withId(R.id.activityDetail));
        // TextView is correct
        onView(withId(R.id.text_image)).check(matches(withText("Caroline")));

    }


    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(Matchers.allOf(ViewMatchers.isDisplayed(),withId(R.id.list_neighbours))).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(Matchers.allOf(ViewMatchers.isDisplayed(),withId(R.id.list_neighbours)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(Matchers.allOf(ViewMatchers.isDisplayed(),withId(R.id.list_neighbours))).check(withItemCount(ITEMS_COUNT-1));
    }

    // we only see favorite neighbours in the favorite tabs
    @Test
    public void favoriteTabsIsPlayedOnTheFavList() {
        onView(Matchers.allOf(ViewMatchers.isDisplayed(),withId(R.id.list_neighbours))).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        // click on the fav Button
        onView(withId(R.id.fab)).perform(click());
        // Go back on neighbour list
        Espresso.pressBack();
        onView(Matchers.allOf(ViewMatchers.isDisplayed(),withId(R.id.list_neighbours))).perform(swipeLeft());
        // Neighbour should appear in the list of favorites neighbours
        onView(Matchers.allOf(ViewMatchers.isDisplayed(),withId(R.id.list_neighbours))).check(withItemCount(1));


    }
}
