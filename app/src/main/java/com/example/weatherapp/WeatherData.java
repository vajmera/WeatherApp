package com.example.weatherapp;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherData {
    Context context;
    String cityId;

    public static final String QUERY_FOR_CITY_WEATHER_BY_ID = "https://www.metaweather.com/api/location/";
    public WeatherData(Context context) {
        this.context=context;
    }

    //We put VOlley interface to introudce callbacks
    public interface VolleyResponseListener{
        void onError(String message);
        void onResponse(String cityId);


    }

    public void getCityId(String cityName,VolleyResponseListener volleyResponseListener)
    {


        String url = "https://www.metaweather.com/api/location/search/?query=";


        String ourQuery =cityName;
        String ByName = url + ourQuery;


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, ByName, null, new Response.Listener<JSONArray>() {
            @Override

            public void onResponse(JSONArray response) {
                 cityId = "";
                try {
                    JSONObject cityInformtaion = response.getJSONObject(0);
                    cityId = cityInformtaion.getString("woeid");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //THis is our API call it will work
                //Toast.makeText(context, "Data is " + cityId, Toast.LENGTH_SHORT).show();
                volleyResponseListener.onResponse(cityId);
            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                     //   Toast.makeText(context, "Not able to get", Toast.LENGTH_SHORT).show();
                        volleyResponseListener.onError("Something WRONG");
                    }
                }

        );
        // Add a request (in this example, called stringRequest) to your RequestQueue.
        MySingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);

        //return cityId;
    }

//------------------------------------------------------------------------------------------------------
    public interface ForeCastByIdResponse{
        void onError(String message);
        void onResponse(WeatherReportModel weatherReportModel);
    }
    public void  getCityForecastById (String cityId,ForeCastByIdResponse foreCastByIdResponse){
        List<WeatherReportModel> report=new ArrayList<>(); // it gives a jsonObject

        //First object is jsonObject and then we have jsonArray
        String url=QUERY_FOR_CITY_WEATHER_BY_ID+cityId;
        //get json object
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

               // Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                try {
                    WeatherReportModel first_day=new WeatherReportModel();
                    JSONArray consolodated_weather_list= response.getJSONArray("consolidated_weather");
                    JSONObject first_data_from_api= (JSONObject) consolodated_weather_list.get(0); //get 0th element from that list as it contains 6 items of jsonobject type

//                    private int id;
//                    private String weather_state_name;
//                    private String weather_state_abbr;
//                    private String wind_direction_compass;
//                    private String created;
//                    private String applicable_data;
//                    private float min_temp;
//                    private float max_temp;
//                    private float the_temp;
//                    private float wind_speed;
//                    private float wind_direction;
//                    private int air_pressure;
//                    private int humidity;
//                    private float visibility;
//                    private int predictability;
                    first_day.setId( first_data_from_api.getInt("id"));
                    first_day.setWeather_state_name(first_data_from_api.getString("weather_state_name"));
                    first_day.setWeather_state_abbr(first_data_from_api.getString("weather_state_abbr"));
                    first_day.setWind_direction_compass(first_data_from_api.getString("wind_direction_compass"));

                    first_day.setCreated(first_data_from_api.getString("created"));
                    first_day.setApplicable_data(first_data_from_api.getString("applicable_date"));
                    first_day.setMin_temp(first_data_from_api.getLong("min_temp"));
                    first_day.setMax_temp(first_data_from_api.getLong("max_temp"));
                    first_day.setThe_temp(first_data_from_api.getLong("the_temp"));

                    first_day.setWind_speed(first_data_from_api.getLong("wind_speed"));
                    first_day.setWind_direction(first_data_from_api.getLong("wind_direction"));

                    first_day.setAir_pressure(first_data_from_api.getInt("air_pressure"));
                    first_day.setHumidity(first_data_from_api.getInt("humidity"));
                    first_day.setVisibility(first_data_from_api.getLong("visibility"));

                    first_day.setPredictability(first_data_from_api.getInt("predictability"));

                    foreCastByIdResponse.onResponse(first_day);
                    //we want to return this first_day object hence we again use callbacks
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        // Add a request (in this example, called stringRequest) to your RequestQueue.
        MySingleton.getInstance(context).addToRequestQueue(request);

                //and we want "consolidated_weather" which is label of that jsonArray

                //we get each item in array and assign it to a new WeatherReportModel object


    }



}
