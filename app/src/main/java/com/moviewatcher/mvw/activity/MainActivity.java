package com.moviewatcher.mvw.activity;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.moviewatcher.mvw.R;
import com.moviewatcher.mvw.contract.MainContract;
import com.moviewatcher.mvw.fragment.FragmentAccount;
import com.moviewatcher.mvw.fragment.FragmentFavorite;
import com.moviewatcher.mvw.fragment.FragmentGenre;
import com.moviewatcher.mvw.fragment.FragmentHome;
import com.moviewatcher.mvw.utils.IntentManager;
import com.moviewatcher.mvw.utils.SharedPreferenceManager;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements MainContract.View {

    private static final String TAG = "MainActivity";
    @BindView(R.id.bottom_navigation)
    BottomNavigationView navigationView;
    @Inject
    FragmentHome home;
    @Inject
    FragmentGenre genre;
    @Inject
    FragmentAccount account;
    @Inject
    SharedPreferenceManager sharedPreference;
    @Inject
    IntentManager intentHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        navigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.home_menu:
                    swapFragment(home, "Home");
                    return true;
                case R.id.genre_menu:
                     swapFragment(genre, "Genre");
                    return true;
                case R.id.account_menu:
                    if (sharedPreference.getIsLogin()) {
                        swapFragment(account, "Account");
                        return true;
                    }
                    else{
                        intentHelper.noHistoryIntent(LoginActivity.class);
                        return false;
                    }
            }
            return false;
        });
       navigationView.setSelectedItemId(R.id.home_menu);
    }

    public void swapFragment(Fragment mfragment, String key){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment curFrag = fragmentManager.getPrimaryNavigationFragment();
        if (curFrag != null) {
            fragmentTransaction.hide(curFrag);
        }

        Fragment fragment2 = fragmentManager.findFragmentByTag(key);
        if (fragment2 == null) {
            fragmentTransaction.add(R.id.fragmnet_container, mfragment, key);
        } else {
            fragmentTransaction.show(mfragment);
        }

        fragmentTransaction.setPrimaryNavigationFragment(mfragment);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commitNowAllowingStateLoss();
    }
}
