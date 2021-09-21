package com.devmeca.simpletorrent.ui.history;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.devmeca.simpletorrent.R;
import com.devmeca.simpletorrent.core.utils.Utils;
import com.devmeca.simpletorrent.service.HistoryService;
import com.devmeca.simpletorrent.ui.BaseAlertDialog;

import io.reactivex.disposables.CompositeDisposable;

public class HistoryActivity extends AppCompatActivity
{
    private static final String TAG = HistoryActivity.class.getSimpleName();
    private static final String TAG_HISTORY_FRAGMENT = "fragment_history";
    HistoryFragment historyFragment;

    private CompositeDisposable disposables = new CompositeDisposable();
    private BaseAlertDialog.SharedViewModel dialogViewModel;

    /* Android data binding doesn't work with layout aliases */
    private Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        setTheme(Utils.getAppTheme(getApplicationContext()));
        super.onCreate(savedInstanceState);

        ViewModelProvider provider = new ViewModelProvider(this);
        dialogViewModel = provider.get(BaseAlertDialog.SharedViewModel.class);

        setContentView(R.layout.activity_history_view);
        initLayout();

        FragmentManager fm = getSupportFragmentManager();
        historyFragment = (HistoryFragment)fm.findFragmentByTag(TAG_HISTORY_FRAGMENT);

        HistoryService.getInstance(this).initSQL();


    }

    private void initLayout()
    {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.history_menu);
        toolbar.inflateMenu(R.menu.history);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener((v) -> finish());
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
