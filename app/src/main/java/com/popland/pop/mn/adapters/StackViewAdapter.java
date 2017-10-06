package com.popland.pop.mn.adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.popland.pop.mn.Utils.ImageUtils;
import com.popland.pop.mn.model.NuIm;
import com.popland.pop.mn.R;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by hai on 27/05/2017.
 */

public  class StackViewAdapter extends BaseAdapter {
    List<NuIm> MSystem;
    Context context;
    LayoutInflater inflater;

    public StackViewAdapter(Context c,List<NuIm> MSystem) {
        this.context = c;
        this.MSystem = MSystem;
        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return MSystem.size();
    }

    @Override
    public String getItem(int position) {
        return MSystem.get(position).so;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    class ViewHolder {
        TextView tvSo, tvNuKe;
        ImageView iv;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.stackview_custom, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvSo = (TextView) convertView.findViewById(R.id.tvSo);
            viewHolder.tvNuKe = (TextView) convertView.findViewById(R.id.tvNuKe);
            viewHolder.iv = (ImageView) convertView.findViewById(R.id.ivIllustration);
            convertView.setTag(viewHolder);
        }

        final ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.tvSo.setText(MSystem.get(position).so);
        holder.tvNuKe.setHint(MSystem.get(position).hint);
        holder.tvNuKe.setText(MSystem.get(position).keyword);
        byte[] anh = MSystem.get(position).image;
        if(anh!=null) {
//            FileOutputStream fos = null;
//            try {
//            File file = File.createTempFile("tmp",null);
//            fos = new FileOutputStream(file);
//            fos.write(anh);
//            Picasso.with(context).load(file).fit().into(holder.iv);
//            } catch (IOException e) {
//            e.printStackTrace();
//           }
//            finally {
//                    try {
//                        if(fos!=null)
//                           fos.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//            }

            ImageUtils imageUtils = new ImageUtils();
            holder.iv.setImageBitmap(imageUtils.getResizedBitmap(anh,null,200,200));
        }
        return convertView;
    }
}
