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
        String byName = url + ourQuery;


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, byName, null, new Response.Listener<JSONArray>() {
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
        void onResponse(List<WeatherReportModel> weatherReportModels);
    }
    public void  getCityForecastById (String cityId,ForeCastByIdResponse foreCastByIdResponse){
        List<WeatherReportModel> weatherReportModels=new ArrayList<>(); // it gives a jsonObject

        //First object is jsonObject and then we have jsonArray
        String url=QUERY_FOR_CITY_WEATHER_BY_ID+cityId;
        //get json object
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

               // Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                try {

                    JSONArray consolodated_weather_list= response.getJSONArray("consolidated_weather");

                    for(int i=0;i<consolodated_weather_list.length();i++) {

                        WeatherReportModel one_day_weather=new WeatherReportModel();
                        JSONObject first_data_from_api = (JSONObject) consolodated_weather_list.get(i); //get 0th element from that list as it contains 6 items of jsonobject type

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
                        one_day_weather.setId(first_data_from_api.getInt("id"));
                        one_day_weather.setWeather_state_name(first_data_from_api.getString("weather_state_name"));
                        one_day_weather.setWeather_state_abbr(first_data_from_api.getString("weather_state_abbr"));
                        one_day_weather.setWind_direction_compass(first_data_from_api.getString("wind_direction_compass"));

                        one_day_weather.setCreated(first_data_from_api.getString("created"));
                        one_day_weather.setApplicable_data(first_data_from_api.getString("applicable_date"));
                        one_day_weather.setMin_temp(first_data_from_api.getLong("min_temp"));
                        one_day_weather.setMax_temp(first_data_from_api.getLong("max_temp"));
                        one_day_weather.setThe_temp(first_data_from_api.getLong("the_temp"));

                        one_day_weather.setWind_speed(first_data_from_api.getLong("wind_speed"));
                        one_day_weather.setWind_direction(first_data_from_api.getLong("wind_direction"));

                        one_day_weather.setAir_pressure(first_data_from_api.getInt("air_pressure"));
                        one_day_weather.setHumidity(first_data_from_api.getInt("humidity"));
                        one_day_weather.setVisibility(first_data_from_api.getLong("visibility"));

                        one_day_weather.setPredictability(first_data_from_api.getInt("predictability"));

                        weatherReportModels.add(one_day_weather);
                        foreCastByIdResponse.onResponse(weatherReportModels);
                    }
                    //we want to return this one_day_weather object hence we again use callbacks
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

    public interface GetCityByNameCallback
    {
        void onError(String message);
        void onResponse(List<WeatherReportModel> weatherReportModels);
    }


    public void getCityForecastByName(String cityName,GetCityByNameCallback getCityByNameCallback)
    {
        List<WeatherReportModel> weatherReportModels=new ArrayList<>();

        //Step1: Fetch the cityId using cityName
        //Step2: Fetch the forecast using cityId

        getCityId(cityName, new VolleyResponseListener() {
            @Override
            public void onError(String message) {

            }

            @Override
            public void onResponse(String cityId) {

                //...we have city id
                getCityForecastById(cityId, new ForeCastByIdResponse() {
                    @Override
                    public void onError(String message) {

                    }

                    @Override
                    public void onResponse(List<WeatherReportModel> weatherReportModels) {

                        //we have the wather report
                        getCityByNameCallback.onResponse(weatherReportModels);
                    }
                });
            }
        });



    }




}
