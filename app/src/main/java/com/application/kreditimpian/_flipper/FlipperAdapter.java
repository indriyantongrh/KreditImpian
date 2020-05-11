package com.application.kreditimpian._flipper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.application.kreditimpian.Model.ModelImagePromo.DataItem;
import com.application.kreditimpian.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by indriyanto Nugroho on 6 Mei 2020.
 */
public class FlipperAdapter extends BaseAdapter {
    private Context mCtx;
    private ArrayList<DataItem> ImagePromo;

    public FlipperAdapter(Context mCtx, ArrayList<DataItem> ImagePromo) {
        this.mCtx = mCtx;
        this.ImagePromo = ImagePromo;
    }

    @Override
    public int getCount() {
        return ImagePromo.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DataItem dataItem = ImagePromo.get(position);

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.flipper_items, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);

        Glide.with(mCtx).load(dataItem.getImages()).into(imageView);
        return view;
    }
}
