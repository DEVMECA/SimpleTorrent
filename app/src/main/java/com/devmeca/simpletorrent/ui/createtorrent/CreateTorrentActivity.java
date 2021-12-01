/*
 * Copyright (C) 2019 Yaroslav Pronin <proninyaroslav@mail.ru>
 *
 * This file is part of LibreTorrent.
 *
 * LibreTorrent is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * LibreTorrent is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with LibreTorrent.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.devmeca.simpletorrent.ui.createtorrent;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.devmeca.simpletorrent.core.utils.Utils;
import com.devmeca.simpletorrent.ui.FragmentCallback;
import com.devmeca.simpletorrent.ui.PermissionDeniedDialog;
import com.devmeca.simpletorrent.ui.StoragePermissionManager;

public class CreateTorrentActivity extends AppCompatActivity
    implements FragmentCallback
{
    private static final String TAG_CREATE_TORRENT_DIALOG = "create_torrent_dialog";
    private static final String TAG_PERM_DENIED_DIALOG = "perm_denied_dialog";

    private CreateTorrentDialog createTorrentDialog;
    private PermissionDeniedDialog permDeniedDialog;
    private boolean permDialogIsShow = false;
    private StoragePermissionManager permissionManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        setTheme(Utils.getTranslucentAppTheme(getApplicationContext()));
        super.onCreate(savedInstanceState);

        FragmentManager fm = getSupportFragmentManager();
        permissionManager = new StoragePermissionManager(this,
                (isGranted, shouldRequestStoragePermission) -> {
                    if (!isGranted && shouldRequestStoragePermission) {
                        if (fm.findFragmentByTag(TAG_PERM_DENIED_DIALOG) == null) {
                            permDeniedDialog = PermissionDeniedDialog.newInstance();
                            FragmentTransaction ft = fm.beginTransaction();
                            ft.add(permDeniedDialog, TAG_PERM_DENIED_DIALOG);
                            ft.commitAllowingStateLoss();
                        }
                    }
                });

        createTorrentDialog = (CreateTorrentDialog)fm.findFragmentByTag(TAG_CREATE_TORRENT_DIALOG);
        if (createTorrentDialog == null) {
            createTorrentDialog = CreateTorrentDialog.newInstance();
            createTorrentDialog.show(fm, TAG_CREATE_TORRENT_DIALOG);
        }

        if (savedInstanceState != null)
            permDialogIsShow = savedInstanceState.getBoolean(TAG_CREATE_TORRENT_DIALOG);

        if (!permissionManager.checkPermissions() && permDeniedDialog == null) {
            permissionManager.requestPermissions();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState)
    {
        outState.putBoolean(TAG_CREATE_TORRENT_DIALOG, permDialogIsShow);

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onFragmentFinished(@NonNull Fragment f, Intent intent, @NonNull ResultCode code)
    {
        finish();
    }

    @Override
    public void onBackPressed()
    {
        createTorrentDialog.onBackPressed();
    }
}
