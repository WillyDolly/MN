package com.popland.pop.mn.fragment;

import android.app.Dialog;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.StackView;
import android.widget.TextView;
import android.widget.Toast;

import com.popland.pop.mn.MainActivity;
import com.popland.pop.mn.R;
import com.popland.pop.mn.SplashActivity;
import com.popland.pop.mn.adapters.StachViewAdapter2;
import com.popland.pop.mn.adapters.StackViewAdapter;
import com.popland.pop.mn.model.NuIm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by hai on 06/06/2017.
 */
//free 8, allo 246 -> 7 ; 172
public class Fragment_Retrieve extends Fragment{
View v;
ArrayList<NuIm> mangNuIm;
//LinearLayout ll;
GridView gridView;
TextView tvShow;
    String textShow;
    int clickTime = 0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.retrieve_fragment,container,false);
        Toast.makeText(getActivity(),"preload 31",Toast.LENGTH_SHORT).show();
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        Toast.makeText(getActivity(), "preload 32", Toast.LENGTH_SHORT).show();
//        tvShow = (TextView) v.findViewById(R.id.tvShow);
//        gridView = (GridView) v.findViewById(R.id.gridView);
////        ll = (LinearLayout)v.findViewById(R.id.linearLayout);
//        mangNuIm = MainActivity.database.DBtoArrayList(1);
//        gridView.setAdapter(new StachViewAdapter2(getActivity(), mangNuIm));
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                TextView tvSo = (TextView) view.findViewById(R.id.tvSo);
//                String so = tvSo.getText().toString();
//                textShow = tvShow.getText().toString();
//                tvShow.setText(textShow + so);
//
//                String thongbao;
//                if (!so.equals(Fragment_Remember.groups[clickTime])) {
//                    thongbao = "Wrong";
//                    resultDialog(thongbao);
//                }
//                if ((so.equals(Fragment_Remember.groups[clickTime]))
//                        && (clickTime == (Fragment_Remember.groups.length - 1))) {
//                    thongbao = "Well done!";
//                    resultDialog(thongbao);
//                }
//                clickTime++;
//            }
//        });
        super.onViewCreated(view, savedInstanceState);
    }
//        Collections.shuffle(mangNuIm);
//        Log.i("size",mangNuIm.size()+"");
//        int a=0;
//        for(int i=0;i<ll.getChildCount();i++){
//            StackView sv = (StackView) ll.getChildAt(i);
//            sv.setAdapter(new StachViewAdapter2(getActivity(),mangNuIm.subList(a,a+=10)));
//            sv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    TextView tvSo = (TextView) view.findViewById(R.id.tvSo);
//                    String so = tvSo.getText().toString();
//                    textShow = tvShow.getText().toString();
//                    tvShow.setText(textShow+so);
//
//                    String thongbao;
//                    if(!so.equals(Fragment_Remember.groups[clickTime])){
//                        thongbao = "Wrong";
//                        resultDialog(thongbao);
//                    }
//                    if( (so.equals(Fragment_Remember.groups[clickTime]))
//                            && (clickTime==(Fragment_Remember.groups.length-1)) ){
//                        thongbao = "Well done!";
//                        resultDialog(thongbao);
//                    }
//                    clickTime++;
//                }
//            });
//        }
//
//        super.onViewCreated(view, savedInstanceState);
//    }
//

        public void resultDialog(String thongbao){
            final Dialog log = new Dialog(getActivity());
            log.requestWindowFeature(Window.FEATURE_NO_TITLE);
            log.setContentView(R.layout.result_dialog);
            log.setCanceledOnTouchOutside(false);
            TextView tvThongBao = (TextView) log.findViewById(R.id.tvThongBao);
            Button btnOK = (Button)log.findViewById(R.id.btnOk);

            tvThongBao.setText(thongbao);
            btnOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    log.cancel();
                    MainActivity.ibHethong.setEnabled(true);
                    MainActivity.ibNho.setEnabled(true);
                    MainActivity.viewPager.setCurrentItem(1,true);
                    clickTime = 0;
                    tvShow.setText("");

                    MainActivity.ibNho.setBackgroundColor(Color.GREEN);
                    MainActivity.ibHoituong.setBackgroundColor(Color.LTGRAY);
                    Toast.makeText(getActivity(),"count "+MainActivity.apf.getCount(),Toast.LENGTH_SHORT).show();
                }
            });
            log.show();
        }
}
