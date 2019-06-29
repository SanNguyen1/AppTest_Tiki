package com.dmt.sannguyen.test_tiki_app;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> Ten = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // goi ham passdata de lay du lieu tu link url
        new passdata().execute("https://raw.githubusercontent.com/tikivn/android-home-test/v2/keywords.json");

    }

    public class passdata extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings) {

            // get du lieu tu URL
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStream = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStream);
                String line = ""  ;
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return content.toString();
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                // lay chuoi + xu ly
                final JSONArray jsonArray = new JSONArray(s);
                // lay tung item trong chuoi jsonArray + xu ly chuoi
                for(int i =0; i < jsonArray.length();i++) {

                    String Teninurl  = jsonArray.getString(i);
                    String[] TenCut = Teninurl.split(" ");
                    // xu ly chuoi

                    if(TenCut.length == 1){
                        Ten.add(TenCut[0]);
                    } else if (TenCut.length == 2 ){
                        Ten.add(TenCut[0]+"\n"+TenCut[1]);
                    } else if(TenCut.length == 3){
                        Ten.add(TenCut[0]+" "+TenCut[1]+"\n"+TenCut[2]);
                    } else if(TenCut.length == 4){
                        Ten.add(TenCut[0]+" "+TenCut[1]+"\n"+TenCut[2]+" "+TenCut[3]);
                    } else if(TenCut.length == 5){
                        Ten.add(TenCut[0]+" "+TenCut[1]+" "+TenCut[2]+"\n"+TenCut[3]+" "+TenCut[4]);
                    }  else if(TenCut.length == 6){
                        Ten.add(TenCut[0]+" "+TenCut[1]+" "+TenCut[2]+"\n"+TenCut[3]+" "+TenCut[4]+" "+TenCut[5]);
                    } else if(TenCut.length == 7){
                        Ten.add(TenCut[0]+" "+TenCut[1]+" "+TenCut[2]+"\n"+TenCut[3]+" "+TenCut[4]+" "+TenCut[5]+" "+TenCut[6]);
                    } else if(TenCut.length == 8){
                        Ten.add(TenCut[0]+" "+TenCut[1]+" "+TenCut[2] +" "+TenCut[3] +"\n"+TenCut[4]+" "+TenCut[5]+" "+TenCut[6]+" "+TenCut[7]);
                    }
                }
                // xu ly giua cac View de truyen chuoi vao View
                LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                // anh xa RecyclerView
                RecyclerView recyclerView = findViewById(R.id.recycleView);
                recyclerView.setLayoutManager(layoutManager);
                ViewAdapter adapter = new ViewAdapter(MainActivity.this, Ten);
                recyclerView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
