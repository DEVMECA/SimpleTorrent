package com.devmeca.simpletorrent.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.devmeca.simpletorrent.common.DBHelper;
import com.devmeca.simpletorrent.enumtype.DbResultType;
import com.devmeca.simpletorrent.ui.site.SiteLinkItemData;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SiteService {

    static SiteService siteService;
    static Context context;

    ModelMapper modelMapper = new ModelMapper();
    DBHelper helper = null;
    SQLiteDatabase db = null;

    public static SiteService getInstance(Context contextParam){
        if(siteService == null){
            siteService = new SiteService();
            if(contextParam != null)
                context = contextParam;
        }
        return siteService;
    }


    public DbResultType dropTableSQL(){
        DbResultType result = DbResultType.FAIL;
        try{
            helper = new DBHelper(context, "torrentdata.db", null, 1);
            db = helper.getWritableDatabase();
            helper.onCreate(db);

            helper.executeSQL(db, "DROP TABLE TORRENTSITEDATA;");

            result = DbResultType.SUCCESS;
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(helper != null && db.isOpen()){
                try{
                    db.close();
                    helper.close();
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        }

        return result;
    }


    public DbResultType initSQL(){
        DbResultType result = DbResultType.FAIL;
        try{
            helper = new DBHelper(context, "torrentdata.db", null, 1);
            db = helper.getWritableDatabase();
            helper.onCreate(db);

            helper.executeSQL(db, "CREATE TABLE if not exists TORRENTSITEDATA ("
                    + "_id integer primary key autoincrement,"
                    + "tor_site_seq text,"
                    + "tor_site_nation_type text,"
                    + "tor_genre text,"
                    + "tor_site_nm text,"
                    + "tor_site_url text,"
                    + "tor_order integer,"
                    + "tor_site_img text"
                    + ");");

            result = DbResultType.SUCCESS;
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(helper != null && db.isOpen()){
                try{
                    db.close();
                    helper.close();
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        }

        return result;
    }


    public List<SiteLinkItemData> selectTorrentSiteData(){
        List<SiteLinkItemData> resultList = new ArrayList<>();
        try{
            helper = new DBHelper(context, "torrentdata.db", null, 1);
            db = helper.getWritableDatabase();
            helper.onCreate(db);

            List<Map> resultMapList = helper.selectSQL(db
                    , "SELECT _id, "
                            + "           tor_site_seq, "
                            + "           tor_site_nation_type, "
                            + "           tor_genre, "
                            + "           tor_site_nm, "
                            + "           tor_site_url, "
                            + "           tor_order, "
                            + "           tor_site_img "
                            + "     FROM TORRENTSITEDATA "
                            + "     ORDER BY tor_order; "
            );

            for(Map result : resultMapList){
                SiteLinkItemData data = new SiteLinkItemData();
                data.set_id(Long.parseLong((String)result.get("_id")));
                data.setTor_site_seq(Long.parseLong((String)result.get("tor_site_seq")));
                data.setTor_site_nation_type((String)result.get("tor_site_nation_type"));
                data.setTor_genre((String)result.get("tor_genre"));
                data.setTor_site_nm((String)result.get("tor_site_nm"));
                data.setTor_site_url((String)result.get("tor_site_url"));
                data.setTor_order((String)result.get("tor_order"));
                data.setTor_site_img((String)result.get("tor_site_img"));
                resultList.add(data);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(helper != null && db.isOpen()){
                try{
                    db.close();
                    helper.close();
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        }

        return resultList;

    }

    public DbResultType addTorrentSiteData(SiteLinkItemData itemData){

        DbResultType result = DbResultType.FAIL;
        try{
            helper = new DBHelper(context, "torrentdata.db", null, 1);
            db = helper.getWritableDatabase();
            helper.onCreate(db);

            helper.executeSQL(db, "INSERT INTO TORRENTSITEDATA ("
                    + "tor_site_seq,"
                    + "tor_site_nation_type,"
                    + "tor_genre,"
                    + "tor_site_nm,"
                    + "tor_site_url,"
                    + "tor_order,"
                    + "tor_site_img"
                    + ") "
                    + "VALUES ("
                    + "\"" + itemData.getTor_site_seq() + "\","
                    + "\"" + itemData.getTor_site_nation_type() + "\","
                    + "\"" + itemData.getTor_genre() + "\","
                    + "\"" + itemData.getTor_site_nm() + "\","
                    + "\"" + itemData.getTor_site_url() + "\","
                    + "\"" + itemData.getTor_order() + "\","
                    + "\"" + itemData.getTor_site_img() + "\""
                    + ");"
            );

            result = DbResultType.SUCCESS;
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(helper != null && db.isOpen()){
                try{
                    db.close();
                    helper.close();
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        }

        return result;

    }

    public DbResultType delTorrentSiteData(SiteLinkItemData itemData){

        DbResultType result = DbResultType.FAIL;
        try{
            helper = new DBHelper(context, "torrentdata.db", null, 1);
            db = helper.getWritableDatabase();
            helper.onCreate(db);

            helper.executeSQL(db, "DELETE FROM TORRENTSITEDATA"
                    + " WHERE _id = " + "\"" + itemData.get_id() + "\";"
            );

            result = DbResultType.SUCCESS;
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(helper != null && db.isOpen()){
                try{
                    db.close();
                    helper.close();
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        }

        return result;

    }


    public DbResultType delAllTorrentSiteData(){

        DbResultType result = DbResultType.FAIL;
        try{
            helper = new DBHelper(context, "torrentdata.db", null, 1);
            db = helper.getWritableDatabase();
            helper.onCreate(db);

            helper.executeSQL(db, "DELETE FROM TORRENTSITEDATA;");

            result = DbResultType.SUCCESS;
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(helper != null && db.isOpen()){
                try{
                    db.close();
                    helper.close();
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        }

        return result;

    }


}
