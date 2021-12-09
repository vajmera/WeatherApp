package com.example.weatherapp;

public class WeatherReportModel {

    //data returned by api forecast for 6 days we have --> First object is jsonObject and then we have jsonArray
//    {"consolidated_weather":
//        [{"id":6410134252158976,"weather_state_name":"Heavy Rain",
//            "weather_state_abbr":"hr",
//            "wind_direction_compass":"S",
//            "created":"2021-12-07T06:59:02.662617Z",
//            "applicable_date":"2021-12-07",
//            "min_temp":1.6800000000000002,
//            "max_temp":8.555,"" +
//            "the_temp":7.51,
//            "wind_speed":11.405774242824572,
//            "wind_direction":169.71316152881056,
//            "air_pressure":998.0,
//            "humidity":79,
//            "visibility":10.249828501550942,
//            "predictability":77},
//        {"id":4897017097617408,"weather_state_name":"Light Rain","weather_state_abbr":"lr","wind_direction_compass":"SW","created":"2021-12-07T06:59:01.559400Z","applicable_date":"2021-12-08","min_temp":5.045,"max_temp":7.8,"the_temp":6.720000000000001,"wind_speed":11.891804566730295,"wind_direction":221.33387178440327,"air_pressure":989.0,"humidity":71,"visibility":12.343538733794638,"predictability":75},{"id":6062169717211136,"weather_state_name":"Light Rain","weather_state_abbr":"lr","wind_direction_compass":"W","created":"2021-12-07T06:59:02.076916Z","applicable_date":"2021-12-09","min_temp":4.619999999999999,"max_temp":7.720000000000001,"the_temp":7.359999999999999,"wind_speed":6.70203681845489,"wind_direction":264.01445573757843,"air_pressure":999.0,"humidity":77,"visibility":12.317441143720671,"predictability":75},{"id":6304391981170688,"weather_state_name":"Light Rain","weather_state_abbr":"lr","wind_direction_compass":"NW","created":"2021-12-07T06:59:01.589056Z","applicable_date":"2021-12-10","min_temp":3.785,"max_temp":6.685,"the_temp":6.36,"wind_speed":9.117284500463958,"wind_direction":312.0006098385439,"air_pressure":998.5,"humidity":76,"visibility":12.506027300564702,"predictability":75},{"id":5314421212577792,"weather_state_name":"Light Rain","weather_state_abbr":"lr","wind_direction_compass":"W","created":"2021-12-07T06:59:01.965932Z","applicable_date":"2021-12-11","min_temp":2.945,"max_temp":6.945,"the_temp":7.075,"wind_speed":5.201019171566054,"wind_direction":271.1539904031686,"air_pressure":1016.5,"humidity":80,"visibility":11.491949514833372,"predictability":75},{"id":4554495737462784,"weather_state_name":"Heavy Rain","weather_state_abbr":"hr","wind_direction_compass":"SSW","created":"2021-12-07T06:59:04.472554Z","applicable_date":"2021-12-12","min_temp":6.23,"max_temp":12.11,"the_temp":9.94,"wind_speed":4.929298894456375,"wind_direction":206.99999999999997,"air_pressure":1019.0,"humidity":96,"visibility":9.143477093772368,"predictability":77}],"time":"2021-12-07T08:40:55.956397Z","sun_rise":"2021-12-07T07:51:45.272696Z","sun_set":"2021-12-07T15:52:06.442768Z","timezone_name":"LMT","parent":{"title":"England","location_type":"Region / State / Province","woeid":24554868,"latt_long":"52.883560,-1.974060"},"sources":[{"title":"BBC","slug":"bbc","url":"http://www.bbc.co.uk/weather/","crawl_rate":360},{"title":"Forecast.io","slug":"forecast-io","url":"http://forecast.io/","crawl_rate":480},{"title":"HAMweather","slug":"hamweather","url":"http://www.hamweather.com/","crawl_rate":360},{"title":"Met Office","slug":"met-office","url":"http://www.metoffice.gov.uk/","crawl_rate":180},{"title":"OpenWeatherMap","slug":"openweathermap","url":"http://openweathermap.org/","crawl_rate":360},{"title":"Weather Underground","slug":"wunderground","url":"https://www.wunderground.com/?apiref=fc30dc3cd224e19b","crawl_rate":720},{"title":"World Weather Online","slug":"world-weather-online","url":"http://www.worldweatheronline.com/","crawl_rate":360}],"title":"London","location_type":"City","woeid":44418,"latt_long":"51.506321,-0.12714","timezone":"Europe/London"}
    private int id;
    private String weather_state_name;
    private String weather_state_abbr;
    private String wind_direction_compass;
    private String created;
    private String applicable_data;
    private float min_temp;
    private float max_temp;
    private float the_temp;
    private float wind_speed;
    private float wind_direction;
    private int air_pressure;
    private int humidity;
    private float visibility;
    private int predictability;

    public WeatherReportModel()
    {}

    public WeatherReportModel(int id, String weather_state_name, String weather_state_abbr, String wind_direction_compass, String created, String applicable_data, float min_temp, float max_temp, float the_temp, float wind_speed, float wind_direction, int air_pressure, int humidity, float visibility, int predictability) {
        this.id = id;
        this.weather_state_name = weather_state_name;
        this.weather_state_abbr = weather_state_abbr;
        this.wind_direction_compass = wind_direction_compass;
        this.created = created;
        this.applicable_data = applicable_data;
        this.min_temp = min_temp;
        this.max_temp = max_temp;
        this.the_temp = the_temp;
        this.wind_speed = wind_speed;
        this.wind_direction = wind_direction;
        this.air_pressure = air_pressure;
        this.humidity = humidity;
        this.visibility = visibility;
        this.predictability = predictability;
    }

    @Override
    public String toString() {
        return
                "Current ='" + weather_state_name + '\'' +
                        ", Date : '" + applicable_data + '\'' + //date
                ", min=" + min_temp +
                ", max=" + max_temp
                ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeather_state_name() {
        return weather_state_name;
    }

    public void setWeather_state_name(String weather_state_name) {
        this.weather_state_name = weather_state_name;
    }

    public String getWeather_state_abbr() {
        return weather_state_abbr;
    }

    public void setWeather_state_abbr(String weather_state_abbr) {
        this.weather_state_abbr = weather_state_abbr;
    }

    public String getWind_direction_compass() {
        return wind_direction_compass;
    }

    public void setWind_direction_compass(String wind_direction_compass) {
        this.wind_direction_compass = wind_direction_compass;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getApplicable_data() {
        return applicable_data;
    }

    public void setApplicable_data(String applicable_data) {
        this.applicable_data = applicable_data;
    }

    public float getMin_temp() {
        return min_temp;
    }

    public void setMin_temp(float min_temp) {
        this.min_temp = min_temp;
    }

    public float getMax_temp() {
        return max_temp;
    }

    public void setMax_temp(float max_temp) {
        this.max_temp = max_temp;
    }

    public float getThe_temp() {
        return the_temp;
    }

    public void setThe_temp(float the_temp) {
        this.the_temp = the_temp;
    }

    public float getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(float wind_speed) {
        this.wind_speed = wind_speed;
    }

    public float getWind_direction() {
        return wind_direction;
    }

    public void setWind_direction(float wind_direction) {
        this.wind_direction = wind_direction;
    }

    public int getAir_pressure() {
        return air_pressure;
    }

    public void setAir_pressure(int air_pressure) {
        this.air_pressure = air_pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public float getVisibility() {
        return visibility;
    }

    public void setVisibility(float visibility) {
        this.visibility = visibility;
    }

    public int getPredictability() {
        return predictability;
    }

    public void setPredictability(int predictability) {
        this.predictability = predictability;
    }
}
