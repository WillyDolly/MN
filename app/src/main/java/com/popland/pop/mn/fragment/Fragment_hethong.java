package com.popland.pop.mn.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.StackView;
import android.widget.TextView;
import android.widget.Toast;

import com.popland.pop.mn.MainActivity;
import com.popland.pop.mn.Utils.ImageUtils;
import com.popland.pop.mn.model.NuIm;
import com.popland.pop.mn.R;
import com.popland.pop.mn.SplashActivity;
import com.popland.pop.mn.adapters.StackViewAdapter;
import com.qozix.tileview.TileView;
import com.qozix.tileview.markers.MarkerLayout;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hai on 17/05/2017.
 */
//free 16 . allo 121 -> 3;106
public class Fragment_hethong extends Fragment {
View v;
//    LinearLayout linear;
    final int CAMERA_CODE = 111;
    final int GALLERY_CODE = 222;
    ImageView ivPic;
    Dialog dialog;
//    ArrayList<List<NuIm>> listCon;
//    ArrayList<StackViewAdapter> mangAdapter;
    ImageUtils imageUtils = new ImageUtils();
ArrayList<NuIm> MajorSystem = new ArrayList<>();
 TileView tileView;
    int index2;
 @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {//run before onCreatedView
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser) {
            if(MajorSystem.size()==0) {
                Toast.makeText(getActivity(), "visible", Toast.LENGTH_SHORT).show();
                MajorSystem = MainActivity.database.DBtoArrayList(0);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
          //v = inflater.inflate(R.layout.fragment_hethong,container,false);
//        linear = (LinearLayout) v.findViewById(R.id.linearLayout);
//        Toast.makeText(getActivity(),"createView",Toast.LENGTH_SHORT).show();
        tileView = new TileView(getActivity());
        tileView.setSize(480,3520);
        tileView.addDetailLevel(1f,"tiles/land/1000/%d_%d.png");
        tileView.addDetailLevel(0.5f,"tiles/land/500/%d_%d.png");
        tileView.addDetailLevel(0.25f,"tiles/land/250/%d_%d.png");
        tileView.addDetailLevel(0.125f,"tiles/land/125/%d_%d.png");
        tileView.setScaleLimits(0,5);
        tileView.setScale(0);
        tileView.defineBounds(0,0,29,219);
        tileView.setShouldRenderWhilePanning(true);
        return tileView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int index1 = 0;
        for(double[] position: positions) {
            TextView number = new TextView(getActivity());
            number.setText(MajorSystem.get(index1).so);
            number.setTextColor(Color.BLUE);
            number.setTag(index1);
            tileView.addMarker(number, position[0], position[1], -0.5f, -1f);
            index1++;
        }
        tileView.setMarkerTapListener(mMarkerTapListener);
    }

    public MarkerLayout.MarkerTapListener mMarkerTapListener = new MarkerLayout.MarkerTapListener() {
        @Override
        public void onMarkerTap(View view, int x, int y) {
            index2 = (int) view.getTag();
            dialog = new Dialog(getActivity());
                        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.edit_dialog);
                        dialog.setCanceledOnTouchOutside(true);
                        //final TextView tvNumber = (TextView) dialog.findViewById(R.id.tvNumber);
                        final EditText edtWord = (EditText) dialog.findViewById(R.id.edtWord);
                         ivPic = (ImageView) dialog.findViewById(R.id.ivPic);
                        ImageButton ibPhoto = (ImageButton) dialog.findViewById(R.id.ibPhoto);
                        ImageButton ibGallery = (ImageButton) dialog.findViewById(R.id.ibGallery);
                        ImageButton ibSave = (ImageButton) dialog.findViewById(R.id.ibSave);

                        //tvNumber.setText(listCon.get(order).get(position).so);
                        edtWord.setHint(MajorSystem.get(index2).hint);
                        edtWord.setText(MajorSystem.get(index2).keyword);
                        byte[] hai01 = MajorSystem.get(index2).image;
                        if (hai01 != null) {
                            ivPic.setImageBitmap(imageUtils.getResizedBitmap(hai01,null,400,400));
                        }
            ibPhoto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent in = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(in, CAMERA_CODE);
                            }
                        });

            ibGallery.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(intent, GALLERY_CODE);
                            }
                        });

            ibSave.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                               String newWord = edtWord.getText().toString();
                                MainActivity.database.updateDB(MajorSystem.get(index2).so, newWord, ImageView_To_ByteArray(ivPic));
                                MajorSystem.set(index2, new NuIm(MajorSystem.get(index2).so, MajorSystem.get(index2).hint, newWord, ImageView_To_ByteArray(ivPic)));
                               dialog.dismiss();
                              Toast.makeText(getActivity(), "update ok", Toast.LENGTH_LONG).show();
                           }
                        });
            dialog.show();
        }
    };

//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

//        //1.ArrayList extract data from dataBase
//
//        //2. Handle StackView's events
//        if(MajorSystem.size()>0) {
//            Log.i("AAAAA",MajorSystem.size()+"");
//            Toast.makeText(getActivity(),"viewCreated",Toast.LENGTH_SHORT).show();
//            int a = 0;
//            listCon = new ArrayList<>();
//            mangAdapter = new ArrayList<>();
//            for (int i = 0; i < linear.getChildCount(); i++) {
//                //2.1 show database in StackView
//                final StackView sv = (StackView) linear.getChildAt(i);
//                listCon.add(MajorSystem.subList(a,a+=10));
//                mangAdapter.add(new StackViewAdapter(getActivity(), listCon.get(i)));
//                sv.setAdapter(mangAdapter.get(i));
//                //2.2 NestedScrolling Handle
//                sv.setOnTouchListener(new View.OnTouchListener() {
//                    @Override
//                    public boolean onTouch(View v, MotionEvent event) {
//                        sv.requestDisallowInterceptTouchEvent(true);// long slide on sv can scroll parent
//                        return false;
//                    }
//                });
//                //2.3 show Edit Dialog when a View clicked
//                sv.setOnItemClickListener(new AdapterView.OnItemClickListener() {  //for loop skip this block
//                    @Override //drag down != click
//                    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
//                        TextView tvSo = (TextView) view.findViewById(R.id.tvSo);// Item = view
//                        //determine data list's order
//                        switch (tvSo.getText().toString().length()) {
//                            case 1:
//                                order = 0;
//                                break;
//                            case 2:
//                                char c = tvSo.getText().toString().charAt(0);
//                                order = Integer.parseInt(c + "") + 1;
//                                break;
//                        }
//
//                        dialog = new Dialog(getActivity());
//                        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//                        dialog.setContentView(R.layout.edit_dialog);
//                        dialog.setCanceledOnTouchOutside(true);
//                        final TextView tvNumber = (TextView) dialog.findViewById(R.id.tvNumber);
//                        final EditText edtWord = (EditText) dialog.findViewById(R.id.edtWord);
//                        ivPic = (ImageView) dialog.findViewById(R.id.ivPic);
//                        ImageButton ibPhoto = (ImageButton) dialog.findViewById(R.id.ibPhoto);
//                        ImageButton ibGallery = (ImageButton) dialog.findViewById(R.id.ibGallery);
//                        ImageButton ibSave = (ImageButton) dialog.findViewById(R.id.ibSave);
//                        tvNumber.setText(listCon.get(order).get(position).so);
//                        edtWord.setHint(listCon.get(order).get(position).hint);
//                        edtWord.setText(listCon.get(order).get(position).keyword);
//                        byte[] hai01 = listCon.get(order).get(position).image;
//                        if (hai01 != null) {
//                            ivPic.setImageBitmap(imageUtils.getResizedBitmap(hai01,null,400,400));
//                        }
//
//                        ibPhoto.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                Intent in = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                                startActivityForResult(in, CAMERA_CODE);
//                            }
//                        });
//
//                        ibGallery.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                Intent intent = new Intent(Intent.ACTION_PICK);
//                                intent.setType("image/*");
//                                startActivityForResult(intent, GALLERY_CODE);
//                            }
//                        });
//
//                        ibSave.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                String newWord = edtWord.getText().toString();
//                                String so = tvNumber.getText().toString();
//                                MainActivity.database.updateDB(so, newWord, ImageView_To_ByteArray(ivPic));
//                                listCon.get(order).set(position, new NuIm(so, listCon.get(order).get(position).hint, newWord, ImageView_To_ByteArray(ivPic)));
//                                mangAdapter.get(order).notifyDataSetChanged();
//                                dialog.dismiss();
//                                Toast.makeText(getActivity(), "update ok", Toast.LENGTH_LONG).show();
//                            }
//                        });
//                        dialog.show();
//                        // Toast.makeText(getActivity(),position+"_"+tvSo.getText().toString(),Toast.LENGTH_LONG).show();
//                    }
//                });
//            }
//        }
//        super.onViewCreated(view, savedInstanceState)
// }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==getActivity().RESULT_OK && null!=data) {
            switch (requestCode){
                case CAMERA_CODE:
                    Bitmap bm = (Bitmap) data.getExtras().get("data");
                    ivPic.setImageBitmap(imageUtils.getResizedBitmap(null,bm,400,400));
                    break;
                case GALLERY_CODE:
                    try {
                        Uri uri = data.getData();
                        InputStream inputStream = null;
                        inputStream = getActivity().getContentResolver().openInputStream(uri);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        ivPic.setImageBitmap(imageUtils.getResizedBitmap(null,bitmap,400,400));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    //Picasso.with(getActivity()).load(uri).fit().into(ivPic);
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        if(dialog!=null && dialog.isShowing())
            dialog.dismiss();//avoid leaked Window as Fragment destroyed
        super.onDestroy();
    }

    byte[] ImageView_To_ByteArray(ImageView iv){
        BitmapDrawable drawable = (BitmapDrawable) iv.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
        byte[] mang = baos.toByteArray();
        return mang;
    }

    ArrayList<double[]> positions = new ArrayList<>();
    {positions.add(new double[]{1,217});
        positions.add(new double[]{16,217});
        positions.add(new double[]{25,217});
        positions.add(new double[]{28,214});
        positions.add(new double[]{27,204});
        positions.add(new double[]{21,205});
        positions.add(new double[]{16,205});
        positions.add(new double[]{12,205});
        positions.add(new double[]{10,208});
        positions.add(new double[]{4,206});

        positions.add(new double[]{2,194});
        positions.add(new double[]{9,192});
        positions.add(new double[]{16,194});
        positions.add(new double[]{21,196});
        positions.add(new double[]{25,196});
        positions.add(new double[]{27,186});
        positions.add(new double[]{25,180});
        positions.add(new double[]{18,182});
        positions.add(new double[]{11,184});
        positions.add(new double[]{4,180});

        positions.add(new double[]{2,171});
        positions.add(new double[]{7,172});
        positions.add(new double[]{12,174});
        positions.add(new double[]{21,171});
        positions.add(new double[]{28,172});
        positions.add(new double[]{26,169});
        positions.add(new double[]{27,161});
        positions.add(new double[]{20,163});
        positions.add(new double[]{12,160});
        positions.add(new double[]{2,164});

        positions.add(new double[]{1,154});
        positions.add(new double[]{5,153});
        positions.add(new double[]{9,155});
        positions.add(new double[]{16,150});
        positions.add(new double[]{23,150});
        positions.add(new double[]{27,152});
        positions.add(new double[]{27,141});
        positions.add(new double[]{16,138});
        positions.add(new double[]{9,145});
        positions.add(new double[]{5,140});

        positions.add(new double[]{3,131});
        positions.add(new double[]{9,129});
        positions.add(new double[]{15,135});
        positions.add(new double[]{21,129});
        positions.add(new double[]{26,124});
        positions.add(new double[]{24,127});
        positions.add(new double[]{15,115});
        positions.add(new double[]{15,125});
        positions.add(new double[]{5,120});
        positions.add(new double[]{5,125});

        positions.add(new double[]{3,116});
        positions.add(new double[]{8,109});
        positions.add(new double[]{14,111});
        positions.add(new double[]{21,109});
        positions.add(new double[]{27,116});
        positions.add(new double[]{25,104});
        positions.add(new double[]{20,104});
        positions.add(new double[]{15,104});
        positions.add(new double[]{10,104});
        positions.add(new double[]{5,104});

        positions.add(new double[]{1,86});
        positions.add(new double[]{12,86});
        positions.add(new double[]{16,92});
        positions.add(new double[]{19,93});
        positions.add(new double[]{28,91});
        positions.add(new double[]{27,86});
        positions.add(new double[]{27,82});
        positions.add(new double[]{15,80});
        positions.add(new double[]{16,85});
        positions.add(new double[]{8,83});

        positions.add(new double[]{2,78});
        positions.add(new double[]{6,77});
        positions.add(new double[]{9,77});
        positions.add(new double[]{15,77});
        positions.add(new double[]{18,73});
        positions.add(new double[]{24,70});
        positions.add(new double[]{20,71});
        positions.add(new double[]{15,72});
        positions.add(new double[]{11,72});
        positions.add(new double[]{5,70});

        positions.add(new double[]{3,55});
        positions.add(new double[]{11,55});
        positions.add(new double[]{19,55});
        positions.add(new double[]{24,54});
        positions.add(new double[]{27,54});
        positions.add(new double[]{24,52});
        positions.add(new double[]{27,51});
        positions.add(new double[]{26,44});
        positions.add(new double[]{17,44});
        positions.add(new double[]{7,44});

        positions.add(new double[]{2,38});
        positions.add(new double[]{7,32});
        positions.add(new double[]{10,32});
        positions.add(new double[]{13,34});
        positions.add(new double[]{19,36});
        positions.add(new double[]{26,26});
        positions.add(new double[]{20,26});
        positions.add(new double[]{18,27});
        positions.add(new double[]{14,28});
        positions.add(new double[]{2,23});

        positions.add(new double[]{2,15});
        positions.add(new double[]{8,11});
        positions.add(new double[]{13,14});
        positions.add(new double[]{14,9});
        positions.add(new double[]{22,11});
        positions.add(new double[]{24,6});
        positions.add(new double[]{26,2});
        positions.add(new double[]{17,4});
        positions.add(new double[]{11,5});
        positions.add(new double[]{5,2});}
}

//C:\Users\hai\AppData\Local\Android\sdk1\platform-tools