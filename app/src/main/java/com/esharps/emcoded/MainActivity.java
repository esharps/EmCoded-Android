package com.esharps.emcoded;

import java.io.*;
import java.util.*;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.*;
import com.android.volley.RequestQueue;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.esharps.emcoded.controller.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private EditText codeEditTxt;
    private Button submitCode;
    private TextView codeEditorView;

    RequestQueue queue;
    String replitUrl = "https://eval-backend.emmy-sharp.repl.co/exec";
    String command = "x = 'Hello World!'\nprint(x)";
    String language = "python";
    JSONObject tempResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






        queue = Volley.newRequestQueue(this);

        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("language", language.toString());
            jsonParams.put("command", command.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT,
                replitUrl, jsonParams, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                tempResponse = response;
                //Log.d("Replit Response", tempResponse.toString());
                try {
                    Log.d("Replit Response", tempResponse.toString());
                } catch (Exception e) {
                    Log.d("API callback error", e.getMessage());
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error response", error.toString());
                    }
                }
        );

        queue.add(jsonObjectRequest);


    } // end of onCreate()

    public void onClick(View view) {

    }

}

