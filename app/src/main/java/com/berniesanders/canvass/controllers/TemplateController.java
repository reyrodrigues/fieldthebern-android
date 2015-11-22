package com.berniesanders.canvass.controllers;

/**
 *
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mortar.Presenter;
import mortar.bundler.BundleService;
import timber.log.Timber;

import static mortar.bundler.BundleService.getBundleService;

/**
 * Provides a route to coordinate facebook auth with the MainActivity
 */
public class TemplateController extends Presenter<TemplateController.Activity> {

    public interface Activity {
        AppCompatActivity getActivity();
    }

    // package private
    // only instantiated via the TemplateControllerModule dagger module inner class below
    TemplateController() {
    }

    @Override
    public void onLoad(Bundle savedInstanceState) {
        Timber.v("onLoad()");
        //you can safely call getView() to get the activity here
    }

    @Override
    public void dropView(Activity view) {
        super.dropView(view);
        //after this it is no longer safe to call getView()
    }


    //required by mortar
    @Override
    protected BundleService extractBundleService(Activity activity) {
        return getBundleService(activity.getActivity());
    }


    //used to inject this singleton present onto the Activity
    @Module
    public static class TemplateControllerModule {

        @Provides
        @Singleton
        TemplateController provideTemplateController() {
            return new TemplateController();
        }
    }
}