package com.popland.pop.mn.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.popland.pop.mn.R;
import com.popland.pop.mn.Utils.ImageUtils;
import com.popland.pop.mn.model.NuIm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by hai on 06/06/2017.
 */

public class StachViewAdapter2 extends BaseAdapter {
    List<NuIm> MSystem;
    Context context;
    LayoutInflater inflater;

     public StachViewAdapter2(Context c,List<NuIm> MSystem) {
        this.context = c;
        this.MSystem = MSystem;
        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return MSystem.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

class ViewHolder{
    ImageView imageView;
    TextView textView;
}
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.stackview_horizontal,parent,false);
            viewHolder.imageView = (ImageView)convertView.findViewById(R.id.ivAnh);
            viewHolder.textView = (TextView)convertView.findViewById(R.id.tvSo);
            convertView.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder)convertView.getTag();
        holder.textView.setText(MSystem.get(position).so);
        byte[] anh = MSystem.get(position).image;
        if(anh!=null) {
//            FileOutputStream fos = null;
//            try {
//                File file = File.createTempFile("tmp",null);
//                fos = new FileOutputStream(file);
//                fos.write(anh);
//                Picasso.with(context).load(file).fit().into(holder.imageView);
//            }catch (IOException exc){
//                exc.printStackTrace();
//            }
//            finally {
//                try {
//                    if(fos!=null)
//                        fos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }

            ImageUtils imageUtils = new ImageUtils();
            holder.imageView.setImageBitmap(imageUtils.getResizedBitmap(anh,null,200,200));
        }
        return convertView;
    }
}
