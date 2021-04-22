package com.example.examples;

import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import lombok.Data;

@Data
public class Currency {
    private Bitmap flag;
    private String name;
    private double value;

    public Currency(Bitmap flag, String name, double value){
        this.flag=flag;
        this.name=name;
        this.value=value;
    }
    @BindingAdapter("tools:srcCompat")
    public static void loadImage(ImageView iv, Bitmap bitmap) {
        iv.setImageBitmap(bitmap);
    }
}
