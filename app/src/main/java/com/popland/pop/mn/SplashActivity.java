package com.popland.pop.mn;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.popland.pop.mn.fragment.Fragment_Remember;
import com.popland.pop.mn.fragment.Fragment_hethong;

import java.nio.ByteBuffer;
//free 6 , allo 27 -> 3;24
public class SplashActivity extends AppCompatActivity {
Button btn;
//public static SystemDataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        btn = (Button)findViewById((R.id.btnBatDau));

//        dataBase = new SystemDataBase(this,"MajorSystem.db",null,1);
//        dataBase.queryData("CREATE TABLE IF NOT EXISTS MaSy(id INTEGER PRIMARY KEY AUTOINCREMENT," +
//                "so VARCHAR, hint VARCHAR, keyword VARCHAR, pic BLOB)");//without table(not data), database null
//        Cursor cursor = dataBase.getData("SELECT * FROM MaSy");
//        if(cursor.getCount() == 0) {
//            Log.i("dataBase: ", "null");
//            String[] letters = getResources().getStringArray(R.array.MajorSystem);
//            String[][] hint = new String[11][10];
//            String[][] so = new String[11][10];
//            for (int r = 0; r < 11; r++) {
//                for (int c = 0; c < 10; c++) {
//                    if (r == 0) {
//                        so[r][c] = String.valueOf(c);
//                        hint[r][c] = letters[c];
//                    } else {
//                        so[r][c] = String.valueOf(r - 1) + c;
//                        hint[r][c] = letters[r - 1] + "   " + letters[c];
//                    }
//                }
//            }
//            //add default values to dataBase
//            for(int r=0;r<11;r++){
//                for(int c=0;c<10;c++){
//                    dataBase.Insert_Data(so[r][c],hint[r][c],"");
//                }
//            }
//        }
    }

    public void Move(View v){
        Intent i = new Intent(SplashActivity.this,MainActivity.class);
        startActivity(i);
    }
}
