package com.example.weatherman.dto;

import java.util.ArrayList;

// generated using https://json2csharp.com/code-converters/json-to-pojo
public class OpenMeteoDTO {

    public static class Root {
        public int elevation;
        public double generationtime_ms;
        public Hourly hourly;
        public HourlyUnits hourly_units;
        public int latitude;
        public double longitude;
        public String timezone;
        public String timezone_abbreviation;
        public int utc_offset_seconds;

        public Hourly getHourly() {
            return hourly;
        }
    }

    public static class Hourly {
        public ArrayList<Double> temperature_2m;
        public ArrayList<String> time;

        public ArrayList<Double> getTemperature_2m() {
            return temperature_2m;
        }

        public ArrayList<String> getTime() {
            return time;
        }
    }

    // all classes below are not used in this project
    public static class HourlyUnits {
        public String temperature_2m;
        public String time;
    }

}
