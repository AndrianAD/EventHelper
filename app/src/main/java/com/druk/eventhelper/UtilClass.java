package com.druk.eventhelper;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.druk.eventhelper.data.Event;

import java.util.ArrayList;

public class UtilClass {
    public static boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    public static void makeToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }


    public static void deleteEvent(final Context context, final String name) {
        final String URL_DELETE = "http://uncroptv.000webhostapp.com/Delete_Event.php/?name=" + name;
        StringRequest ifDeleteRequest = new StringRequest(Request.Method.POST, URL_DELETE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                makeToast(context, "Erore delete");
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(ifDeleteRequest);

    }


    public static void read(final Context context, String user_id, final RecyclerView recyclerView) {
        BackgroundTask backgroundTask = new BackgroundTask(context, user_id);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        backgroundTask.getList(new BackgroundTask.arrayCallBack() {
            @Override
            public void onSuccess(ArrayList<Event> events) {
                RecyclerView.Adapter adapter = new RecyclerAdapter(events);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(layoutManager);
            }

            @Override
            public void onFail(String msg) {

            }
        });

    }
}
