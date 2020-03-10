package com.pixelhubllc.dictionary.database;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseOpenHelper extends SQLiteAssetHelper {

    private static final String DB_NAME = "dictionary.db";
    private static final int DB_VERSION = 1;

    public DatabaseOpenHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }
}
