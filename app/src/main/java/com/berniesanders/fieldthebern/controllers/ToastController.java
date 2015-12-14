package com.berniesanders.fieldthebern.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.berniesanders.fieldthebern.R;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mortar.Presenter;
import mortar.bundler.BundleService;

import static mortar.bundler.BundleService.getBundleService;

/**
 *
 */
public class ToastController extends Presenter<ToastController.Activity> {

    public interface Activity {
        AppCompatActivity getActivity();
    }

    ToastController() {

    }

    public void bern(String text) {
        //Toast.makeText(getView().getActivity(), text, Toast.LENGTH_LONG).show();

        View layout = LayoutInflater
                .from(getView().getActivity())
                .inflate(R.layout.toast,
                        (ViewGroup) getView().getActivity().findViewById(R.id.toast_layout_root),
                        false
                        );

        ImageView image = (ImageView) layout.findViewById(R.id.image);
        image.setImageResource(R.drawable.ic_info_outline_white_24dp);
        TextView textView = (TextView) layout.findViewById(R.id.text);
        textView.setText(text);

        Toast toast = new Toast(getView().getActivity());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    @Override
    protected void onLoad(Bundle savedInstanceState) {
        super.onLoad(savedInstanceState);
    }

    @Override
    public void dropView(Activity view) {
        super.dropView(view);
    }

    @Override
    protected BundleService extractBundleService(Activity activity) {
        return getBundleService(activity.getActivity());
    }


    @Module
    public static class ToastModule {

        @Provides
        @Singleton
        ToastController provideToastController() {
            return new ToastController();
        }
    }
}
