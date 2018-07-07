package com.druk.eventhelper;


import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BackgroundTask {
    Context context;
    String user_id;
    String json_url = "http://uncroptv.000webhostapp.com/Read.php/?id=";
    static ArrayList<Event> arrayList = new ArrayList<>();

    public BackgroundTask(Context context, String user_id) {
        this.context = context;
        this.user_id = user_id;
    }

    public void getList(final arrayCallBack onCallBack) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, json_url+user_id, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int count = 0;
                while (count < response.length()) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(count);
                        Event event = new Event(jsonObject.getString("name"), jsonObject.getString("description"), jsonObject.getString("time"));
                        arrayList.add(event);
                        count++;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                onCallBack.onSuccess(arrayList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error..", Toast.LENGTH_LONG).show();
                error.printStackTrace();
                onCallBack.onFail(error.toString());

            }
        });
        MySingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);




    }

    public interface arrayCallBack {
        void onSuccess(ArrayList<Event> contacts);

        void onFail(String msg);
    }
}

