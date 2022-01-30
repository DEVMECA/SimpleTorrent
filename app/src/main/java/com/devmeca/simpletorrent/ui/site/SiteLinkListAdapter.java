package com.devmeca.simpletorrent.ui.site;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.devmeca.simpletorrent.R;
import com.devmeca.simpletorrent.service.SiteService;

import java.util.LinkedList;

public class SiteLinkListAdapter extends BaseAdapter {

    private LinkedList<SiteLinkItemData> listViewItemList = new LinkedList<SiteLinkItemData>();
    private Activity activity;
    private Context context;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;

        // "item_list" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_sitelink_list, parent, false);
        }

        SiteLinkItemData listViewItem = listViewItemList.get(position);
        LinearLayout sitelink_item_div = (LinearLayout) convertView.findViewById(R.id.sitelink_item_div);

        TextView torrent_site_nm = (TextView) convertView.findViewById(R.id.torrent_site_nm) ;
        TextView torrent_site_url = (TextView) convertView.findViewById(R.id.torrent_site_url) ;

        torrent_site_nm.setText(listViewItem.getTor_site_nm());
        torrent_site_url.setText(listViewItem.getTor_site_url());

        sitelink_item_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(listViewItem.getTor_site_url());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                activity.startActivity(intent);
            }
        });

        sitelink_item_div.setLongClickable(true);
        sitelink_item_div.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                new AlertDialog.Builder(activity)
                        .setTitle(context.getString(R.string.delete))
                        .setMessage(context.getString(R.string.delete_torrent_site_question))
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                try{
                                    removeItem(listViewItem);
                                    refreshItemList(listViewItemList);
                                    SiteService.getInstance(context).delTorrentSiteData(listViewItem);
                                }catch(Exception e){
                                    e.printStackTrace();
                                }
                            }})
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) { }})
                        .show();

                return false;
            }
        });

        return convertView;
    }


    public SiteLinkListAdapter(Activity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }

    @Override
    public int getCount() {
        if(listViewItemList==null) return 0;
        return listViewItemList.size();
    }

    @Override
    public SiteLinkItemData getItem(int position) {
        if(listViewItemList==null) return null;
        return listViewItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        if(listViewItemList==null) return 0;
        return listViewItemList.get(position).getTor_site_seq();
    }

    //리프레시(삭제 후 데이터 재저장)
    public void refreshItemList(LinkedList<SiteLinkItemData> listViewItemList_temp){
        removeAllItem();
        for(SiteLinkItemData item : listViewItemList_temp){
            listViewItemList.add(item);
        }
        notifyDataSetChanged();
    }

    // 아이템 데이터 추가
    public void addItem(SiteLinkItemData param) {
        listViewItemList.add(param);
        notifyDataSetChanged();
    }

    // 아이템 데이터 삭제
    public void removeItem(SiteLinkItemData param) {
        listViewItemList.remove(param);
        notifyDataSetChanged();
    }

    // 데이터 모두 삭제
    public void removeAllItem(){
        listViewItemList = new LinkedList<SiteLinkItemData>();
        notifyDataSetChanged();
    }

    //조회아이템 리스트(체크박스 리스트)_리스트 얻어오기
    public LinkedList<SiteLinkItemData> getListViewItemList() {
        return listViewItemList;
    }


}
