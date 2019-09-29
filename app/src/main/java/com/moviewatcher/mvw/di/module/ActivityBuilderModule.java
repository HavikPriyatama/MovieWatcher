package com.moviewatcher.mvw.di.module;

import com.moviewatcher.mvw.activity.ListMovieActivity;
import com.moviewatcher.mvw.activity.MainActivity;
import com.moviewatcher.mvw.activity.LoginActivity;
import com.moviewatcher.mvw.activity.RegisterActivity;
import com.moviewatcher.mvw.activity.SearchActivity;
import com.moviewatcher.mvw.fragment.FragmentGenre;
import com.moviewatcher.mvw.fragment.FragmentHome;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract LoginActivity contributeLoginActivity();

    @ContributesAndroidInjector
    abstract RegisterActivity contribuRegisterActivity();

    @ContributesAndroidInjector (modules = MainFragmentModule.class)
    abstract MainActivity contibuteHomeActivity();

    @ContributesAndroidInjector
    abstract SearchActivity contributeSearchActivity();

    @ContributesAndroidInjector
    abstract ListMovieActivity contribuListMovieActivity();

    @ContributesAndroidInjector
    abstract FragmentHome contributeFragmentHome();

    @ContributesAndroidInjector
    abstract FragmentGenre contirbuteFragmentGenre();

}
