package com.druk.eventhelper;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static com.druk.eventhelper.UtilClass.isEmpty;

public class UserAreaActivity extends Activity {
    final String SERVER_ULR = "http://uncroptv.000webhostapp.com/Create_Event.php";
    int user_id;
    TextView tvWelcomeMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);
        Button btn_newEvent = findViewById(R.id.new_event);
        Button btn_displayList = findViewById(R.id.display_list);


        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String lastname = intent.getStringExtra("lastname");
        user_id = Integer.parseInt(intent.getStringExtra("id"));


        btn_newEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                save_form();

            }
        });

        tvWelcomeMsg = (TextView) findViewById(R.id.tvWelcomeMsg);
        String message = name + "  " + lastname + " welcome to your user area";
        tvWelcomeMsg.setText(message + getCurrentTime() + "Это ваш ID номер:" + user_id);

    }

    public String getCurrentTime() {
        return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
    }


    private void save_form() {
        final Dialog dialog = new Dialog(UserAreaActivity.this);
        dialog.setContentView(R.layout.save_form);
        dialog.setTitle("Введите название:");
        dialog.show();
        final Button buttonOK = (Button) dialog.findViewById(R.id.save_form_bt_OK);
        final EditText name = (EditText) dialog.findViewById(R.id.save_form_et_name);
        final EditText description = (EditText) dialog.findViewById(R.id.save_form_et_description);
        name.post(new Runnable() {
            @Override
            public void run() {
                InputMethodManager inputMethodManager =
                        (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.toggleSoftInputFromWindow(
                        name.getApplicationWindowToken(), InputMethodManager.SHOW_IMPLICIT, 0);
                name.requestFocus();
            }
        });
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty(name) || isEmpty(description)) {
                    Toast.makeText(UserAreaActivity.this, "Заполните все поля !", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringRequest stringRequest = new StringRequest(Request.Method.POST, SERVER_ULR, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        dialog.dismiss();
                        tvWelcomeMsg.setText(response);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        String to_name = name.getText().toString();
                        String to_description = description.getText().toString();
                        String time = getCurrentTime();

                        params.put("name", to_name);
                        params.put("description", to_description);
                        params.put("time", time);
                        params.put("id", user_id + "");
                        return params;
                    }
                };
                MySingleton.getInstance(UserAreaActivity.this).addToRequestQueue(stringRequest);
            }
        });


    }


    public void displayList(View view) {
        Intent intent=new Intent(UserAreaActivity.this,DisplayList.class);
        intent.putExtra("id", user_id+"");
        startActivity(intent);
    }
}
