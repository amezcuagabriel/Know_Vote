package com.example.gabe.politicianspulse;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VoterInformation extends AppCompatActivity {
    private static final String url = "https://www.googleapis.com/civicinfo/v2/voterinfo?address=2613+Irvington+Ave+San+Bernardino+CA+92407&electionId=2000&officialOnly=false&returnAllAvailableData=false&fields=contests(referendumBallotResponses%2CreferendumBrief%2CreferendumConStatement%2CreferendumEffectOfAbstain%2CreferendumPassageThreshold%2CreferendumProStatement%2CreferendumSubtitle%2CreferendumText%2CreferendumTitle%2CreferendumUrl)&key=AIzaSyB1B5mEKHK8PHDiNcGQ5ZU3fPIH9KWxAcQ";
    private TextView refTitle, refSub;
    private LinearLayoutManager linearLayoutManager;
    private List<Refs> refList;
    private RecyclerView myrv;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voter_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        refTitle = findViewById(R.id.ref_title);         //Initialize TextViews
        refSub = findViewById(R.id.ref_sub);

        myrv = findViewById(R.id.ref_rv);        //Initialize recycler view
        refList = new ArrayList<>();
        adapter = new RefRvAdapter(getApplicationContext(), refList);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        myrv.setHasFixedSize(true);
        myrv.setLayoutManager(linearLayoutManager);
        myrv.setAdapter(adapter);

        getData();
    }
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
                    JSONArray array = jsonObject.getJSONArray("contests");
                    for(int i = 0; i < array.length(); i++){
                        JSONObject jo = array.getJSONObject(i);
                        Refs refs = new Refs(jo.getString("referendumTitle"),
                                jo.getString("referendumSubtitle"));
                        refList.add(refs);
                    }
                    adapter = new RefRvAdapter(refList, getApplicationContext());
                    myrv.setAdapter(adapter);
                } catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
