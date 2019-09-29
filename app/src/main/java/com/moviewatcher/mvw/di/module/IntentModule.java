package com.moviewatcher.mvw.di.module;

import android.app.Application;

import com.moviewatcher.mvw.utils.IntentManager;

import dagger.Module;
import dagger.Provides;

@Module
public class IntentModule {

    @Provides
    IntentManager provideIntent(Application application){
        return new IntentManager(application);
    }
}
