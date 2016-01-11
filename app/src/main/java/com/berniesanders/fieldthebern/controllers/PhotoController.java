package com.berniesanders.fieldthebern.controllers;

/**
 *
 */

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mortar.Presenter;
import mortar.bundler.BundleService;
import rx.functions.Action1;
import timber.log.Timber;

import static mortar.bundler.BundleService.getBundleService;

/**
 * Provides a way to coordinate with the MainActivity to request permissions
 */
public class PhotoController extends Presenter<PhotoController.Activity> {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int PICK_PHOTO = 2;
    /**
     * passes the thumbnail of the photo taken/picked or null if cancelled
     * @param bitmap
     */
    private Action1<Bitmap> onComplete;

    public interface Activity {
        AppCompatActivity getActivity();
    }

    /**
     */
    PhotoController() {
    }

    @Override
    public void onLoad(Bundle savedInstanceState) {
    }

    @Override
    public void dropView(Activity view) {
        //after this it is no longer safe to call getView()
        super.dropView(view);
    }



    public void takePhoto(Action1<Bitmap> onComplete) {
        this.onComplete = onComplete;
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getView().getActivity().getPackageManager()) != null) {
            getView().getActivity().startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void pickImage(Action1<Bitmap> onComplete) {
        this.onComplete = onComplete;
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        getView().getActivity().startActivityForResult(intent, PICK_PHOTO);
    }
    /**
     *
     */
    public void onResult(int requestCode, int resultCode, Intent data) {
        //onComplete.call();
        Timber.v("activity result...");
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            Bitmap imageBitmap = null;

            if (resultCode == android.app.Activity.RESULT_OK) {
                Bundle extras = data.getExtras();
                imageBitmap = (Bitmap) extras.get("data");

            }
            if (onComplete != null) {
                onComplete.call(imageBitmap);
            }
        } else if (requestCode == PICK_PHOTO) {
            Bitmap imageBitmap = null;

            if (resultCode == android.app.Activity.RESULT_OK) {
                Uri imageUri = data.getData();

                //TODO this code sucks
                // How is it the api for interacting with the media store
                // is still manually iterating with a cursor...?!
                long id = Long.parseLong(
                        imageUri.getLastPathSegment()
                                .substring(imageUri.getLastPathSegment().lastIndexOf(":")+1));

                imageBitmap = MediaStore.Images.Thumbnails.getThumbnail(
                        getView().getActivity().getContentResolver(),
                        id,
                        MediaStore.Images.Thumbnails.MINI_KIND,
                        (BitmapFactory.Options) null);

            }
            if (onComplete != null) {
                onComplete.call(imageBitmap);
            }
        }
    }

    /**
     * required by mortar
     */
    @Override
    protected BundleService extractBundleService(Activity activity) {
        return getBundleService(activity.getActivity());
    }

    /**
     */
    @Module
    public static class PhotoModule {

        @Provides
        @Singleton
        PhotoController providePhotoontroller() {
            return new PhotoController();
        }
    }
}