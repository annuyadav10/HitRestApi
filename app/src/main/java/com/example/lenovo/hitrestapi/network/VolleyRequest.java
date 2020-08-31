package com.example.lenovo.hitrestapi.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.lenovo.hitrestapi.application.AppController;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

public class VolleyRequest {
    public static VolleyRequest volleyRequest;
    Context context;

    public VolleyRequest(Context context) {
        this.context=context;
    }

    public static VolleyRequest getInstance(Context context) {
        if (volleyRequest == null)
            volleyRequest = new VolleyRequest(context);
        return volleyRequest;
    }

    public void requestWithJsonObject(final String url, final VolleyResponse vr){

        JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(Request.Method.GET, url,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.w("Response", url + " " + response);
                    vr.onResponse(response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Volley Error :", error.toString());
            }
        }) ;
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 1, 1f) {
        })

        ;
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

}
