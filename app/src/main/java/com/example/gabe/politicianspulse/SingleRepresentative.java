package com.example.gabe.politicianspulse;

import android.app.ProgressDialog;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SingleRepresentative extends AppCompatActivity {
    private static final String url = "https://www.googleapis.com/civicinfo/v2/representatives?address=2613+irvington+ave+San+Bernardino+CA+92407&includeOffices=true&fields=normalizedInput%2Coffices(levels%2Cname%2Croles)%2Cofficials(address%2Cchannels%2Cemails%2Cname%2Cparty%2Cphones%2CphotoUrl)&key=AIzaSyB1B5mEKHK8PHDiNcGQ5ZU3fPIH9KWxAcQ";
    private List<Reps> repList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_representative);

        repList = new ArrayList<>();

        //receive data
        String name = getIntent().getExtras().getString("name");
        String party = getIntent().getExtras().getString("party");
        String office = getIntent().getExtras().getString("office");
        String rep = getIntent().getExtras().getString("rep");
        String line1 = getIntent().getExtras().getString("line1");
        String city = getIntent().getExtras().getString("city");
        String state = getIntent().getExtras().getString("state");
        String zip = getIntent().getExtras().getString("zip");
        String phones = getIntent().getExtras().getString("phones");

        //initialize views
        TextView name_tv = findViewById(R.id.name);
        TextView party_tv = findViewById(R.id.party);
        TextView office_tv = findViewById(R.id.office);
        ImageView rep_tv = findViewById(R.id.rep);
        TextView line1_tv = findViewById(R.id.line1);
        TextView city_tv = findViewById(R.id.city);
        TextView state_tv = findViewById(R.id.state);
        TextView zip_tv = findViewById(R.id.zip);
        TextView phones_tv = findViewById(R.id.phones);

        //set string values to each view
        name_tv.setText(name);
        party_tv.setText(party);
        office_tv.setText(office);
        line1_tv.setText(line1);
        city_tv.setText(city);
        state_tv.setText(state);
        zip_tv.setText(zip);
        phones_tv.setText(phones);
        //set image
        Picasso.get()
                .load(rep)
                .centerCrop()
                .transform(new CircleTransform(50, 0))
                .fit()
                .into(rep_tv);

    }

    /**
     *TEMPORARY: Find a better way to implement this in Representatives.java.
     * Using this method will create unecessary JSON requests, creating overhead,
     * but I mean, it works for now...
     **/

}
