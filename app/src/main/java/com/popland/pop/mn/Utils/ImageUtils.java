package com.popland.pop.mn.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

/**
 * Created by hai on 22/06/2017.
 */

public class ImageUtils {
int newW, newH;

    public Bitmap getResizedBitmap(byte[] by,Bitmap source,int newWidth,int newHeight){
        Bitmap bitmap;
        newW = newWidth;
        newH = newHeight;
        if(source==null) {
            bitmap = BitmapFactory.decodeByteArray(by, 0, by.length);
            return formula(bitmap);
        }else{
            return formula(source);
        }
    }

    public Bitmap formula(Bitmap bm){
//        int width = bm.getWidth();
//        int height = bm.getHeight();
//        float scaleWidth = ((float) newW / width);
//        float scaleHeight = ((float) newH / height);
//        Matrix matrix = new Matrix();
//        matrix.postScale(scaleWidth, scaleHeight);
//
//        bm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        Bitmap scaledBitmap = Bitmap.createBitmap(newW,newH, Bitmap.Config.ARGB_8888);
        float ratioX = newW / (float)bm.getWidth();
        float ratioY = newH / (float)bm.getHeight();
        float middleX = newW / 2.0f;
        float middleY = newH / 2.0f;

        Matrix matrix = new Matrix();
        matrix.setScale(ratioX,ratioY,middleX,middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(matrix);
        canvas.drawBitmap(bm,middleX- bm.getWidth()/2,middleY- bm.getHeight()/2,new Paint(Paint.FILTER_BITMAP_FLAG));
        return scaledBitmap;
    }
}
