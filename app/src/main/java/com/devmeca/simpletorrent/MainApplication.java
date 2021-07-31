/*
 * Copyright (C) 2016-2019 Yaroslav Pronin <proninyaroslav@mail.ru>
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

package com.devmeca.simpletorrent;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.multidex.MultiDexApplication;

import com.devmeca.simpletorrent.core.utils.Utils;
import com.devmeca.simpletorrent.ui.TorrentNotifier;
import com.devmeca.simpletorrent.ui.errorreport.ErrorReportActivity;

import org.acra.ACRA;
import org.acra.annotation.AcraCore;
import org.acra.annotation.AcraDialog;
import org.acra.annotation.AcraMailSender;

@AcraCore(buildConfigClass = com.devmeca.simpletorrent.BuildConfig.class)
@AcraMailSender(mailTo = "devmecacompany@gmail.com")
@AcraDialog(reportDialogClass = ErrorReportActivity.class)

public class MainApplication extends MultiDexApplication
{
    private static final String TAG = MainApplication.class.getSimpleName();

    static {
        /* Vector Drawable support in ImageView for API < 21 */
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    public void onCreate()
    {
        super.onCreate();

        Utils.migrateTray2SharedPreferences(this);
        ACRA.init(this);

        TorrentNotifier.getInstance(this).makeNotifyChans();
    }
}