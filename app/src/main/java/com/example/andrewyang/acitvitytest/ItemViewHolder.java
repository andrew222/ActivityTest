package com.example.andrewyang.acitvitytest;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    public final TextView textView;

    public ItemViewHolder(View itemView, TextView textView) {
        super(itemView);
        this.textView = textView;
    }
}