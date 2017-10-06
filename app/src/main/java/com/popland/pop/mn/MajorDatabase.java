package com.popland.pop.mn;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.widget.Toast;

import com.popland.pop.mn.model.NuIm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by hai on 26/06/2017.
 */

public class MajorDatabase extends SQLiteOpenHelper {
Context context;
public static String DBNAME = "Major.db";
public static String DBLOCATION = "/data/data/com.popland.pop.mn/databases/";
SQLiteDatabase sqLiteDatabase;

    MajorDatabase(Context context){
        super(context,DBNAME,null,1);
        this.context = context;
    }

    public void checkDB(){
        File fileDB = context.getDatabasePath(DBNAME);
        if(!fileDB.exists()){
            this.getReadableDatabase();// take time to create/open read-only DB but can be writable
            if(copyDB())
                Toast.makeText(context,"copy ok",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(context,"copy error",Toast.LENGTH_SHORT).show();
        }else
            return;
    }

    public boolean copyDB(){
        try {
            InputStream inputStream = context.getAssets().open(DBNAME);
            String filePath = DBLOCATION + DBNAME;
            FileOutputStream fos = new FileOutputStream(filePath);
            byte[] bytes = new byte[1024];
            int length;
            while((length = inputStream.read(bytes)) > 0){
                fos.write(bytes);
            }
            fos.flush();
            fos.close();
        return true;
        } catch (IOException e) {
            e.printStackTrace();
        return false;
        }
    }

    public void openDB(){
        String pathDB = context.getDatabasePath(DBNAME).getPath();
        if(sqLiteDatabase!=null && sqLiteDatabase.isOpen())
            return;
        sqLiteDatabase = SQLiteDatabase.openDatabase(pathDB,null,SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDB(){
        if(sqLiteDatabase.isOpen())
                sqLiteDatabase.close();
    }

    public ArrayList<NuIm> DBtoArrayList(int typeDB){
        openDB();
        ArrayList<NuIm> MajorList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM MaSy",null);
        switch(typeDB){
            case 0:
                while(cursor.moveToNext())
                    MajorList.add(new NuIm(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getBlob(4)));
                break;
            case 1:
                while(cursor.moveToNext())
                    MajorList.add(new NuIm(cursor.getString(1),cursor.getBlob(4)));
                break;
        }
        closeDB();
        return MajorList;
    }

    public void updateDB(String so,String newWord,byte[] anh){
        SQLiteDatabase db= getWritableDatabase();
        String update = "UPDATE MaSy Set keyword =?, image =? WHERE number =?";
        SQLiteStatement statement = db.compileStatement(update);
        statement.clearBindings();

        statement.bindString(1,newWord);
        statement.bindBlob(2,anh);
        statement.bindString(3,so);
        statement.executeUpdateDelete();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
