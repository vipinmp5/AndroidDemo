package com.mytaxi.android_demo.pages;

import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.RootMatchers;
import android.view.View;
import android.widget.ListView;

import com.mytaxi.android_demo.R;
import com.mytaxi.android_demo.activities.MainActivity;
import com.mytaxi.android_demo.adapters.DriverAdapter;

import org.hamcrest.CoreMatchers;

import java.security.spec.ECField;
import java.util.Map;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.openLinkWithText;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasValue;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matcher.*;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Created by vipin on 10/04/2019.
 */

public class SearchPage extends BasePage {

    ViewInteraction searchBox;
    ViewInteraction openNavigation;
    ViewInteraction logOutButton;
    ViewInteraction searchData;

    public SearchPage(){
        searchBox =    onView(
                allOf(withId(R.id.textSearch),
                        childAtPosition(
                                allOf(withId(R.id.searchContainer),
                                        childAtPosition(
                                                withClassName(is("android.support.design.widget.CoordinatorLayout")),
                                                1)),
                                0),
                        isDisplayed()));;

        openNavigation = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withClassName(is("android.support.design.widget.AppBarLayout")),
                                                0)),
                                1),
                        isDisplayed()));

        logOutButton = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0)),
                        1),
                        isDisplayed()));

    }

    public ViewInteraction getSearchData(String driverName){
         return   onView(withText(driverName)).inRoot(RootMatchers.isPlatformPopup()).perform(click());

    }

    public void verifythatSearchBoxDisplayed(){
        onView(isRoot()).perform(waitId(R.id.textSearch, 5000));
        searchBox.check(matches(CoreMatchers.notNullValue()))
                .check(matches(isDisplayed()));
    }
    public boolean verifySearchDisplayed(){
        try{
            searchBox.check(matches(CoreMatchers.notNullValue()))
                    .check(matches(isDisplayed()));
            return  true;
        } catch(Exception e){
            return false;
        }

    }

    public void logOut()  {
        onView(isRoot()).perform(waitId(R.id.toolbar, 5000));
        openNavigation.perform(click());
        logOutButton.perform(click());
    }

    public void searchText(String inputText,String driverName) throws InterruptedException{
        onView(isRoot()).perform(waitId(R.id.textSearch, 5000));
        searchBox.perform(click());
        searchBox.perform(replaceText(inputText), closeSoftKeyboard());
        Thread.sleep(5000);
        searchData=getSearchData(driverName);

    }


}
