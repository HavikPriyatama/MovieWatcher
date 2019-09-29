package com.moviewatcher.mvw.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import com.jakewharton.rxbinding3.widget.RxTextView;
import com.moviewatcher.mvw.R;
import com.moviewatcher.mvw.adapter.SearchAdapter;
import com.moviewatcher.mvw.contract.SearchContract;
import com.moviewatcher.mvw.model.Movie;
import com.moviewatcher.mvw.presenter.SearchPresenter;
import com.moviewatcher.mvw.utils.IntentManager;
import com.squareup.picasso.Picasso;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static com.moviewatcher.mvw.helper.Constant.SEARCH_VIEW;

public class SearchActivity extends DaggerAppCompatActivity implements SearchContract.View {

    @BindView(R.id.txt_search)
    EditText txtSearch;
    @BindView(R.id.recycleView_search)
    RecyclerView recyclerView;
    @Inject
    SearchPresenter searchPresenter;
    @Inject
    Picasso picasso;
    @Inject
    IntentManager intentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        Disposable disposable = RxTextView.textChanges(txtSearch)
                .debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(charSequence -> searchPresenter.onTextChange(charSequence.toString()));

        txtSearch.setOnEditorActionListener((v, actionId, event) -> {
            hideKeyboard(this);
            txtSearch.clearFocus();
            intentManager.getListMovie(0, txtSearch.getText().toString(),"Search Result", SEARCH_VIEW);
            return true;
        });
    }

    @Override
    public void setSearchList(List<Movie> movies) {
        SearchAdapter adapter = new SearchAdapter(this, movies, picasso, intentManager);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        searchPresenter.onDestroy();
        super.onDestroy();
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
