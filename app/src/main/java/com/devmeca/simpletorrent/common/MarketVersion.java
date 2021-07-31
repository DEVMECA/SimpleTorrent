package com.devmeca.simpletorrent.common;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MarketVersion extends AsyncTask<String, String, String> {
    Activity activity;
    Context context;

    public MarketVersion(Activity activity, Context context){
        this.activity = activity;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        String result_msg = "";

        System.out.println("-------접속 대기중------");
        try {

            InputStream is = null;
            URL urlCon = new URL(Constants.SERVER_DOMAIN +"/ajaxApiCall");
            HttpURLConnection httpCon = (HttpURLConnection)urlCon.openConnection();
            httpCon.setRequestMethod("POST");
            httpCon.setRequestProperty("Accept", "application/json");
            httpCon.setRequestProperty("Content-type", "application/json");
            httpCon.setDoOutput(true);
            httpCon.setDoInput(true);

            String pakage_name = strings[0];

            String json = "{\"androidId\" : \"" + Constants.android_id + "\""
                    + ", \"connect_url\" : \"" + "ajaxProductUpdateCheck" + "\""
                    + ", \"product_id\" : \"" + pakage_name + "\""
                    + "}";

            OutputStream os = httpCon.getOutputStream();
            os.write(json.getBytes("utf-8"));
            os.flush();

            try {
                BufferedReader br = new BufferedReader(new InputStreamReader((httpCon.getInputStream())));
                String output;
                while ((output = br.readLine()) != null) {
                    result_msg += output;
                }
                httpCon.disconnect();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                httpCon.disconnect();
            }

        } catch (Exception e) {
            result_msg = "error";
            e.printStackTrace();
        }

        return result_msg;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}