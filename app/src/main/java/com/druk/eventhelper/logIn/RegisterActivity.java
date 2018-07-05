package com.druk.eventhelper.logIn;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.druk.eventhelper.R;


import org.json.JSONException;
import org.json.JSONObject;

import static com.druk.eventhelper.UtilClass.isEmpty;


public class RegisterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText et_name = findViewById(R.id.name);
        final EditText et_lastName = findViewById(R.id.lastname);
        final EditText et_email = findViewById(R.id.email);
        final EditText et_password = findViewById(R.id.password);
        final Button btn_register = findViewById(R.id.btn_register);


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEmpty(et_name)||isEmpty(et_lastName)||isEmpty(et_email)||isEmpty(et_password)) {
                    Toast.makeText(RegisterActivity.this, "Заполните все поля !", Toast.LENGTH_SHORT).show();
                    return;
                }
                final String name = et_name.getText().toString();
                final String lastName = et_lastName.getText().toString();
                final String email = et_email.getText().toString();
                final String password = et_password.getText().toString();



                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Register Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(name, lastName, password, email, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });
    }



}


