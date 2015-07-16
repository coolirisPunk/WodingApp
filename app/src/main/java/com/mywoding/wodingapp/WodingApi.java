package com.mywoding.wodingapp;

import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.facebook.login.LoginResult;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by germanpunk on 12/04/15.
 */
public class WodingApi {
    public static final String API_URL_LOGIN_FB = "http://mywoding.com/rest-auth/facebook/";

    public WodingApi(){

    }

    public void Login(final String token){

        StringRequest myReq = new StringRequest(Request.Method.POST,
                API_URL_LOGIN_FB,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("access_token",token);
                return params;
            }
        };
        int MY_SOCKET_TIMEOUT_MS = 30000;//30 seconds - change to what you want
        myReq.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyVolleySingleton.getInstance().addToRequestQueue(myReq);
    }

    private Response.Listener<JSONObject> createMyReqSuccessListener() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.v("response", response.toString());
            }
        };
    }

    private Response.ErrorListener createMyReqErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("response", error.toString());
            }
        };
    }
}
