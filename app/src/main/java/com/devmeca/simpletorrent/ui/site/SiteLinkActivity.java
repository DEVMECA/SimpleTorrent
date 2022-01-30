package com.devmeca.simpletorrent.ui.site;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.devmeca.simpletorrent.R;
import com.devmeca.simpletorrent.core.utils.Utils;
import com.devmeca.simpletorrent.ui.BaseAlertDialog;

import io.reactivex.disposables.CompositeDisposable;

public class SiteLinkActivity extends AppCompatActivity
{
    private static final String TAG = SiteLinkActivity.class.getSimpleName();
    private static final String TAG_SITE_LINK_FRAGMENT = "fragment_sitelink";
    SiteLinkFragment siteLinkFragment;

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

        setContentView(R.layout.activity_site_torrent);
        initLayout();

        FragmentManager fm = getSupportFragmentManager();
        siteLinkFragment = (SiteLinkFragment)fm.findFragmentByTag(TAG_SITE_LINK_FRAGMENT);

    }

    private void initLayout()
    {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.site_link_menu);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener((v) -> finish());
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

}
