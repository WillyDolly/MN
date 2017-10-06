package com.popland.pop.mn;


import android.app.Dialog;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.*;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.popland.pop.mn.adapters.AdapterPagerFragment;
import com.popland.pop.mn.fragment.Fragment_Remember;
import com.popland.pop.mn.fragment.Fragment_Retrieve;
import com.popland.pop.mn.fragment.Fragment_hethong;

public class MainActivity extends AppCompatActivity {
    public static DeactiveViewPager viewPager;
    public static ImageButton ibHethong, ibNho, ibHoituong;
    public static  AdapterPagerFragment apf;
    public static MajorDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        database = new MajorDatabase(MainActivity.this);
        database.checkDB();

        viewPager = (DeactiveViewPager)findViewById(R.id.viewPager);
        ibHethong = (ImageButton)findViewById(R.id.ibHethong);
        ibNho = (ImageButton)findViewById(R.id.ibIn);
        ibHoituong = (ImageButton)findViewById(R.id.ibOut);

         apf = new AdapterPagerFragment(getSupportFragmentManager());
        apf.AddFragment(new Fragment_hethong());
        apf.AddFragment(new Fragment_Remember());
        viewPager.setAdapter(apf);
        viewPager.setEnabled(false);

        ibHethong.setBackgroundColor(Color.GREEN);
    }

    public void Hethong(View v){
       viewPager.setCurrentItem(0,true);
        ibHethong.setBackgroundColor(Color.GREEN);
        ibNho.setBackgroundColor(Color.LTGRAY);
    }

    public void Nho(View v){
                viewPager.setCurrentItem(1, true);
                ibHethong.setBackgroundColor(Color.LTGRAY);
                ibNho.setBackgroundColor(Color.GREEN);
    }
}
