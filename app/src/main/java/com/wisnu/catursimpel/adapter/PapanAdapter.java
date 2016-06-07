package com.wisnu.catursimpel.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.wisnu.catursimpel.Papan;
import com.wisnu.catursimpel.R;

/**
 * Created by private on 07/06/2016.
 */
public class PapanAdapter extends CustomAdapter<Papan> {
    private Context context;

    public PapanAdapter(Context context){
        this.context = context;
    }

    @Override
    public ViewHolder<Papan> createHolder() {
        return new ViewHolder<Papan>() {
            ImageView warna;

            @Override
            public View createView(Context context) {
                View view = LayoutInflater.from(context).inflate(R.layout.papan, null);
                warna = (ImageView) view.findViewById(R.id.papanItem);
                return view;
            }

            @Override
            public void bind(int pos, Papan papan) {
                if (papan.getWarna().equals("darkblue")){
                    warna.setBackgroundResource(R.color.darkblue);
                }else if (papan.getWarna().equals("blue")){
                    warna.setBackgroundResource(R.color.blue);
                }
            }
        };
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
