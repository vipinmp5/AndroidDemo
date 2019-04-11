package com.mytaxi.android_demo.pages;

import android.support.test.espresso.ViewInteraction;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;


import com.mytaxi.android_demo.R;
import android.support.test.espresso.Espresso;

import android.support.test.espresso.IdlingPolicies;
import android.support.test.espresso.IdlingResource;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import org.hamcrest.*;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.is;
import java.util.concurrent.TimeUnit;
import android.text.format.DateUtils;


/**
 * Created by vipin on 10/04/2019.
 */

public class LoginPage extends BasePage {
     ViewInteraction userName;
     ViewInteraction password;
     ViewInteraction loginButton;
     ViewInteraction loginValidation;

   public LoginPage(){
        userName=onView(
                allOf(withId(R.id.edt_username),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.design.widget.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        password=onView(
                allOf(withId(R.id.edt_password),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.design.widget.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        loginButton=onView(
                allOf(withId(R.id.btn_login), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));

       loginValidation=onView(allOf(withId(android.R.id.content), withText("Login failed")));
    }

   public void loginToApplication(String userNameText, String passwordText){
       onView(isRoot()).perform(waitId(R.id.edt_username, 5000));
       userName.perform(replaceText(userNameText));
       password.perform(replaceText(passwordText), closeSoftKeyboard());
       loginButton.perform(click());
   }

   public void verifyLoginValidationDisplayed(){
       loginValidation.check(matches(isDisplayed()));
   }

    public void verifyLogout() {
        onView(isRoot()).perform(waitId(R.id.edt_username, 5000));
        userName.check(matches(isDisplayed()));
        password.check(matches(isDisplayed()));
        loginButton.check(matches(isDisplayed()));

    }



}
