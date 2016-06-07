package com.wisnu.catursimpel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.wisnu.catursimpel.Bidak;
import com.wisnu.catursimpel.R;

/**
 * Created by private on 07/06/2016.
 */
public class BidakAdapter extends CustomAdapter<Bidak> {
    private Context context;

    public BidakAdapter(Context context){
        this.context = context;
    }

    @Override
    public ViewHolder<Bidak> createHolder() {
        return new ViewHolder<Bidak>() {
            ImageView status;

            @Override
            public View createView(Context context) {
                View view = LayoutInflater.from(context).inflate(R.layout.bidak, null);
                status = (ImageView) view.findViewById(R.id.status);
                return view;
            }

            @Override
            public void bind(int pos, Bidak bidak) {
                if (bidak.getWarna() != null) {
                    status.setVisibility(View.VISIBLE);
                    if (bidak.getWarna().equals("darkblue")) {
                        switch (bidak.getNama().toLowerCase()) {
                            case "k":
                                status.setImageResource(R.drawable.bk);
                                break;
                            case "q":
                                status.setImageResource(R.drawable.bq);
                                break;
                            case "n":
                                status.setImageResource(R.drawable.bn);
                                break;
                            case "b":
                                status.setImageResource(R.drawable.bb);
                                break;
                            case "r":
                                status.setImageResource(R.drawable.br);
                                break;
                        }
                    } else if (bidak.getWarna().equals("blue")) {
                        switch (bidak.getNama().toLowerCase()) {
                            case "k":
                                status.setImageResource(R.drawable.wk);
                                break;
                            case "q":
                                status.setImageResource(R.drawable.wq);
                                break;
                            case "n":
                                status.setImageResource(R.drawable.wn);
                                break;
                            case "b":
                                status.setImageResource(R.drawable.wb);
                                break;
                            case "r":
                                status.setImageResource(R.drawable.wr);
                                break;

                        }
                    }
                } else {
                    status.setVisibility(View.INVISIBLE);
                }

            }
        };
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}