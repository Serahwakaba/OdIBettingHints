package com.example.odibettinghints.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
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

//    private final String JSON_URL = "https://www.suretips.co.ke/bettips_api//tips/all/v2" ;
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<Anime> lstAnime ;
    private RecyclerView recyclerView ;
    private DialogFragment progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressDialog dialog = new ProgressDialog(this);//initialization;        progressDialog.setContentView(R.layout.progress_layout);


        lstAnime = new ArrayList<>() ;
        recyclerView = findViewById(R.id.recyclerview_id) ;
        jsonrequest();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("app_package","com.sportytips");
        jsonObject.put("gamedate","2020-05-06");
        postLogin(jsonObject);

    }

    private void jsonrequest() {
    }

    private void postLogin(JSONObject payload){
        progressDialog.show();
        String url = "https://www.suretips.co.ke/bettips_api//tips/all/v2";
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,"https://www.suretips.co.ke/bettips_api//tips/all/v2", payload,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        Log.d("Tips",response.toString());
                        try {

                            //put your code here
                            Anime anime = new Anime() ;
                            anime.setMatch_start_time(payload.getInt("match_start_time"));
                            anime.setHome_team(payload.getString("home_team"));
                            anime.setAway_team(payload.getString("away_team"));
                            anime.setCompetition_cluster(payload.getString("competition_cluster"));
                            anime.setHome_strength(payload.getInt("home_strength"));
                            anime.setAway_strength(payload.getInt("away_strength"));
                            anime.setPredictions(payload.getInt("prediction"));
                            anime.setOdds(payload.getInt("odds"));
                            lstAnime.add(anime);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    else{
                        lblMessageBox.setVisibility(View.VISIBLE);
                        lblMessageBox.setText(message);
                        //Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();


//                } catch (JSONException  e) {
//            e.printStackTrace();
//        }

                @Override
                public void onErrorResponse(VolleyError volleyError)
        {
                    progressDialog.dismiss();
                    NetworkResponse response = volleyError.networkResponse;

                    if (response != null && response.data != null) {
                        String json = new String(response.data);
                        Log.d("Error", json);

                        try {
                            JSONObject obj = new JSONObject(json);
                            json = obj.getString("statusDescription");
                            if (!obj.isNull("data")) {
                                JSONObject data = obj.getJSONObject("data");
                                lblMessageBox.setVisibility(View.VISIBLE);
                                lblMessageBox.setText(data.getString("message"));

                                // Toast.makeText(getContext(),data.getString("message"),Toast.LENGTH_SHORT).show();
                            } else {
                                lblMessageBox.setText(json);
                                lblMessageBox.setVisibility(View.VISIBLE);
                                Toast.makeText(recyclerView.getContext(), json, Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            lblMessageBox.setVisibility(View.VISIBLE);
                            lblMessageBox.setText(recyclerView.getContext().getResources().getString(R.string.serverError));

                            //Toast.makeText(getContext(),getContext().getResources().getString(R.string.serverError),Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    } else {
                        lblMessageBox.setVisibility(View.VISIBLE);
                        lblMessageBox.setText(recyclerView.getContext().getResources().getString(R.string.serverError));
                    }

//                        RetryPolicy policy = new DefaultRetryPolicy(Config.SOCKET_TIMEOUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//                        jsonObjReq.setRetryPolicy(policy);
//                        // Adding request to request queue
//                    RequestSingletone.getInstance(recyclerView.getContext()).addToRequestQueue(jsonObjReq, "SET PIN REQUEST");

                }
}
//    private void jsonrequest() {
//
//        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//
//                JSONObject jsonObject = new JSONObject();
//                try {
//                    jsonObject.put("app_package","com.sportytips");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    jsonObject.put("gamedate","2020-05-06");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                postLogin(jsonObject);
//                for(int i = 0 ; i < response.length(); i++ ){
//
//                    try {
//                        jsonObject = response.getJSONObject(i) ;
//                        Anime anime = new Anime() ;
//                        anime.setMatch_start_time(jsonObject.getInt("match_start_time"));
//                        anime.setHome_team(jsonObject.getString("home_team"));
//                        anime.setAway_team(jsonObject.getString("away_team"));
//                        anime.setCompetition_cluster(jsonObject.getString("competition_cluster"));
//                        anime.setHome_strength(jsonObject.getInt("home_strength"));
//                        anime.setAway_strength(jsonObject.getInt("away_strength"));
//                        anime.setPredictions(jsonObject.getInt("prediction"));
//                        anime.setOdds(jsonObject.getInt("odds"));
//                        lstAnime.add(anime);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//
//                setuprecyclerview(lstAnime) ;
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//
//
//        requestQueue = Volley.newRequestQueue(MainActivity.this) ;
//        requestQueue.add(request) ;
//
//    }
//
//    private void postLogin(JSONObject jsonObject) {
//    }
//
//    private void setuprecyclerview(List<Anime> lstAnime) {
//
//        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter( this, lstAnime) ;
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        recyclerView.setAdapter(myAdapter);
//    }
//}
//
//// time.setText(jsonObject.getInt("time"));
////         tips.setText(jsonObject.getInt("prediction"));
////         odds.setText(jsonObject.getInt("odds"));
////         matchOne.setText(jsonObject.getString("home_team"));
//         matchTwo.setText(jsonObject.getString("away_team"));