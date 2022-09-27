package com.example.post_api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String url_get = "http://192.168.6.229:4000/";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        listView = findViewById(R.id.listView);
        ArrayList<String> student_data = new ArrayList<>();

//        Get Request
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url_get, null, response -> {
            try {
                for(int i=0; i < response.length(); i++){

                    JSONObject jsonObject = response.getJSONObject(i);
                    String student_id = jsonObject.getString("student_id");
                    String first_name = jsonObject.getString("first_name");
                    String last_name = jsonObject.getString("last_name");
                    String mobile_number = jsonObject.getString("mobile_number");
                    String email_id = jsonObject.getString("email_id");

                    student_data.add("Student id : " + student_id + "\n" +
                            "Name : " + first_name + " " + last_name + "\n" +
                            "mob. : " + mobile_number + "\n" +
                            "e-mail : " + email_id);

                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, student_data);
                    listView.setAdapter(arrayAdapter);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> Log.d("myapp", "something went wrong "));

        requestQueue.add(jsonArrayRequest);
    }
}