package com.popland.pop.mn.model;

/**
 * Created by hai on 18/05/2017.
 */

public class NuIm {
    public String so , hint, keyword;
    public byte[] image;

    public NuIm(String so,byte[] image){
        this.so = so;
        this.image = image;
    }

    public NuIm(String so, String hint, String keyword, byte[] image){
        this.so = so;
        this.hint = hint;
        this.keyword = keyword;
        this.image = image;
    }
}
