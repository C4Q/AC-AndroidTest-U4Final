package nyc.c4q.androidtest_unit4final;

import android.graphics.Color;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.PositionAssertions.isBelow;
import static android.support.test.espresso.assertion.PositionAssertions.isCompletelyAbove;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.hasTextColor;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by justiceo on 1/8/18.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule(MainActivity.class);

    @Test
    public void activityLoads() {
        onView(withText("AndroidTest-Unit4Final")).check(matches(isDisplayed()));
    }

    @Test
    public void textsAreDisplayed() {
        onView(withText("blue")).check(matches(isDisplayed()));
        onView(withText("black")).check(matches(isDisplayed()));
    }

    /**
     * If this test is failing for you after implementing json, check that your network on the avd is working
     */
    @Test
    public void textsAreColored() {
        onView(withText("blue")).check(matches(hasTextColor(R.color.blue)));
        onView(withText("purple")).check(matches(hasTextColor(R.color.purple)));
    }

    @Test
    public void textsAreOrdered() {
        onView(withText("black")).check(isCompletelyAbove(withText("blue")));
        onView(withText("purple")).check(isCompletelyAbove(withText("red")));
    }

    @Test
    public void remoteDataIsLoaded() {
        // should test that dict contain items
        MainActivity mainActivity = mActivityRule.getActivity();
        assertTrue(mainActivity.colorDict.containsKey("aliceblue"));
    }
    @Test
    public void remoteDataIsParsedCorrectly() {
        MainActivity mainActivity = mActivityRule.getActivity();
        assertEquals(mainActivity.colorDict.get("aliceblue"), "#f0f8ff");
    }

    @Test
    public void nothingWasAddedRemovedFromList() {
        MainActivity mainActivity = mActivityRule.getActivity();
        assertEquals("Nothing should be added or removed from `colorsList`", mainActivity.colorsList.size(), 8);
    }

    // fragments test
    @Test
    public void fragmentIsDisplayedByDefault() {
        onView(withText(R.string.app_info)).check(matches(isDisplayed()));
    }

    @Test
    public void fragmentButtonIsDisplayed() {
        onView(withText("More")).check(matches(isDisplayed()));
    }

    @Test
    public void clickFragmentButtonShowsMore() {
        onView(withText("More")).perform(click());
        onView(withText(R.string.more_info)).check(matches(isDisplayed()));
    }

    @Test
    public void clickFragmentButtonHidesButton() {
        onView(withText("More")).perform(click()).check(matches(not(isDisplayed())));
    }

    // menu test
    @Test
    public void menuItemIsDisplayed() {
        onView(withText("Info")).check(matches(isDisplayed()));
    }

    @Test
    public void clickMenuItemHidesFrag() {
        onView(withText("Info")).perform(click()).check(matches(not(withText(R.string.app_info))));
    }


    @Test
    public void clickMenuItemAgainShowFrag() {
        onView(withText("Info")).perform(click());
        onView(withText(R.string.app_info)).check(matches(not(isDisplayed())));

        onView(withText("Info")).perform(click());
        onView(withText(R.string.app_info)).check(matches(isDisplayed()));
    }

    // color name click
    @Test
    public void clickOnNameShowsToast() {
        MainActivity mainActivity = mActivityRule.getActivity();
        onView(withText("blue")).perform(click());
        onView(withText("blue has a HEX value of #0000ff"))
                .inRoot(withDecorView(not(is(mainActivity.getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }
}
