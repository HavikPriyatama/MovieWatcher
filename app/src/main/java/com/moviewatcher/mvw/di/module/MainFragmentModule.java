package com.moviewatcher.mvw.di.module;

import com.moviewatcher.mvw.fragment.FragmentAccount;
import com.moviewatcher.mvw.fragment.FragmentFavorite;
import com.moviewatcher.mvw.fragment.FragmentGenre;
import com.moviewatcher.mvw.fragment.FragmentHome;

import dagger.Module;
import dagger.Provides;

@Module
public class MainFragmentModule {
    @Provides
    FragmentHome provideFragmentHome(){
        return new FragmentHome();
    }

    @Provides
    FragmentGenre provideFragmentGenre(){
        return new FragmentGenre();
    }

    @Provides
    FragmentAccount provideFragmentAccount(){
        return new FragmentAccount();
    }

}
