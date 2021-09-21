package com.devmeca.simpletorrent.ui.history;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.devmeca.simpletorrent.R;
import com.devmeca.simpletorrent.service.HistoryService;

import java.util.List;
import java.util.Map;

public class HistoryFragment extends Fragment{

    private AppCompatActivity activity;
    private Context context;

    private HistoryListAdapter historyListAdapter;
    private ListView histroyListView;

    private boolean isCheckAll = false;
    private int theme;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        context = getActivity().getApplicationContext();

        int theme = com.devmeca.simpletorrent.core.utils.Utils.getThemePreference(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (theme == Integer.parseInt(context.getString(R.string.pref_theme_light_value))) {
                view.findViewById(R.id.btn_history_check).setBackgroundTintList(context.getResources().getColorStateList(R.color.primary));
                view.findViewById(R.id.btn_history_delete).setBackgroundTintList(context.getResources().getColorStateList(R.color.primary));
            }
            else if (theme == Integer.parseInt(context.getString(R.string.pref_theme_dark_value))) {
                view.findViewById(R.id.btn_history_check).setBackgroundTintList(context.getResources().getColorStateList(R.color.gray_dark));
                view.findViewById(R.id.btn_history_delete).setBackgroundTintList(context.getResources().getColorStateList(R.color.gray_dark));
            }
            else if (theme == Integer.parseInt(context.getString(R.string.pref_theme_black_value))) {
                view.findViewById(R.id.btn_history_check).setBackgroundTintList(context.getResources().getColorStateList(R.color.gray_dark));
                view.findViewById(R.id.btn_history_delete).setBackgroundTintList(context.getResources().getColorStateList(R.color.gray_dark));
            }
        }else{
            if (theme == Integer.parseInt(context.getString(R.string.pref_theme_light_value))) {
                view.findViewById(R.id.btn_history_check).setBackground(getResources().getDrawable(R.color.primary));
                view.findViewById(R.id.btn_history_delete).setBackground(getResources().getDrawable(R.color.primary));
            }
            else if (theme == Integer.parseInt(context.getString(R.string.pref_theme_dark_value))) {
                view.findViewById(R.id.btn_history_check).setBackground(getResources().getDrawable(R.color.gray_dark));
                view.findViewById(R.id.btn_history_delete).setBackground(getResources().getDrawable(R.color.gray_dark));
            }
            else if (theme == Integer.parseInt(context.getString(R.string.pref_theme_black_value))) {
                view.findViewById(R.id.btn_history_check).setBackground(getResources().getDrawable(R.color.gray_dark));
                view.findViewById(R.id.btn_history_delete).setBackground(getResources().getDrawable(R.color.gray_dark));
            }
        }


        histroyListView = view.findViewById(R.id.history_item_list);
        historyListAdapter = new HistoryListAdapter(getActivity(), context);
        histroyListView.setAdapter(historyListAdapter);

        view.findViewById(R.id.btn_history_check).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isCheckAll){
                    isCheckAll = false;
                    historyListAdapter.checkAll();
                    ((Button)view.findViewById(R.id.btn_history_check)).setText(getResources().getString(R.string.btn_history_check));
                }else{
                    isCheckAll = true;
                    ((Button)view.findViewById(R.id.btn_history_check)).setText(getResources().getString(R.string.btn_history_uncheck));
                    historyListAdapter.unCheckAll();
                }
            }
        });

        view.findViewById(R.id.btn_history_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                historyListAdapter.deleteCheckedItem();
            }
        });

        loadData();

        return view;
    }

    public static HistoryFragment newInstance()
    {
        HistoryFragment historyFragment = new HistoryFragment();

        Bundle args = new Bundle();
        historyFragment.setArguments(args);

        return historyFragment;
    }

    @Override
    public void onAttach(@NonNull Context context)
    {
        super.onAttach(context);

        if (context instanceof AppCompatActivity)
            this.activity = (AppCompatActivity)context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    public void loadData(){

        List<Map> resultList = HistoryService.getInstance(getActivity().getApplicationContext()).selectTorrentData();
        if(resultList==null){
            return;
        }

        for(int num=0; num<resultList.size(); num++){
            Integer _id = Integer.parseInt((String)(resultList.get(num)).get("_id"));
            String RULE_DATE = String.valueOf((resultList.get(num)).get("RULE_DATE"));
            String TOR_TITLE = String.valueOf((resultList.get(num)).get("TOR_TITLE"));
            String TOR_DISK_AMT = String.valueOf((resultList.get(num)).get("TOR_DISK_AMT"));
            String TOR_HASH = String.valueOf((resultList.get(num)).get("TOR_HASH"));
            HistoryItemData itemData = new HistoryItemData(_id, RULE_DATE, TOR_TITLE, TOR_DISK_AMT, TOR_HASH);

            historyListAdapter.addItem(itemData);
        }

        historyListAdapter.notifyDataSetChanged();

    }

    public HistoryListAdapter getHistoryListAdapter() {
        return historyListAdapter;
    }

    public void setHistoryListAdapter(HistoryListAdapter historyListAdapter) {
        this.historyListAdapter = historyListAdapter;
    }

}
