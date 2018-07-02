package com.druk.eventhelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
public class UserAreaActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user_area);

    Intent intent = getIntent();
    String name = intent.getStringExtra("name");
    String lastname = intent.getStringExtra("lastname");

    TextView tvWelcomeMsg = (TextView) findViewById(R.id.tvWelcomeMsg);
    String message = name +  lastname+ " welcome to your user area";
        tvWelcomeMsg.setText(message);

}

}
