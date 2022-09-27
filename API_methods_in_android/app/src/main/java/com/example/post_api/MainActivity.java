package com.example.post_api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    EditText edit_id, edit_firstName, edit_last_name, edit_mobileNumber, edit_emailId;
    Button button_get, button_post, button_put, button_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_id = findViewById(R.id.edit_id);
        edit_firstName = findViewById(R.id.edit_firstName);
        edit_last_name = findViewById(R.id.edit_lastName);
        edit_mobileNumber = findViewById(R.id.edit_mobileNumber);
        edit_emailId = findViewById(R.id.edit_emailId);
        button_get = findViewById(R.id.button_get);
        button_post = findViewById(R.id.button_post);
        button_put = findViewById(R.id.button_put);
        button_delete = findViewById(R.id.button_delete);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url_post = "http://192.168.6.229:4000/student";
        String url_put = "http://192.168.6.229:4000/student/100";
        String url_delete = "http://192.168.6.229:4000/student/100";

//        GET REQUEST
        button_get.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
            startActivity(intent);
        });

//        POST REQUEST
        button_post.setOnClickListener(view -> {

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("student_id", edit_id.getText().toString());
                jsonObject.put("first_name", edit_firstName.getText().toString());
                jsonObject.put("last_name", edit_last_name.getText().toString());
                jsonObject.put("mobile_number", edit_mobileNumber.getText().toString());
                jsonObject.put("email_id", edit_emailId.getText().toString());
            } catch (JSONException e) {
                Toast.makeText(this, "some error occur", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url_post, response -> Toast.makeText(MainActivity.this, "Successfully Submitted", Toast.LENGTH_SHORT).show(), error -> Toast.makeText(this, "some error occur", Toast.LENGTH_SHORT).show()) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() {
                    // Encoding
                    return jsonObject.toString().getBytes(StandardCharsets.UTF_8);
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            requestQueue.add(stringRequest);
        });

//        PUT REQUEST
        button_put.setOnClickListener(view -> {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("student_id", edit_id.getText().toString());
                jsonObject.put("first_name", edit_firstName.getText().toString());
                jsonObject.put("last_name", edit_last_name.getText().toString());
                jsonObject.put("mobile_number", edit_mobileNumber.getText().toString());
                jsonObject.put("email_id", edit_emailId.getText().toString());
            } catch (JSONException e) {
                Toast.makeText(this, "some error occur", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

            StringRequest stringRequest = new StringRequest(Request.Method.PUT, url_put, response -> Toast.makeText(MainActivity.this, "Successfully Updated", Toast.LENGTH_SHORT).show(), error -> Toast.makeText(this, "some error occur", Toast.LENGTH_SHORT).show()) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() {
                    // Encoding
                    return jsonObject.toString().getBytes(StandardCharsets.UTF_8);
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            requestQueue.add(stringRequest);
        });

//        DELETE REQUEST
        button_delete.setOnClickListener(view -> {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("student_id", edit_id.getText().toString());
                jsonObject.put("first_name", edit_firstName.getText().toString());
                jsonObject.put("last_name", edit_last_name.getText().toString());
                jsonObject.put("mobile_number", edit_mobileNumber.getText().toString());
                jsonObject.put("email_id", edit_emailId.getText().toString());
            } catch (JSONException e) {
                Toast.makeText(this, "some error occur", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

            StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url_delete, response -> Toast.makeText(MainActivity.this, "Successfully Deleted", Toast.LENGTH_SHORT).show(), error -> Toast.makeText(this, "some error occur", Toast.LENGTH_SHORT).show()) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() {
                    // Encoding
                    return jsonObject.toString().getBytes(StandardCharsets.UTF_8);
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            requestQueue.add(stringRequest);
        });


    }
}