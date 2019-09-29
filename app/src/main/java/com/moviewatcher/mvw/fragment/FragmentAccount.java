package com.moviewatcher.mvw.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.moviewatcher.mvw.activity.MainActivity;
import com.moviewatcher.mvw.R;
import com.moviewatcher.mvw.utils.SharedPreferenceManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentAccount extends Fragment {

    SharedPreferenceManager sharedPreferenceManager;
    @BindView(R.id.app_bar)
    Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_account, container, false);
        ButterKnife.bind(this, rootView);
        sharedPreferenceManager = new SharedPreferenceManager(getContext());
        setHasOptionsMenu(true);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_account, menu);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle("Hello, " + sharedPreferenceManager.getUserName());
        menu.findItem(R.id.action_appbar).setTitle("Logout");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_appbar:
                sharedPreferenceManager.isLogout();
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
