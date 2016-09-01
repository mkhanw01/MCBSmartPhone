package com.example.waseem.mcbosp;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Waseem on 28-04-2016.
 */
public class SQLiteHelper {
    public static ArrayList<String[]> DATA = new ArrayList<String[]>();
    private static final String DATABASE_NAME = "banklogin.db";
    private static final int DATABASE_VERSION = 3;
    private static final String TABLE_NAME = "banklogin";
    private Context context;
    private SQLiteDatabase db = null;
    OpenHelper openHelper;

    public SQLiteHelper(Context context) {
        this.context = context;

        if (db != null)
            if (db.isOpen())
                db.close();

        openHelper = new OpenHelper(this.context);
        this.db = openHelper.getWritableDatabase();
    }

    public void close() {
        if (openHelper != null) {
            openHelper.close();
        }
    }
    public void deleteAll() {
        deleteTable(TABLE_NAME);
    }
    public void deleteTable(String tn) {
        this.db.delete(tn, null, null);
    }
    public int insertData(String username, String pass, String mailid, String contact) {
        int entryId = 0;
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("pass", pass);
        values.put("mailid", mailid);
        values.put("contact",contact);
        entryId = (int) this.db.insert(TABLE_NAME, null, values);
        return entryId;
    }






    public ArrayList<String[]> selectalldatabase() {
        Cursor c = null;
        DATA.clear();
        String[] columns = new String[] { "id", "username", "pass", "mailid","contact" };
        c = db.query(TABLE_NAME, columns, null, null, null, null,null);
        if (c.moveToFirst()) {
            do {
                if (c.getColumnCount() == 5) {
                    String[] str = new String[4];
                    str[0] = c.getString(1);
                    str[1] = c.getString(2);
                    str[2] = c.getString(3);
                    str[3] = c.getString(4);



                    DATA.add(str);
                }
            } while (c.moveToNext());
        }

        if (c != null && !c.isClosed()) {
            c.close();
        }

        return DATA;
    }



    static class OpenHelper extends SQLiteOpenHelper {

        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL("CREATE TABLE " + TABLE_NAME + "("
                    + "id INTEGER PRIMARY KEY, " + "username TEXT, "
                    + "pass TEXT, " + "mailid TEXT," + "contact TEXT)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w("Catch DB",
                    "Upgrading database, this will drop tables and recreate.");
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }



    public String loginData(String h) {
        //String g=h;
        //System.out.print(g);


        // TODO Auto-generated method stub
        Cursor c1 = null;
        DATA.clear();
        String[] columns = new String[] {  "username" };

        c1 = db.query(TABLE_NAME, columns,"username=?", new String[] {h}, null,null,null,null);
        if (c1.moveToNext()) {


            return "Login Failed";
        }
        else
        {
            return "Login Successfully";
        }






    }


}

