package com.devmeca.simpletorrent.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.devmeca.simpletorrent.common.DBHelper;
import com.devmeca.simpletorrent.enumtype.DbResultType;
import com.devmeca.simpletorrent.ui.history.HistoryItemData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HistoryService {

    static HistoryService historyService;
    static Context context;

    DBHelper helper = null;
    SQLiteDatabase db = null;

    public static HistoryService getInstance(Context contextParam){
        if(historyService == null){
            historyService = new HistoryService();
            if(contextParam != null)
                context = contextParam;
        }
        return historyService;
    }


    public DbResultType dropTableSQL(){
        DbResultType result = DbResultType.FAIL;
        try{
            helper = new DBHelper(context, "torrentdata.db", null, 1);
            db = helper.getWritableDatabase();
            helper.onCreate(db);

            helper.executeSQL(db, "DROP TABLE TORRENTDATA;");

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

            helper.executeSQL(db, "CREATE TABLE if not exists TORRENTDATA ("
                    + "_id integer primary key autoincrement,"
                    + "RULE_DATE text,"
                    + "TOR_TITLE text,"
                    + "TOR_DISK_AMT text,"
                    + "TOR_HASH text"
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


    public List<Map> selectTorrentData(){
        List<Map> resultList = new ArrayList<>();
        try{
            helper = new DBHelper(context, "torrentdata.db", null, 1);
            db = helper.getWritableDatabase();
            helper.onCreate(db);

            resultList = helper.selectSQL(db
                    , "SELECT _id, "
                            + "           RULE_DATE, "
                            + "           TOR_TITLE, "
                            + "           TOR_DISK_AMT, "
                            + "           TOR_HASH "
                            + "     FROM TORRENTDATA "
                            + "     ORDER BY _id DESC; "
            );
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

    public DbResultType addTorrentData(HistoryItemData itemData){

        DbResultType result = DbResultType.FAIL;
        try{
            helper = new DBHelper(context, "torrentdata.db", null, 1);
            db = helper.getWritableDatabase();
            helper.onCreate(db);

            helper.executeSQL(db, "INSERT INTO TORRENTDATA ("
                    + "rule_date,"
                    + "tor_title,"
                    + "tor_disk_amt,"
                    + "tor_hash"
                    + ") "
                    + "VALUES ("
                    + "\"" + itemData.getRULE_DATE() + "\","
                    + "\"" + itemData.getTOR_TITLE() + "\","
                    + "\"" + itemData.getTOR_DISK_AMT() + "\","
                    + "\"" + itemData.getTOR_HASH() + "\""
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

    public DbResultType delTorrentData(HistoryItemData itemData){

        DbResultType result = DbResultType.FAIL;
        try{
            helper = new DBHelper(context, "torrentdata.db", null, 1);
            db = helper.getWritableDatabase();
            helper.onCreate(db);

            helper.executeSQL(db, "DELETE FROM TORRENTDATA"
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


    public DbResultType delAllTorrentData(){

        DbResultType result = DbResultType.FAIL;
        try{
            helper = new DBHelper(context, "torrentdata.db", null, 1);
            db = helper.getWritableDatabase();
            helper.onCreate(db);

            helper.executeSQL(db, "DELETE FROM TORRENTDATA;");

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
