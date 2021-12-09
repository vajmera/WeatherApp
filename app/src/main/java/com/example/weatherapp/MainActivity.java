package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button btn_city, btn_id, btn_city_name;
    ListView ls_view;

    WeatherData weatherData=new WeatherData(MainActivity.this); //We have to send the context to other page hence its paased as a constructor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextTextPersonName);
        btn_city = findViewById(R.id.btn_city);
        btn_id = findViewById(R.id.btn_get_weather_by_id);
        btn_city_name = findViewById(R.id.btn_get_weather_by_city_name);

        ls_view = findViewById(R.id.WeatherData);
        //Request: A request is like a query to the rest API, there exits a queue of requests which needs to be handled
        //in a sequential order.
        //We instantiate the request queue

        /*
            Use of singleton design pattern → We don’t want to create RequestQueue everytime we press the button,
            with only one queue, we can do the functionality, hence we use singleton design pattern.

         */
        //RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        btn_city.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            //below thing does callback
                                            weatherData.getCityId(editText.getText().toString(), new WeatherData.VolleyResponseListener() {
                                                @Override
                                                public void onError(String message) {
                                                    Toast.makeText(MainActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();

                                                }

                                                @Override
                                                public void onResponse(String cityId) {
                                                    Toast.makeText(MainActivity.this, "Returned Id of " + cityId, Toast.LENGTH_SHORT).show();

                                                }
                                            });
                                            //This didm't return anything. We use Callback to correct
                                            //Callback is a way to chedule a method call to occur when another method finishes its task.
                                            //Toast.makeText(MainActivity.this,"Returned Id of "+cityId, Toast.LENGTH_SHORT).show();
//                                            String url = "https://www.metaweather.com/api/location/search/?query=";
//
//                                            String ourQuery = editText.getText().toString();
//                                            String ByName = url + ourQuery;
//
//
//                                            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, ByName, null, new Response.Listener<JSONArray>() {
//                                                @Override
//
//                                                public void onResponse(JSONArray response) {
//                                                    String cityId = "";
//                                                    try {
//                                                        JSONObject cityInformtaion = response.getJSONObject(0);
//                                                        cityId = cityInformtaion.getString("woeid");
//                                                    } catch (JSONException e) {
//                                                        e.printStackTrace();
//                                                    }
//                                                    Toast.makeText(MainActivity.this, "Data is " + cityId, Toast.LENGTH_SHORT).show();
//
//                                                }
//
//                                            },
//                                                    new Response.ErrorListener() {
//                                                        @Override
//                                                        public void onErrorResponse(VolleyError error) {
//                                                            Toast.makeText(MainActivity.this, "Not able to get", Toast.LENGTH_SHORT).show();
//                                                        }
//                                                    }
//
//                                            );
//                                            // Add a request (in this example, called stringRequest) to your RequestQueue.
//                                            MySingleton.getInstance(MainActivity.this).addToRequestQueue(jsonArrayRequest);
                                        }
                                    }
        );
        // requestQueue.add(jsonArrayRequest);

        //THe site is returning for very api ,  a json array object we can get a string response as well.
//                StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Toast.makeText(MainActivity.this, "Response is " + response, Toast.LENGTH_SHORT).show();
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(MainActivity.this,"DIDNt worked", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                Toast.makeText(MainActivity.this,"Successfully clicked to get ID of city name", Toast.LENGTH_SHORT).show();
//
//                //Adding the request to request queue
//                requestQueue.add(stringRequest);
//            }
//        });

        //String curr_url = "https://www.metaweather.com/api/location/";
        //String temp = editText.getText().toString();

        //String send_url = curr_url + temp + "/";

        btn_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                weatherData.getCityForecastById(editText.getText().toString(), new WeatherData.ForeCastByIdResponse() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this,"ERROROO", Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onResponse(List<WeatherReportModel> weatherReportModels) {
                        //Toast.makeText(MainActivity.this,weatherReportModel.toString(), Toast.LENGTH_SHORT).show();

                        //We now create our list using layout adapter, using array adapter
                        ArrayAdapter arrayAdapter=new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,weatherReportModels);
                        ls_view.setAdapter(arrayAdapter);
                    }
                });

               // String ByName = "";
                btn_city_name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        weatherData.getCityForecastByName(editText.getText().toString(), new WeatherData.GetCityByNameCallback(){
                            @Override
                            public void onError(String message) {
                                Toast.makeText(MainActivity.this,"ERROROO", Toast.LENGTH_SHORT).show();


                            }

                            @Override
                            public void onResponse(List<WeatherReportModel> weatherReportModels) {
                                //Toast.makeText(MainActivity.this,weatherReportModel.toString(), Toast.LENGTH_SHORT).show();

                                //We now create our list using layout adapter, using array adapter
                                ArrayAdapter arrayAdapter=new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,weatherReportModels);
                                ls_view.setAdapter(arrayAdapter);
                            }
                        });
                    }
                });
            }

        });
    }}