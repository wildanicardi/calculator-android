package com.example.minggupertama;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;


public class GalleryAdapter extends ArrayAdapter {
    private List<Gallery> galleries;
    private ImageView img;
    private Activity context;

    public GalleryAdapter(Activity context, List<Gallery> galleries) {
        super(context, R.layout.gallery_thumbnail, galleries);
        this.context = context;
        this.galleries = galleries;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.gallery_thumbnail, null, true);

        TextView lbl_read = (TextView) listViewItem.findViewById(R.id.lbl_name);
        TextView lbl_id = (TextView) listViewItem.findViewById(R.id.lbl_id);
        img = (ImageView) listViewItem.findViewById(R.id.img);

        Gallery gallery = galleries.get(position);
        lbl_read.setText(gallery.getName());
        lbl_id.setText(gallery.getId());

        Glide.with(getContext()).load("https://docs.google.com/uc?id=" + gallery.getId())
                .thumbnail(0.2f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(img);


        return listViewItem;
    }

}
