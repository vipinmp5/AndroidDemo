package com.mytaxi.android_demo;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.support.test.espresso.IdlingPolicies;

import com.mytaxi.android_demo.activities.AuthenticationActivity;
import com.mytaxi.android_demo.activities.MainActivity;
import com.mytaxi.android_demo.pages.LoginPage;
import com.mytaxi.android_demo.pages.SearchPage;
import com.mytaxi.android_demo.pages.SearchResultPage;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static com.mytaxi.android_demo.App.getApplicationContext;
import static java.lang.Thread.*;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class AndroidDemoE2ETest {

    LoginPage objLogin;
    SearchPage objSearch;
    SearchResultPage objSearchResult;




    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);


    private IdlingResource mIdlingResource;

    @Before
    public void BeforeTest(){
        //clearPreferences();
        clearAllData();
        IdlingPolicies.setMasterPolicyTimeout(100, TimeUnit.SECONDS);
        IdlingPolicies.setIdlingResourceTimeout(100, TimeUnit.SECONDS);
        objLogin = new LoginPage();
        objSearch = new SearchPage();
        objSearchResult =  new SearchResultPage();
        if(objSearch.verifySearchDisplayed()){
            objSearch.logOut();
        }
    }

    @Test
    public void verifyValidLoginWithLogOut() throws InterruptedException {

        objLogin.loginToApplication("crazydog335","venture");
        Thread.sleep(5000);
        objSearch.verifythatSearchBoxDisplayed();
        Thread.sleep(5000);
        objSearch.logOut();
        Thread.sleep(5000);
        objLogin.verifyLogout();
    }

    @Test
    public void verifySearchFunctionality() throws InterruptedException {

        objLogin.loginToApplication("crazydog335","venture");
        Thread.sleep(5000);
        objSearch.verifythatSearchBoxDisplayed();
        Thread.sleep(5000);
        objSearch.searchText("sa","Sarah Scott");
        Thread.sleep(5000);
        objSearchResult.verifyElementExist("Sarah Scott");
        objSearchResult.clickCallButton();
       // objSearchResult.verifyDialerNumber("(413)868-2228");
    }

    @After
    public void TearDown(){
        clearAllData();
    }

    public void clearAllData(){
        Context appContext = InstrumentationRegistry.getTargetContext();
        SharedPreferences mSharedPref = appContext.getSharedPreferences("MytaxiPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.remove("username");
        editor.remove("salt");
        editor.remove("sha256");
        editor.apply();
    }



}
