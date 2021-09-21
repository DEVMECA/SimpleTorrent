package com.devmeca.simpletorrent.ui.history;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.devmeca.simpletorrent.R;
import com.devmeca.simpletorrent.core.utils.Utils;
import com.devmeca.simpletorrent.service.HistoryService;
import com.devmeca.simpletorrent.ui.addtorrent.AddTorrentActivity;

import java.util.LinkedList;

public class HistoryListAdapter extends BaseAdapter {

    private LinkedList<HistoryItemData> listViewItemList = new LinkedList<HistoryItemData>();
    private Activity activity;
    private Context context;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;

        // "item_list" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_history_list, parent, false);
        }

        HistoryItemData listViewItem = listViewItemList.get(position);

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        CheckBox tor_id        = (CheckBox) convertView.findViewById(R.id.tor_id) ;
        tor_id.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    listViewItemList.get(position).setUSE_YN("N");
                }else{
                    listViewItemList.get(position).setUSE_YN("Y");
                }
            }
        });
        LinearLayout history_item_div = (LinearLayout) convertView.findViewById(R.id.history_item_div);
        history_item_div.setOnClickListener(new LinearLayout.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, AddTorrentActivity.class);
                i.putExtra(AddTorrentActivity.TAG_URI, Uri.parse("magnet:?xt=urn:btih:" + listViewItem.getTOR_HASH()));
                activity.startActivity(i);
            }
        });
        TextView tor_title = (TextView) convertView.findViewById(R.id.tor_title) ;
        TextView tor_disk_amt = (TextView) convertView.findViewById(R.id.tor_disk_amt) ;
        TextView rule_date = (TextView) convertView.findViewById(R.id.rule_date) ;

        if(!"Y".equals(listViewItemList.get(position).getUSE_YN())){
            tor_id.setChecked(true);
        }else{
            tor_id.setChecked(false);
        }
        tor_title.setText(listViewItemList.get(position).getTOR_TITLE());
        tor_disk_amt.setText(listViewItemList.get(position).getTOR_DISK_AMT());
        rule_date.setText(listViewItemList.get(position).getRULE_DATE());

        int theme = Utils.getThemePreference(context);
        if (theme == Integer.parseInt(context.getString(R.string.pref_theme_light_value))) {
            tor_title.setTextColor(ContextCompat.getColor(context, R.color.text_primary));
            tor_disk_amt.setTextColor(ContextCompat.getColor(context, R.color.text_primary));
            rule_date.setTextColor(ContextCompat.getColor(context, R.color.text_primary));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                tor_id.setButtonTintList(context.getResources().getColorStateList(R.color.text_primary));
            }
        }
        else if (theme == Integer.parseInt(context.getString(R.string.pref_theme_dark_value))) {
            tor_title.setTextColor(ContextCompat.getColor(context, R.color.text_secondary));
            tor_disk_amt.setTextColor(ContextCompat.getColor(context, R.color.text_secondary));
            rule_date.setTextColor(ContextCompat.getColor(context, R.color.text_secondary));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                tor_id.setButtonTintList(context.getResources().getColorStateList(R.color.text_secondary));
            }
        }
        else if (theme == Integer.parseInt(context.getString(R.string.pref_theme_black_value))) {
            tor_title.setTextColor(ContextCompat.getColor(context, R.color.text_primary_inverse));
            tor_disk_amt.setTextColor(ContextCompat.getColor(context, R.color.text_primary_inverse));
            rule_date.setTextColor(ContextCompat.getColor(context, R.color.text_primary_inverse));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                tor_id.setButtonTintList(context.getResources().getColorStateList(R.color.text_primary_inverse));
            }
        }

        return convertView;
    }


    public HistoryListAdapter(Activity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }

    @Override
    public int getCount() {
        if(listViewItemList==null) return 0;
        return listViewItemList.size();
    }

    @Override
    public HistoryItemData getItem(int position) {
        if(listViewItemList==null) return null;
        return listViewItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        if(listViewItemList==null) return 0;
        return listViewItemList.get(position).get_id();
    }

    //리프레시(삭제 후 데이터 재저장)
    public void refreshItemList(LinkedList<HistoryItemData> listViewItemList_temp){
        removeAllItem();
        for(HistoryItemData item : listViewItemList_temp){
            listViewItemList.add(item);
        }
        notifyDataSetChanged();
    }

    // 아이템 데이터 추가
    public void addItem(HistoryItemData schItemData) {
        listViewItemList.add(schItemData);
        notifyDataSetChanged();
    }

    // 데이터 모두 삭제
    public void removeAllItem(){
        listViewItemList = new LinkedList<HistoryItemData>();
        notifyDataSetChanged();
    }

    public void checkAll(){
        for(int i=0; i<listViewItemList.size(); i++){
            listViewItemList.get(i).setUSE_YN("Y");
        }
        notifyDataSetChanged();
    }

    public void unCheckAll(){
        for(int i=0; i<listViewItemList.size(); i++){
            listViewItemList.get(i).setUSE_YN("N");
        }
        notifyDataSetChanged();
    }


    public void deleteCheckedItem(){
        LinkedList<HistoryItemData> listViewItemListTemp = new LinkedList<HistoryItemData>();
        for(int i=0; i<listViewItemList.size(); i++){
            if(!"Y".equals(listViewItemList.get(i).getUSE_YN())){
                HistoryService.getInstance(context).delTorrentData(listViewItemList.get(i));
            }else{
                listViewItemListTemp.add(listViewItemList.get(i));
            }
        }

        refreshItemList(listViewItemListTemp);
    }

    //조회아이템 리스트(체크박스 리스트)_리스트 얻어오기
    public LinkedList<HistoryItemData> getListViewItemList() {
        return listViewItemList;
    }


}
