/*
 * Copyright (C) 2016, 2019 Yaroslav Pronin <proninyaroslav@mail.ru>
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

package com.devmeca.simpletorrent.core.sorting;

import com.devmeca.simpletorrent.ui.main.TorrentListItem;

import java.util.Comparator;

public class TorrentSortingComparator implements Comparator<TorrentListItem>
{
    private TorrentSorting sorting;

    public TorrentSortingComparator(TorrentSorting sorting)
    {
        this.sorting = sorting;
    }

    public TorrentSorting getSorting()
    {
        return sorting;
    }

    @Override
    public int compare(TorrentListItem state1, TorrentListItem state2)
    {
        return TorrentSorting.SortingColumns.fromValue(sorting.getColumnName())
                .compare(state1, state2, sorting.getDirection());
    }
}
