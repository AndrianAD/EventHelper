package com.druk.eventhelper;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.druk.eventhelper.logIn.RegisterActivity;

import java.util.HashMap;
import java.util.Map;

public class UtilClass {
    public static boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }
    public static void makeToast(Context context,String text){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }


public static void deleteEvent(final Context context, final String name){
        final String URL_DELETE="http://uncroptv.000webhostapp.com/Delete_Event.php/?name="+name;
    StringRequest ifDeleteRequest=new StringRequest(Request.Method.POST, URL_DELETE, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            makeToast(context,response);
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            makeToast(context,"Erore delete");
        }
    }) ;

    MySingleton.getInstance(context).addToRequestQueue(ifDeleteRequest);
}
}
