/*
 * Copyright (C) 2018-2021 Yaroslav Pronin <proninyaroslav@mail.ru>
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

package com.devmeca.simpletorrent.core.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.devmeca.simpletorrent.core.model.data.Priority;
import com.devmeca.simpletorrent.core.model.data.entity.TagInfo;

import java.util.Arrays;
import java.util.List;

public class AddTorrentParams implements Parcelable {
    /* File path or magnet link */
    @NonNull
    public String source;
    public boolean fromMagnet;
    @NonNull
    public String sha1hash;
    @NonNull
    public String name;
    /* Optional field (e.g. if file information is available) */
    public Priority[] filePriorities;
    @NonNull
    public Uri downloadPath;
    public boolean sequentialDownload;
    public boolean addPaused;
    @NonNull
    public List<TagInfo> tags;

    public AddTorrentParams(
            @NonNull String source,
            boolean fromMagnet,
            @NonNull String sha1hash,
            @NonNull String name,
            Priority[] filePriorities,
            @NonNull Uri downloadPath,
            boolean sequentialDownload,
            boolean addPaused,
            @NonNull List<TagInfo> tags
    ) {
        this.source = source;
        this.fromMagnet = fromMagnet;
        this.sha1hash = sha1hash;
        this.name = name;
        this.filePriorities = filePriorities;
        this.downloadPath = downloadPath;
        this.sequentialDownload = sequentialDownload;
        this.addPaused = addPaused;
        this.tags = tags;
    }

    public AddTorrentParams(Parcel source) {
        this.source = source.readString();
        fromMagnet = source.readByte() != 0;
        sha1hash = source.readString();
        name = source.readString();
        filePriorities = (Priority[]) source.readArray(Priority.class.getClassLoader());
        downloadPath = source.readParcelable(Uri.class.getClassLoader());
        sequentialDownload = source.readByte() != 0;
        addPaused = source.readByte() != 0;
        tags = source.readArrayList(TagInfo.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(source);
        dest.writeByte((byte) (fromMagnet ? 1 : 0));
        dest.writeString(sha1hash);
        dest.writeString(name);
        dest.writeArray(filePriorities);
        dest.writeParcelable(downloadPath, flags);
        dest.writeByte((byte) (sequentialDownload ? 1 : 0));
        dest.writeByte((byte) (addPaused ? 1 : 0));
        dest.writeTypedList(tags);
    }

    public static final Creator<AddTorrentParams> CREATOR =
            new Creator<AddTorrentParams>() {
                @Override
                public AddTorrentParams createFromParcel(Parcel source) {
                    return new AddTorrentParams(source);
                }

                @Override
                public AddTorrentParams[] newArray(int size) {
                    return new AddTorrentParams[size];
                }
            };


    @Override
    public int hashCode() {
        return sha1hash.hashCode();
    }

    @Override
    public String toString() {
        return "AddTorrentParams{" +
                "source='" + source + '\'' +
                ", fromMagnet=" + fromMagnet +
                ", sha1hash='" + sha1hash + '\'' +
                ", name='" + name + '\'' +
                ", filePriorities=" + Arrays.toString(filePriorities) +
                ", downloadPath=" + downloadPath +
                ", sequentialDownload=" + sequentialDownload +
                ", addPaused=" + addPaused +
                ", tags=" + tags +
                '}';
    }
}
