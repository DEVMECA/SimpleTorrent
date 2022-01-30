package com.devmeca.simpletorrent.ui.site;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.devmeca.simpletorrent.R;
import com.devmeca.simpletorrent.service.SiteService;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class SiteAddConfirmDialog extends DialogFragment {

    Context context;
    SiteLinkListAdapter siteLinkListAdapter;

    TextInputLayout tor_site_url_input;
    TextInputLayout tor_site_nm_input;
    TextInputEditText tor_site_url_input_2;
    TextInputEditText tor_site_nm_input_2;

    String question = "";

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public SiteAddConfirmDialog(SiteLinkListAdapter siteLinkListAdapter){
        this.siteLinkListAdapter = siteLinkListAdapter;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View convertView = inflater.inflate(R.layout.dialog_create_site, null, false);

        tor_site_url_input = convertView.findViewById(R.id.tor_site_url_input);
        tor_site_nm_input = convertView.findViewById(R.id.tor_site_nm_input);
        tor_site_url_input_2 = convertView.findViewById(R.id.tor_site_url_input_2);
        tor_site_nm_input_2 = convertView.findViewById(R.id.tor_site_nm_input_2);

        tor_site_url_input_2.setText("https://");

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(convertView)
                // Add action buttons
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String tor_site_url = tor_site_url_input_2.getText().toString();
                        String tor_site_nm = tor_site_nm_input_2.getText().toString();

                        if("".equals(tor_site_url.trim())){
                            tor_site_url_input_2.requestFocus();
                            return ;
                        }

                        if("".equals(tor_site_nm.trim()) || "http://".equals(tor_site_nm.trim()) || "https://".equals(tor_site_nm.trim())){
                            tor_site_nm_input_2.requestFocus();
                            return ;
                        }

                        List<SiteLinkItemData> siteLinkItemDataList = SiteService.getInstance(context).selectTorrentSiteData();
                        SiteLinkItemData data = new SiteLinkItemData();

                        String torSiteSeq = "1";
                        if(siteLinkItemDataList != null && siteLinkItemDataList.size()>1){
                            torSiteSeq = String.valueOf(siteLinkItemDataList.size() + 1);
                        }

                        data.setTor_site_seq(Long.parseLong(torSiteSeq));
                        data.setTor_site_nation_type("us");
                        data.setTor_genre("ALL");
                        data.setTor_site_nm(tor_site_nm);
                        data.setTor_site_url(tor_site_url);
                        data.setTor_order(torSiteSeq);
                        data.setTor_site_img("");

                        SiteService.getInstance(context).addTorrentSiteData(data);
                        siteLinkListAdapter.addItem(data);
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        return builder.create();
    }

}
