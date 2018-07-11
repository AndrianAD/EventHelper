package com.druk.eventhelper.logIn;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.druk.eventhelper.R;
import com.druk.eventhelper.UserAreaActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    RequestQueue queue;
    static SharedPreferences sharedPreferences;
    final String SHARED_EMAIL = "SHARED_EMAIL";
    final String SHARED_PASSWORD = "SHARED_PASSWORD";
    EditText etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        final TextView tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);
        final Button bLogin = (Button) findViewById(R.id.bSignIn);
        sharedPreferences = getPreferences(MODE_PRIVATE);
        if (sharedPreferences.contains(SHARED_EMAIL)) {
            loadPreferances();
            loginCheck();
        }






        tvRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });


        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginCheck();
            }
        });
    }


    private void loginCheck() {
        final String email = etEmail.getText().toString();
        final String password = etPassword.getText().toString();
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success) {
                        String name = jsonResponse.getString("name");
                        String lastname = jsonResponse.getString("lastname");
                        String id = jsonResponse.getString("id");

                        Intent intent = new Intent(LoginActivity.this, UserAreaActivity.class);
                        intent.putExtra("name", name);
                        intent.putExtra("lastname", lastname);
                        intent.putExtra("id", id);
                        queue.stop();
                        savePreferances();
                        startActivity(intent);

                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setMessage("Login Failed")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        LoginRequest loginRequest = new LoginRequest(email, password, responseListener);
        queue = Volley.newRequestQueue(LoginActivity.this);
        queue.add(loginRequest);
    }


    public void savePreferances() {
        final String email = etEmail.getText().toString();
        final String password = etPassword.getText().toString();
        sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SHARED_EMAIL, email);
        editor.putString(SHARED_PASSWORD, password);
        editor.commit();
    }


    public void loadPreferances() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        String email = sharedPreferences.getString(SHARED_EMAIL, "");
        String password = sharedPreferences.getString(SHARED_PASSWORD, "");
        etEmail.setText(email);
        etPassword.setText(password);
    }

    public void clearPreferances() {
        sharedPreferences.edit().remove(SHARED_EMAIL).remove(SHARED_PASSWORD).commit();
        etPassword.setText("");
        etEmail.setText("");
    }
}