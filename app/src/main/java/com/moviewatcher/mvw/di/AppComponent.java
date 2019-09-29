package com.moviewatcher.mvw.di;

import android.app.Application;

import com.moviewatcher.mvw.App;
import com.moviewatcher.mvw.di.module.ActivityBuilderModule;
import com.moviewatcher.mvw.di.module.DatabaseModule;
import com.moviewatcher.mvw.di.module.IntentModule;
import com.moviewatcher.mvw.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityBuilderModule.class,
        NetworkModule.class,
        DatabaseModule.class,
        IntentModule.class})
@Singleton
public interface AppComponent extends AndroidInjector<App> {

    @Component.Builder
    interface Builder{

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    //void inject(App appController);

}
