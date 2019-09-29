package com.moviewatcher.mvw.activity;


import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputLayout;
import com.moviewatcher.mvw.R;
import com.moviewatcher.mvw.contract.LoginContract;
import com.moviewatcher.mvw.presenter.LoginPresenter;
import com.moviewatcher.mvw.utils.IntentManager;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class LoginActivity extends DaggerAppCompatActivity implements LoginContract.View {

    private static final String TAG = "LoginActivity";

    @Inject
    LoginPresenter loginPresenter;
    @Inject
    IntentManager intentManager;

    @BindView(R.id.txt_username)
    TextInputLayout txtUsername;
    @BindView(R.id.txt_password)
    TextInputLayout txtPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.app_bar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnLogin.setOnClickListener(v -> tryToLogin());
        txtPassword.getEditText().setOnEditorActionListener((v, actionId, event) -> {
            boolean handel = false;
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                tryToLogin();
                handel = true;
            }
            return handel;
        });
    }

    public void tryToLogin() {
        loginPresenter.doLogin(txtUsername.getEditText().getText().toString(), txtPassword.getEditText().getText().toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_appbar:
                intentManager.noHistoryIntent(RegisterActivity.class);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_account, menu);
        menu.findItem(R.id.action_appbar).setTitle("Register");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onSuccess() {
        intentManager.clearStackIntent(MainActivity.class);
        finish();
    }

    @Override
    public void onNotFound() {
        Toast.makeText(this, "User ID and Password Not Found", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError() {
        Toast.makeText(this, "Error get data from DB", Toast.LENGTH_SHORT).show();
    }
}
