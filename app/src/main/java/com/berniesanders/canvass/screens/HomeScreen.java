package com.berniesanders.canvass.screens;

import android.os.Bundle;

import com.berniesanders.canvass.R;
import com.berniesanders.canvass.annotations.Layout;
import com.berniesanders.canvass.dagger.FtbScreenScope;
import com.berniesanders.canvass.controllers.ActionBarController;
import com.berniesanders.canvass.controllers.ActionBarService;
import com.berniesanders.canvass.mortar.FlowPathBase;
import com.berniesanders.canvass.views.HomeView;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;
import flow.Flow;
import mortar.ViewPresenter;
import timber.log.Timber;

/**
 */
@Layout(R.layout.screen_home)
public class HomeScreen extends FlowPathBase {
    /**
     */
    public HomeScreen() {
    }

    /**
     */
    @Override
    public Object createComponent() {
        return DaggerHomeScreen_Component
                .builder()
                .build();
    }

    /**
     */
    @Override
    public String getScopeName() {
        // note someData.hashCode() makes the screen unique
        return HomeScreen.class.getName();
    }

    /**
     */
    @FtbScreenScope
    @dagger.Component()
    public interface Component {
        /**
         */
        void inject(HomeView t);

    }

    @FtbScreenScope
    static public class Presenter extends ViewPresenter<HomeView> {

        @BindString(R.string.app_name) String screenTitle;
        /**
         */
        @Inject
        Presenter() {
        }

        /**
         */
        @Override
        protected void onLoad(Bundle savedInstanceState) {
            Timber.v("onLoad");
            ButterKnife.bind(this, getView());
            ActionBarService
                    .getActionbarController(getView())
                    .showToolbar()
                    .closeAppbar()
                    .unlockDrawer()
                    .setConfig(new ActionBarController.Config(screenTitle, null));
        }

        /**
         * called on rotation only
         */
        @Override
        protected void onSave(Bundle outState) {
        }


        /**
         */
        @Override
        public void dropView(HomeView view) {
            super.dropView(view);
            ButterKnife.unbind(this);
        }

        @OnClick(R.id.screen_home_canvass)
        void onCanvassClicked() {
            Flow.get(getView().getContext()).set(new MapScreen());
        }

        @OnClick(R.id.screen_home_issues)
        void onIssuesClicked() {
            Flow.get(getView().getContext()).set(new Main());
        }

    }
}
