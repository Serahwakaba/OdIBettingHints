package com.example.odibettinghints.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.odibettinghints.R;
import com.example.odibettinghints.adapters.RecyclerViewAdapter;
import com.example.odibettinghints.model.Anime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String JSON_URL = "https://www.suretips.co.ke/bettips_api//tips/all/v2" ;
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<Anime> lstAnime ;
    private RecyclerView recyclerView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstAnime = new ArrayList<>() ;
        recyclerView = findViewById(R.id.recyclerview_id) ;
        jsonrequest();
        
    }

    private void jsonrequest() {

        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null ;
                for(int i = 0 ; i < response.length(); i++ ){

                    try {
                        jsonObject = response.getJSONObject(i) ;
                        Anime anime = new Anime() ;
                        anime.setMatch_start_time(jsonObject.getInt("match_start_time"));
                        anime.setHome_team(jsonObject.getString("home_team"));
                        anime.setAway_team(jsonObject.getString("away_team"));
                        anime.setCompetition_cluster(jsonObject.getString("competition_cluster"));
                        anime.setHome_strength(jsonObject.getInt("home_strength"));
                        anime.setAway_strength(jsonObject.getInt("away_strength"));
                        anime.setPredictions(jsonObject.getInt("prediction"));
                        anime.setOdds(jsonObject.getInt("odds"));
                        lstAnime.add(anime);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                setuprecyclerview(lstAnime) ;


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue = Volley.newRequestQueue(MainActivity.this) ;
        requestQueue.add(request) ;

    }

    private void setuprecyclerview(List<Anime> lstAnime) {


        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter( this, lstAnime) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(myAdapter);
    }
}