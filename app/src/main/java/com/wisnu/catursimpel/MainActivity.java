package com.wisnu.catursimpel;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import com.wisnu.catursimpel.adapter.BidakAdapter;
import com.wisnu.catursimpel.adapter.PapanAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.net.SocketFactory;

public class MainActivity extends AppCompatActivity {
    public static final String SERVER = "http://www.xinuc.org/";
    public static final int PORT = 7387;
    Socket mSocket;
    GridView papan;
    GridView bidak;
    BidakAdapter mBidakAdapter;
    PapanAdapter mPapanAdapter;
    List<Papan> mPapanList;
    List<Bidak> mBidakList;
    String[] data = new String[]{
            "Nh3", "Bc2", "Qh7", "Ke3", "Rb5", "nb6", "qa6", "ke4", "bg5", "rg8",
    };

    int[] baris = new int[]{
      8,7,6,5,4,3,2,1
    };

    String[] kolom = new String[]{
      "a","b","c","d","e","f","g","h"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPapanAdapter = new PapanAdapter(this);

        mBidakAdapter = new BidakAdapter(this);

        createPapan();

        mPapanAdapter.setList(mPapanList);
        mPapanAdapter.notifyDataSetChanged();

        papan = (GridView) findViewById(R.id.papanview);
        bidak = (GridView) findViewById(R.id.bidakview);

        papan.setAdapter(mPapanAdapter);

        bidak.setAdapter(mBidakAdapter);

        new AmbilData().execute();
    }


    private void createPapan() {
        mPapanList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Papan papan = new Papan();
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        papan.setWarna("blue");
                    } else {
                        papan.setWarna("darkblue");
                    }
                } else {
                    if (j % 2 == 0) {
                        papan.setWarna("darkblue");
                    }else{
                        papan.setWarna("blue");
                    }
                }
                papan.setKolom(kolom[j]);
                papan.setBaris(baris[i]);
                mPapanList.add(papan);
            }
        }
    }

    class AmbilData extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... params) {
            try {
                mSocket = SocketFactory.getDefault().createSocket(URI.create(SERVER).getHost(), PORT);
                mSocket.setKeepAlive(true);

                BufferedReader reader = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine())!= null){
                    data = line.split(" ");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mBidakList = new ArrayList<Bidak>(Collections.nCopies(64,new Bidak()));
                            for (int i = 0; i < data.length; i++) {
                                Bidak bidak = new Bidak();
                                if (!data[i].substring(0, 1).toUpperCase().equals(data[i].substring(0, 1))) {
                                    bidak.setWarna("darkblue");
                                }else {
                                    bidak.setWarna("blue");
                                }
                                bidak.setNama(data[i].substring(0,1));
                                bidak.setKolom(data[i].substring(1,2));
                                bidak.setBaris(Integer.valueOf(data[i].substring(2,3)));
                                int pos = ((8 - bidak.getBaris()) * 8 )+ Util.getIntChar(bidak.getKolom());
                                mBidakList.set(pos, bidak);
                            }
                            mBidakAdapter.setList(mBidakList);
                            mBidakAdapter.notifyDataSetChanged();
                        }
                    });
                    stringBuilder.append(line).append("\n");
                }

            } catch (IOException e) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
        }
    }
}
