package com.starwars.lab7;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    ProgressBar progressBar;
    FrameLayout frameLayout;
    FrameLayout frameLayoutRoot;
    RecyclerView recyclerView;
    ArrayList<Result> mResults;
    int height;
    int width;
    double screenInches;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar)findViewById(R.id.progress_circular);
        frameLayout = (FrameLayout)findViewById(R.id.frame);
        recyclerView = (RecyclerView) findViewById(R.id.list);
//        frameLayoutRoot = (FrameLayout) findViewById(R.id.frame_root);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        Log.d(TAG,"width "+width);
        Log.d(TAG,"height "+height);
        double wi=(double)width/(double)displayMetrics.xdpi;
        double hi=(double)height/(double)displayMetrics.ydpi;
        double x = Math.pow(wi,2);
        double y = Math.pow(hi,2);
        screenInches = Math.sqrt(x+y);
        Log.d(TAG,"screenInches "+screenInches);

        GetStarWarsAsyncTask getStarWarsAsyncTask = new GetStarWarsAsyncTask();
        getStarWarsAsyncTask.execute();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                        if (screenInches <=9){
                            frameLayout.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                        }
                        Result result = mResults.get(position);
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.frame, DetailsFragment.newInstance(result));
//                        ft.addToBackStack("DetailsFragment");
                        ft.commit();
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
    }

    @Override
    public void onBackPressed() {
        if (screenInches <= 9){
            if (frameLayout.getVisibility() == View.VISIBLE){
                frameLayout.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }else{
                super.onBackPressed();
            }
        }else {
            super.onBackPressed();
        }
    }

    private class GetStarWarsAsyncTask extends AsyncTask<Void, String, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... strings) {
            try {
                // Creating & connection Connection with url and required Header.
//                URL url = new URL("https://swapi.dev/api/people/?format=json");
                return sendGetReq();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                Log.d(TAG,"result "+result);
                progressBar.setVisibility(View.GONE);
//                JSONObject obj = new JSONObject(result);
//                String name= obj.getString("name");
                StarWarApiResp data =new StarWarApiResp();
                Gson gson = new Gson();
                data= gson.fromJson(result,StarWarApiResp.class);
                mResults = data.getResults();
                recyclerView.setAdapter(new MyStarWarRecyclerViewAdapter(data.getResults()));
//                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                ft.replace(R.id.frame, StarWarFragment.newInstance(data.getResults()));
////                ft.addToBackStack("StarWarFragment");
//                ft.commit();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // HTTP GET request
        private String sendGetReq() throws Exception {
//            https://swapi.dev/api/people/?format=json
            String format="json";
            StringBuilder builder = new StringBuilder("https://swapi.dev/api/people");
            builder.append("?format=");
            builder.append(URLEncoder.encode(format, "UTF-8"));
            URL obj = new URL(builder.toString());
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept-Charset", "UTF-8");

            System.out.println("\nSending request to URL : " + con.getURL().toString());
            System.out.println("Response Code : " + con.getResponseCode());
            System.out.println("Response Message : " + con.getResponseMessage());

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();

            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();

            System.out.println(response.toString());
            return response.toString();

        }

    }
}