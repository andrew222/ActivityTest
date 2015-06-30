package com.example.andrewyang.acitvitytest;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by andrewyang on 2015/6/29.
 */
public class LayoutOne extends Fragment {
    public static Fragment newInstance(Context context) {
        LayoutOne f = new LayoutOne();
        return  f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.layout_one, null);
        TextView title = (TextView) root.findViewById(R.id.title);
        return  root;
    }
}
