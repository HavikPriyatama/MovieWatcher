package com.moviewatcher.mvw.activity;

import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputLayout;
import com.moviewatcher.mvw.R;
import com.moviewatcher.mvw.contract.RegisterContract;
import com.moviewatcher.mvw.presenter.RegisterPresenter;
import com.moviewatcher.mvw.utils.IntentManager;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class RegisterActivity extends DaggerAppCompatActivity implements RegisterContract.View {

    @Inject
    RegisterPresenter registerPresenter;
    @Inject
    IntentManager intentManager;

    @BindView(R.id.app_bar)
    Toolbar toolbar;
    @BindView(R.id.txt_username)
    TextInputLayout username;
    @BindView(R.id.txt_password)
    TextInputLayout password;
    @BindView(R.id.txt_repassword)
    TextInputLayout rePassword;
    @BindView(R.id.btn_register)
    Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnRegister.setOnClickListener(v -> registerPresenter.onRegister(username.getEditText().getText().toString(), password.getEditText().getText().toString(),
                rePassword.getEditText().getText().toString()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_account, menu);
        menu.findItem(R.id.action_appbar).setTitle("Login");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_appbar:
                intentManager.noHistoryIntent(LoginActivity.class);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onSuccess() {
        intentManager.noHistoryIntent(LoginActivity.class);
    }

    @Override
    public void onFailed() {
        Toast.makeText(this, "FAILED", Toast.LENGTH_SHORT).show();
    }
}
