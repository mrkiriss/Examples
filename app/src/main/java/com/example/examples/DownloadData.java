package com.example.examples;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DownloadData {

    private AssetManager assetManager;

    private final String[] countries=new String[]{"AUD","AZN","GBP","AMD","BYN","BGN","BRL","HUF","DKK","USD","EUR","JPY"};

    public DownloadData(Context context){
        assetManager=context.getAssets();
    }

    public List<Currency> downloadData() throws JSONException {
        String content = getContentFromUrl();
        List<Currency> result = parseToContent(content);
        return result;
    }

    private String getContentFromUrl(){
        String result = "";
        try {
            //загружаем данные
            Log.i("DownloadData", "Start getting content");

            URL url = new URL("https://www.cbr-xml-daily.ru/latest.js");

            Scanner in = new Scanner((InputStream) url.getContent());

            while (in.hasNext()) {
                result += in.next();
            }
        } catch (Exception e) {
            Log.i("DownloadData", "download error");
            e.printStackTrace();
        }

        return result;
    }
    private List<Currency> parseToContent(String content) throws JSONException {
        List<Currency> result = new ArrayList<>();
        JSONObject country_money = new JSONObject(content).getJSONObject("rates");

        for (String country: countries){
            result.add(new Currency(getBitmapFromAsset(country), country, 1/country_money.getDouble(country)));
        }

        return  result;
    }

    private Bitmap getBitmapFromAsset(String filePath) {

        InputStream istr;
        android.graphics.Bitmap bitmap = null;
        try {
            istr = assetManager.open("img/"+filePath+".png");
            bitmap = BitmapFactory.decodeStream(istr);
            //bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/2, bitmap.getHeight()/2, false);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }
}
