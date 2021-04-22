package com.example.examples;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;

import java.util.List;


public class MainActivity extends AppCompatActivity{

    private MutableLiveData<List<Currency>> data;
    private CurrencyAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data=new MutableLiveData<>();
        adapter = new CurrencyAdapter();
        ((RecyclerView)findViewById(R.id.recycler)).setAdapter(adapter);
        ((RecyclerView)findViewById(R.id.recycler)).setLayoutManager(new LinearLayoutManager(this));

        data.observe((LifecycleOwner) this, currencies -> adapter.setContent(currencies));

        DownloadData downloadData = new DownloadData(this);
        Runnable task = ()->{
            try {
                data.postValue(downloadData.downloadData());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        };
        new Thread(task).start();
    }
}