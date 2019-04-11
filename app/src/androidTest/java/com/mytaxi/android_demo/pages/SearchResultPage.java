package com.mytaxi.android_demo.pages;

import android.support.test.espresso.ViewInteraction;
import android.view.View;

import com.mytaxi.android_demo.R;

import org.hamcrest.CoreMatchers;
import org.hamcrest.core.IsInstanceOf;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by vipin on 11/04/2019.
 */

public class SearchResultPage extends BasePage {
    ViewInteraction driverName;
    ViewInteraction callButton;

    public SearchResultPage(){

        callButton=onView(
                allOf(withId(R.id.fab),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
    }

    public void verifyElementExist(String driverNameText){
        onView(isRoot()).perform(waitId(R.id.textViewDriverName, 5000));
        driverName = onView(allOf(withId(R.id.textViewDriverName))).check(matches(CoreMatchers.notNullValue()))
                        .check(matches(isDisplayed()));
        driverName.check(matches(withText("Sarah Scott")));
    }

    public  void clickCallButton(){
        callButton.perform(click());
    }

}
