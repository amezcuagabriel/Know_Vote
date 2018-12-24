package com.example.gabe.politicianspulse;

import android.app.LauncherActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Representatives extends AppCompatActivity {

    private static final String url = "https://www.googleapis.com/civicinfo/v2/representatives?address=2613+irvington+ave+San+Bernardino+CA+92407&includeOffices=true&fields=normalizedInput%2Coffices(levels%2Cname%2Croles)%2Cofficials(address%2Cchannels%2Cemails%2Cname%2Cparty%2Cphones%2CphotoUrl)&key=AIzaSyB1B5mEKHK8PHDiNcGQ5ZU3fPIH9KWxAcQ";
    private TextView officeName;
    private LinearLayoutManager linearLayoutManager;
    private List<Reps> repList;
    private RecyclerView myrv;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_representatives);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        officeName = findViewById(R.id.office_name);         //Initialize TextViews


        myrv = findViewById(R.id.recycler_view);        //Initialize recycler view
        repList = new ArrayList<>();
        adapter = new RepRvAdapter(repList, getApplicationContext());

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        myrv.setHasFixedSize(true);
        myrv.setLayoutManager(linearLayoutManager);
        myrv.setAdapter(adapter);



        //runAnimation(myrv, 0);     //animation for recycler view
        getData();      //Fetch and parse json data
    }

    private void getData(){
        request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        Reps reps = new Reps();
                        reps.setOfficeName(jsonObject.getString("name"));
                        reps.setOfficialName(jsonObject.getString("name"));
                        reps.setParty(jsonObject.getString("party"));
                        reps.setPhotoUrl(jsonObject.getString("photoUrl"));
                        reps.setLine1(jsonObject.getString("line1"));
                        reps.setCity(jsonObject.getString("city"));
                        reps.setState(jsonObject.getString("state"));
                        reps.setZip(jsonObject.getString("zip"));
                        reps.setPhones(jsonObject.getString("phones"));
                        repList.add(reps);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setUpRv(repList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    private void setUpRv(List<Reps> repList) {
        RepRvAdapter adapter = new RepRvAdapter(repList, this);
        myrv.setAdapter(adapter);
    }

/**
    //fetch json data from Civic API
    private void getData(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    //first loop through offices array. Retrieve officeName value
                    JSONArray officesArray = jsonObject.getJSONArray("offices");        //One array for offices
                    JSONArray officialsArray = jsonObject.getJSONArray("officials");    //one array for officials
                    for (int i = 0; i < officesArray.length(); i++){
                        JSONObject jsonOffices = officesArray.getJSONObject(i);
                        JSONObject jsonOfficials = officialsArray.getJSONObject(i);
                       if(jsonOfficials.has("photoUrl")){
                            Reps reps1 = new Reps (jsonOffices.getString("name"),            //Current Bug: Not all image url are parsed and displayed in imageView
                                    jsonOfficials.getString("name"),
                                    jsonOfficials.getString("party"),
                                    jsonOfficials.getString("photoUrl"));
                            repList.add(reps1);
                        }else{
                           Reps reps2 = new Reps(jsonOffices.getString("name"),
                                   jsonOfficials.getString("name"),
                                   jsonOfficials.getString("party"));
                           repList.add(reps2);
                       }

                    adapter = new RepRvAdapter(repList, getApplicationContext());
                    myrv.setAdapter(adapter);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }**/
}
