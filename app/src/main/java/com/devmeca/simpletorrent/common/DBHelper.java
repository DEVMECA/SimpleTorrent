package com.devmeca.simpletorrent.common;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    }

    public String executeSQL(SQLiteDatabase db, String sql){
        String result = "";
        try{
            db.execSQL(sql);
            result = "SUCCESS";
        }catch(Exception e){
            e.printStackTrace();
            result = e.getMessage();
        }

        return result;
    }

    public List<Map> selectSQL(SQLiteDatabase db, String sql){
        List<Map> result = new LinkedList<Map>();
        try{
            Cursor c = db.rawQuery(sql, null);
            while(c.moveToNext()){
                Map result_obj = new HashMap();
                for(int num=0; num<c.getColumnNames().length; num++){
                    result_obj.put(c.getColumnName(num), c.getString(c.getColumnIndex(c.getColumnName(num))));
                }
                result.add(result_obj);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
