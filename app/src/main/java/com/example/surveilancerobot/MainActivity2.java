package com.example.surveilancerobot;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {
    String data = "";
    String url = "";
    String camUrl = "";
    String log1 = "";
    String la1 = "";
    String log2 = "";
    String la2 = "";
    String log3 = "";
    String la3 = "";
    String log4 = "";
    String la4 = "";

    String NO = "";
    String lat = "";
    String lon = "";

    private TextView satt;
    private TextView lonn;
    private TextView latt;

    private long pressedTime;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        SharedPreferences sp = getSharedPreferences("ip", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();

        final ImageButton down = findViewById(R.id.imageButton);
        final ImageButton up = findViewById(R.id.imageButton2);
        final ImageButton left = findViewById(R.id.imageButton3);
        final ImageButton right = findViewById(R.id.imageButton4);
        final Button delivery = findViewById(R.id.button4);
        final Button ip = findViewById(R.id.button3);
        final EditText IP = findViewById(R.id.editTextText);
        final EditText camURL = findViewById(R.id.editTextText6);
        final EditText lon1 = findViewById(R.id.editTextText2);
        final EditText lat1 = findViewById(R.id.editTextText3);
        final EditText lon2 = findViewById(R.id.editTextText4);
        final EditText lat2 = findViewById(R.id.editTextText5);
//        final EditText lon3 = findViewById(R.id.editTextText6);
//        final EditText lat3 = findViewById(R.id.editTextText7);
//        final EditText lon4 = findViewById(R.id.editTextText8);
//        final EditText lat4 = findViewById(R.id.editTextText9);
        final Button GPS = findViewById(R.id.button);
        final Button m1 = findViewById(R.id.button2);
        final Button m2 = findViewById(R.id.button5);
        final WebView cameraFeed = findViewById(R.id.webView);
//        final Button m3 = findViewById(R.id.button6);
//        final Button m4 = findViewById(R.id.button7);
        satt = findViewById(R.id.textView9);
        latt = findViewById(R.id.textView10);
        lonn = findViewById(R.id.textView11);



        IP.setText(sp.getString("ip", ""));
        camURL.setText(sp.getString("camip", ""));
        url = sp.getString("ip", "");
        camUrl = sp.getString("camip", "");

//        camUrl = getIntent().getStringExtra("url");
        // Load an initial URL
        cameraFeed.loadUrl("http://" + camUrl);

        // Set up WebViewClient to open links within the WebView
        cameraFeed.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        up.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == motionEvent.ACTION_UP) {
                    postDataUsingVolley("0");                    }

                if (motionEvent.getAction() == motionEvent.ACTION_DOWN) {

                    postDataUsingVolley("1");
                }
                return false;
            }
        });

        right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == motionEvent.ACTION_UP) {
                    postDataUsingVolley("0");                    }

                if (motionEvent.getAction() == motionEvent.ACTION_DOWN) {

                    postDataUsingVolley("2");
                }
                return false;
            }
        });

        down.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == motionEvent.ACTION_UP) {
                    postDataUsingVolley("0");                    }

                if (motionEvent.getAction() == motionEvent.ACTION_DOWN) {

                    postDataUsingVolley("3");
                }
                return false;
            }
        });

        left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == motionEvent.ACTION_UP) {
                    postDataUsingVolley("0");                    }

                if (motionEvent.getAction() == motionEvent.ACTION_DOWN) {

                    postDataUsingVolley("4");
                }
                return false;
            }
        });

//        delivery.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String cmdText = null;
//                String btnState = delivery.getText().toString().toLowerCase();
//                switch (btnState) {
//                    case "go for delivery":
//                        delivery.setText("Stop Delivery");
//                        log1 = lon1.getText().toString();
//                        la1 = lat1.getText().toString();
//                        log2 = lon2.getText().toString();
//                        la2 = lat2.getText().toString();
//                        log3 = lon3.getText().toString();
//                        la3 = lat3.getText().toString();
//                        log4 = lon4.getText().toString();
//                        la4 = lat4.getText().toString();
//
//                        String value = ("{\"signal\"" + ":" + "\"A\"" + "}");
//
////                        String value = ("{\"signal\"" + ":" + "\"A\" "+",\"lon1\"" + ":" + "\"" + log1 + "\"" + ",\"lat1\"" + ":" + "\"" + la1 +
////                                        "\"lon2\"" + ":" + "\"" + log2+ "\"" + ",\"lat2\"" + ":" + "\"" + la2 +
////                                        "\"lon3\"" + ":" + "\"" + log3 + "\"" + ",\"lat3\"" + ":" + "\"" + la3 +
////                                         "\"lon4\"" + ":" + "\"" + log4 + "\"" + ",\"lat4\"" + ":" + "\"" + la4 +"}");
//                        postDataUsingVolley(value);
//                        break;
//                    case "stop delivery":
//                        delivery.setText("Go For Delivery");
//                        postDataUsingVolley("5");
//                        break;
//                }
//            }
//        });

        delivery.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == motionEvent.ACTION_UP) {
                    String value = ("{\"signal\"" + ":" + "\"Y\"" + "}");
                    postDataUsingVolley(value);
                }

                if (motionEvent.getAction() == motionEvent.ACTION_DOWN) {

                    String btnState = delivery.getText().toString().toLowerCase();
                    switch (btnState) {
                        case "go for delivery":
                            delivery.setText("Stop Delivery");
                            log1 = lon1.getText().toString();
                            la1 = lat1.getText().toString();
                            log2 = lon2.getText().toString();
                            la2 = lat2.getText().toString();
//                            log3 = lon3.getText().toString();
//                            la3 = lat3.getText().toString();
//                            log4 = lon4.getText().toString();
//                            la4 = lat4.getText().toString();

                            String value = ("{\"signal\"" + ":" + "\"A\"" + "}");

//                        String value = ("{\"signal\"" + ":" + "\"A\" "+",\"lon1\"" + ":" + "\"" + log1 + "\"" + ",\"lat1\"" + ":" + "\"" + la1 +
//                                        "\"lon2\"" + ":" + "\"" + log2+ "\"" + ",\"lat2\"" + ":" + "\"" + la2 +
//                                        "\"lon3\"" + ":" + "\"" + log3 + "\"" + ",\"lat3\"" + ":" + "\"" + la3 +
//                                         "\"lon4\"" + ":" + "\"" + log4 + "\"" + ",\"lat4\"" + ":" + "\"" + la4 +"}");
                            postDataUsingVolley(value);
                            break;
                        case "stop delivery":
                            delivery.setText("Go For Delivery");
                            postDataUsingVolley("5");
                            break;
                    }
                }
                return false;
            }
        });

        ip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.putString("ip", IP.getText().toString());
                edit.putString("camip", camURL.getText().toString());
                edit.apply();
                Toast.makeText(MainActivity2.this, "IP successfully saved", Toast.LENGTH_SHORT).show();
            }

        });

//        GPS.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                postDataUsingVolley("B");
//            }
//
//        });

        GPS.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == motionEvent.ACTION_UP) {
                    String value = ("{\"signal\"" + ":" + "\"D\"" + "}");
                    postDataUsingVolley(value);
                }

                if (motionEvent.getAction() == motionEvent.ACTION_DOWN) {

                    String value = ("{\"signal\"" + ":" + "\"B\"" + "}");
                    postDataUsingVolley(value);
                }
                return false;
            }
        });

        m1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == motionEvent.ACTION_UP) {
                    String value = ("{\"signal\"" + ":" + "\"F\"" + "}");
                    postDataUsingVolley(value);
                }

                if (motionEvent.getAction() == motionEvent.ACTION_DOWN) {
                    lon1.setText(lon);
                    lat1.setText(lat);
                    String value = ("{\"signal\"" + ":" + "\"E\"" + "}");
                    postDataUsingVolley(value);
                }
                return false;
            }
        });

        m2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == motionEvent.ACTION_UP) {
                    String value = ("{\"signal\"" + ":" + "\"F\"" + "}");
                    postDataUsingVolley(value);
                }

                if (motionEvent.getAction() == motionEvent.ACTION_DOWN) {
                    lon2.setText(lon);
                    lat2.setText(lat);
                    String value = ("{\"signal\"" + ":" + "\"G\"" + "}");
                    postDataUsingVolley(value);
                }
                return false;
            }
        });

//        m3.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//
//                if (motionEvent.getAction() == motionEvent.ACTION_UP) {
//                    String value = ("{\"signal\"" + ":" + "\"F\"" + "}");
//                    postDataUsingVolley(value);
//                }
//
//                if (motionEvent.getAction() == motionEvent.ACTION_DOWN) {
////                    lon3.setText(lon);
////                    lat3.setText(lat);
//                    String value = ("{\"signal\"" + ":" + "\"I\"" + "}");
//                    postDataUsingVolley(value);
//                }
//                return false;
//            }
//        });

//        m4.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//
//                if (motionEvent.getAction() == motionEvent.ACTION_UP) {
//                    String value = ("{\"signal\"" + ":" + "\"F\"" + "}");
//                    postDataUsingVolley(value);
//                }
//
//                if (motionEvent.getAction() == motionEvent.ACTION_DOWN) {
//                    lon4.setText(lon);
//                    lat4.setText(lat);
//                    String value = ("{\"signal\"" + ":" + "\"K\"" + "}");
//                    postDataUsingVolley(value);
//                }
//                return false;
//            }
//        });

//        m1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                lon1.setText(lon);
//                lat1.setText(lat);
//            }
//
//        });
//
//        m2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                lon2.setText(lon);
//                lat2.setText(lat);
//            }
//
//        });
//
//        m3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                lon3.setText(lon);
//                lat3.setText(lat);
//            }
//
//        });
//
//        m4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                lon4.setText(lon);
//                lat4.setText(lat);
//            }
//
//        });

    }

    private void postDataUsingVolley(String name) {

//        String url = "http://192.168.4.1/post";

        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(MainActivity2.this);
        String URL = ("http://" + url);

        StringRequest request = new StringRequest(Request.Method.POST, URL, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                data = response;
                try {
                    // get JSONObject from JSON file
                    JSONObject obj = new JSONObject(data);
                    // fetch JSONObject named employee
//                    JSONObject employee = obj.getJSONObject("time");
                    // get employee name and salary

                    NO = obj.getString("no");
                    lat = obj.getString("lat");
                    lon = obj.getString("lon");


                    satt.setText(NO);
                    lonn.setText(lon);
                    latt.setText(lat);


//                    salary = employee.getString("salary");
//                    // set employee name and salary in TextView's
//                    employeeName.setText("Name: "+name);
//                    employeeSalary.setText("Salary: "+salary);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(MainActivity2.this, data, Toast.LENGTH_SHORT).show();
//                int dis = Integer.parseInt(data);
//                if (dis < 50) {
////                   Toast.makeText(MainActivity2.this, "Obstacle observed at " + dis + " centimeter", Toast.LENGTH_SHORT).show();
//                    T.speak("Obstacle observed at " + dis + " centimeter", TextToSpeech.QUEUE_FLUSH, null);
//                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override

            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(MainActivity2.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // below line we are creating a map for
                // storing our values in key and value pair.
                Map<String, String> params = new HashMap<String, String>();

                // on below line we are passing our key
                // and value pair to our parameters.

                params.put("data", name);


                return params;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);
    }





    @Override
    public void onBackPressed() {

        super.onBackPressed();
        if (pressedTime + 2000 > System.currentTimeMillis()) {

            Intent i = new Intent(MainActivity2.this, MainActivity2.class);
            startActivity(i);
            finish();

        } else {
            Toast.makeText(getBaseContext(), "Press back again to logout", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }
}