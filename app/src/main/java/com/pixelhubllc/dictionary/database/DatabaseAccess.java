package com.pixelhubllc.dictionary.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.pixelhubllc.dictionary.model.Model;

import java.util.ArrayList;

public class DatabaseAccess {
    private static final String TAG = "databaseaccess";
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;


    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context){
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context){
        if (instance == null){
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the databases connection.
     */
    public void open(){
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the databases connection.
     */
    public void close(){
        if (database != null){
            this.database.close();
        }
    }


    /**
     * Read all quotes from the databases.
     *
     * @return a List of quotes
     */
//    public ArrayList<String> getWords() {
//        ArrayList<String> list = new ArrayList<>();
//        Cursor cursor = database.rawQuery("SELECT en_word FROM words", null);
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()) {
//            list.add(cursor.getString(cursor.getColumnIndex("en_word")));
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return list;
//    }
//


    public ArrayList<Model> getWordsAndId() throws SQLException {
        ArrayList<Model> data= new ArrayList<>();
            String query = "SELECT _id,en_word FROM words";
            Cursor row = database.rawQuery(query, null);
            if (row != null) {
                row.moveToFirst();
                while(!row.isAfterLast()){
                    int id=row.getInt(row.getColumnIndex("_id"));
                    String word=row.getString(row.getColumnIndex("en_word"));
                    data.add(new Model(id,word));
                    // do what ever you want here
                    row.moveToNext();
                }

                row.close();
            }
        else
            data=null;

        return data;
    }



    public ArrayList<Model> fetchdatabyfilter(String inputText) throws SQLException {
        ArrayList<Model> data= new ArrayList<>();

        if (inputText != null  ||  inputText.length () < 0)  {
            //query = "SELECT * FROM "+dbTable+" WHERE "+filtercolumn+" like '%"+inputText+"%'";
            String query = "SELECT _id,en_word FROM words WHERE en_word LIKE '"+inputText+"%'"+"LIMIT 10";

            Cursor row = database.rawQuery(query, null);
            if (row != null) {
                row.moveToFirst();
                while(!row.isAfterLast()){
                    int id=row.getInt(row.getColumnIndex("_id"));
                    String word=row.getString(row.getColumnIndex("en_word"));
                    data.add(new Model(id,word));
                    // do what ever you want here
                    row.moveToNext();
                }

                row.close();
            }
        }
        else
            data=null;

        return data;
    }

    public Model fetchdatabyId(int id) throws SQLException {
        Model data=null;
        Cursor row = null;
        if (id>0)  {
            //query = "SELECT * FROM "+dbTable+" WHERE "+filtercolumn+" like '%"+inputText+"%'";
            Log.d("iddd",String.valueOf(id));
            String query = "SELECT * FROM words WHERE _id="+id;

            row = database.rawQuery(query, null);

            if (row.getCount()>0) {
                row.moveToFirst();

                String word=row.getString(row.getColumnIndex("en_word"));

                String definition=row.getString(row.getColumnIndex("en_definition"));
                String example=row.getString(row.getColumnIndex("example"));
                String synonyms=row.getString(row.getColumnIndex("synonyms"));
                String antonyms=row.getString(row.getColumnIndex("antonyms"));
                data=new Model(id,word,definition, example, synonyms, antonyms);

            }

        }
        else
            data=null;

        return data;
    }


    public long insertHistory(int id,String text)
    {
        Cursor row = null;

        row = database.rawQuery("SELECT _id FROM history where _id =" + id, null);
        if (row.getCount() == 0){
            //database.execSQL("INSERT INTO history(_id, word) VALUES(('" + id + "'),('" + text + "'))");
            ContentValues values=new ContentValues();
            values.put("_id",id);
            values.put("word",text);
            return database.insert("history",null,values);

        } else{
        return -1;
        }
    }


    //history insert
/*    public Model insertHistory(int id,String text)
    {
        Model data = null;

        if (id>0) {

            database.execSQL("INSERT INTO history(_id, word) VALUES(('" + id + "'),('" + text + "'))");
            data = new Model(id, text);
        }
        else
            data = null;
            return data;

    }*/


    //history insert
//    public Model insertHistory(int id,String text)
//    {
//        Model data = null;
//
//
//        Cursor c = database.rawQuery("SELECT _id FROM history where _id =" + id, null);
//        if (c != null) {
//            Log.e("TAG", "query successull and available" + id);
//            data = null;
//
//        } else {
//            Log.e("TAG", "query successull and available" + id);
//            database.execSQL("INSERT INTO history(_id, word) VALUES(('" + id + "'),('" + text + "'))");
//            data = new Model(id, text);
//        }
//        Log.e("TAG", "query successull" + c.toString());
//
//
//        return data;
//
//    }
//


    //history retriving
    public ArrayList<Model> getSearchHistory() throws SQLException {
        ArrayList<Model> data= new ArrayList<>();
        String query = "SELECT _id,word FROM history";
        Cursor row = database.rawQuery(query, null);
        if (row != null) {
            row.moveToFirst();
            while(!row.isAfterLast()){
                int id=row.getInt(row.getColumnIndex("_id"));
                String word=row.getString(row.getColumnIndex("word"));
                data.add(new Model(id,word));
                // do what ever you want here
                row.moveToNext();
            }

            row.close();
        }
        else
            data=null;

        return data;
    }


    //DELETE ward from history/REMOVE
    public boolean delete(int id)
    {
        try {
            int result=database.delete("history","_id "+" =?",new String[]{String.valueOf(id)});
            if(result>0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public long insertBookmark(int id,String text)
    {
        Cursor row = null;

        row = database.rawQuery("SELECT _id FROM history where _id =" + id, null);
        if (row.getCount() == 0){
            //database.execSQL("INSERT INTO history(_id, word) VALUES(('" + id + "'),('" + text + "'))");
            ContentValues values=new ContentValues();
            values.put("_id",id);
            values.put("word",text);
            return database.insert("bookmark",null,values);

        } else{
            return -1;
        }
    }


    //bookmark retriving
    public ArrayList<Model> getBookmark() throws SQLException {
        ArrayList<Model> data= new ArrayList<>();
        String query = "SELECT _id,word FROM bookmark";
        Cursor row = database.rawQuery(query, null);
        if (row != null) {
            row.moveToFirst();
            while(!row.isAfterLast()){
                int id=row.getInt(row.getColumnIndex("_id"));
                String word=row.getString(row.getColumnIndex("word"));
                data.add(new Model(id,word));
                // do what ever you want here
                row.moveToNext();
            }

            row.close();
        }
        else
            data=null;

        return data;
    }


    //DELETE ward from bookmark
    public boolean deleteBookmarkWord(int id)
    {
        try {
            int result=database.delete("bookmark","_id "+" =?",new String[]{String.valueOf(id)});
            if(result>0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public Cursor getMeaning(String text)
    {
        Cursor c= database.rawQuery("SELECT en_definition,example,synonyms,antonyms FROM words WHERE en_word==UPPER('"+text+"')",null);
        return c;
    }

    public Cursor getSuggestions(String text)
    {
        Cursor c= database.rawQuery("SELECT _id, en_word FROM words WHERE en_word LIKE '"+text+"%' LIMIT 40",null);
        return c;
    }

    public Cursor getHistory()
    {
        Cursor c= database.rawQuery("select distinct  word, en_definition from history h join words w on h.word==w.en_word order by h._id desc",null);
        return c;
    }


    public void  deleteHistory(int id)
    {
        database.execSQL("DELETE FROM history WHERE _id = id;");
    }

    public void  deleteAllHistory()
    {
        database.execSQL("DELETE  FROM history");
    }

    public void  deleteAllBookmark()
    {
        database.execSQL("DELETE  FROM bookmark");
    }
}
