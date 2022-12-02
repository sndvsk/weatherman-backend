package com.example.weatherman.dto;

import java.util.ArrayList;

// generated using https://json2csharp.com/code-converters/json-to-pojo
public class WeatherApiDTO {

    public static class Root {
        public Location location;
        public Current current;
        public Forecast forecast;

        public Forecast getForecast() {
            return forecast;
        }

        public Location getLocation() {
            return location;
        }
    }

    public static class Location {
        public String name;
        public String region;
        public String country;
        public double lat;
        public double lon;
        public String tz_id;
        public int localtime_epoch;
        public String localtime;

        public String getTz_id() {
            return tz_id;
        }
    }

    public static class Forecast {
        public ArrayList<Forecastday> forecastday;

        public ArrayList<Forecastday> getForecastday() {
            return forecastday;
        }
    }

    public static class Forecastday {
        public String date;
        public int date_epoch;
        public Day day;
        public Astro astro;
        public ArrayList<Hour> hour;

        public ArrayList<Hour> getHour() {
            return hour;
        }
    }

    public static class Hour {
        public int time_epoch;
        public String time;
        public double temp_c;
        public double temp_f;
        public int is_day;
        public Condition condition;
        public double wind_mph;
        public double wind_kph;
        public int wind_degree;
        public String wind_dir;
        public double pressure_mb;
        public double pressure_in;
        public double precip_mm;
        public double precip_in;
        public int humidity;
        public int cloud;
        public double feelslike_c;
        public double feelslike_f;
        public double windchill_c;
        public double windchill_f;
        public double heatindex_c;
        public double heatindex_f;
        public double dewpoint_c;
        public double dewpoint_f;
        public int will_it_rain;
        public int chance_of_rain;
        public int will_it_snow;
        public int chance_of_snow;
        public double vis_km;
        public double vis_miles;
        public double gust_mph;
        public double gust_kph;
        public double uv;

        public String getTime() {
            return time;
        }

        public double getTemp_c() {
            return temp_c;
        }
    }

    // all classes below are not used in this project
    public static class Day {
        public double maxtemp_c;
        public double maxtemp_f;
        public double mintemp_c;
        public double mintemp_f;
        public double avgtemp_c;
        public double avgtemp_f;
        public double maxwind_mph;
        public double maxwind_kph;
        public double totalprecip_mm;
        public double totalprecip_in;
        public double totalsnow_cm;
        public double avgvis_km;
        public double avgvis_miles;
        public double avghumidity;
        public int daily_will_it_rain;
        public int daily_chance_of_rain;
        public int daily_will_it_snow;
        public int daily_chance_of_snow;
        public Condition condition;
        public double uv;
    }

    public static class Current {
        public int last_updated_epoch;
        public String last_updated;
        public double temp_c;
        public double temp_f;
        public int is_day;
        public Condition condition;
        public double wind_mph;
        public double wind_kph;
        public int wind_degree;
        public String wind_dir;
        public double pressure_mb;
        public double pressure_in;
        public double precip_mm;
        public double precip_in;
        public int humidity;
        public int cloud;
        public double feelslike_c;
        public double feelslike_f;
        public double vis_km;
        public double vis_miles;
        public double uv;
        public double gust_mph;
        public double gust_kph;
    }

    public static class Astro {
        public String sunrise;
        public String sunset;
        public String moonrise;
        public String moonset;
        public String moon_phase;
        public String moon_illumination;
    }

    public static class Condition {
        public String text;
        public String icon;
        public int code;
    }

}
