package com.druk.eventhelper;


import com.android.volley.Request;
import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
class RegisterRequest extends StringRequest {


    private static final String REGISTER_REQUEST_URL = "http://uncroptv.000webhostapp.com/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String name, String lastname, String password, String email, Response.Listener<String> listener) {
        super(Request.Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("lastname", lastname);
        params.put("password", password);
        params.put("email", email);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

