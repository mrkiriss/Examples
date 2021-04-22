package com.example.examples;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examples.databinding.CurrencyItemBinding;

import java.util.ArrayList;
import java.util.List;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyHolder>{

    List<Currency> currencyList;

    public CurrencyAdapter(){
        currencyList=new ArrayList<>();
    }

    public void setContent( List<Currency> currencyList){
        this.currencyList=currencyList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CurrencyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CurrencyItemBinding binding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.currency_item, parent, false);
        return new CurrencyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyHolder holder, int position) {
        holder.bind(currencyList.get(position));
    }

    @Override
    public int getItemCount() {
        return currencyList.size();
    }

    public static class CurrencyHolder extends RecyclerView.ViewHolder{

        CurrencyItemBinding binding;

        public CurrencyHolder(@NonNull CurrencyItemBinding binding) {
            super(binding.card);
            this.binding=binding;
        }

        public void bind(Currency currency){
            binding.setViewModel(currency);
        }
    }
}
