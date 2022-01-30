package com.devmeca.simpletorrent.ui.site;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.devmeca.simpletorrent.R;
import com.devmeca.simpletorrent.common.Constants;
import com.devmeca.simpletorrent.core.model.data.RetrofitResultDto;
import com.devmeca.simpletorrent.service.RetrofitService;
import com.devmeca.simpletorrent.service.SiteService;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SiteLinkFragment extends Fragment{

    private AppCompatActivity activity;
    private Context context;

    private SiteLinkListAdapter siteLinkListAdapter;
    private ListView siteLinkListView;

    private SiteAddConfirmDialog confirmDialog;
    Button btn_site_add;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_sitelink, container, false);
        context = getActivity().getApplicationContext();

        siteLinkListView = view.findViewById(R.id.sitelink_item_list);
        siteLinkListAdapter = new SiteLinkListAdapter(getActivity(), context);
        siteLinkListView.setAdapter(siteLinkListAdapter);

        confirmDialog = new SiteAddConfirmDialog(siteLinkListAdapter);
        confirmDialog.setQuestion(getString(R.string.add));

        btn_site_add = view.findViewById(R.id.btn_site_add);
        btn_site_add.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmDialog();
            }
        });

        List<SiteLinkItemData> TORRENT_DATA = loadDBData(context);
        if(TORRENT_DATA == null || TORRENT_DATA.size()==0){
            loadAjaxData();
        }else{
            for(SiteLinkItemData TORRENT : TORRENT_DATA){
                siteLinkListAdapter.addItem(TORRENT);
            }
        }

        return view;
    }

    public static SiteLinkFragment newInstance()
    {
        SiteLinkFragment siteLinkFragment = new SiteLinkFragment();

        Bundle args = new Bundle();
        siteLinkFragment.setArguments(args);

        return siteLinkFragment;
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

    public List<SiteLinkItemData> loadDBData(Context context){
        return SiteService.getInstance(context).selectTorrentSiteData();
    }

    public void loadAjaxData(){

        try{
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.SERVER_DOMAIN)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            RetrofitService service = retrofit.create(RetrofitService.class);
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                    "{\"connect_url\":\"api/ajaxTorrentWebData\""
                            + ",\"androidId\":\"" + Constants.android_id + "\""
                            +   "}");

            Call<RetrofitResultDto> retrofitResultDto = service.getPosts(body);
            retrofitResultDto.enqueue(new Callback<RetrofitResultDto>(){
                @Override
                public void onResponse(Call<RetrofitResultDto> call, Response<RetrofitResultDto> response) {
                    if(response.isSuccessful()){
                        RetrofitResultDto result = response.body();
                        List<SiteLinkItemData> TORRENT_DATA = result.getTORRENT_DATA();
                        for(SiteLinkItemData TORRENT : TORRENT_DATA){
                            siteLinkListAdapter.addItem(TORRENT);
                            SiteService.getInstance(context).addTorrentSiteData(TORRENT);
                        }
                    }else{
                        Toast.makeText(getContext(),
                                getResources().getString(R.string.error),
                                Toast.LENGTH_LONG)
                                .show();
                    }
                }
                @Override
                public void onFailure(Call<RetrofitResultDto> call, Throwable t) {
                    Toast.makeText(getContext(),
                            t.getLocalizedMessage(),
                            Toast.LENGTH_LONG)
                            .show();
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public SiteLinkListAdapter getSiteLinkListAdapter() {
        return siteLinkListAdapter;
    }

    public void setSiteLinkListAdapter(SiteLinkListAdapter siteLinkListAdapter) {
        this.siteLinkListAdapter = siteLinkListAdapter;
    }

    public void showConfirmDialog(){
        confirmDialog.show(activity.getSupportFragmentManager(), "ConfirmDialog");
    }
}
