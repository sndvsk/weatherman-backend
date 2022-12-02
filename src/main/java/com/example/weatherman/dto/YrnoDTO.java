package com.example.weatherman.dto;

import java.util.ArrayList;
import java.util.Date;

// generated using https://json2csharp.com/code-converters/json-to-pojo
public class YrnoDTO {

    public static class Root {
        public String type;
        public Geometry geometry;
        public Properties properties;

        public Properties getProperties() {
            return properties;
        }
    }

    public static class Properties {
        public Meta meta;
        public ArrayList<Timeseries> timeseries;

        public ArrayList<Timeseries> getTimeseries() {
            return timeseries;
        }
    }

    public static class Timeseries {
        public Date time;
        public Data data;

        public Date getTime() {
            return time;
        }

        public Data getData() {
            return data;
        }
    }

    public static class Data {
        public Instant instant;
        public Next12Hours next_12_hours;
        public Next1Hours next_1_hours;
        public Next6Hours next_6_hours;

        public Instant getInstant() {
            return instant;
        }
    }

    public static class Instant {
        public Details details;

        public Details getDetails() {
            return details;
        }
    }

    public static class Details {
        public double air_pressure_at_sea_level;
        public double air_temperature;
        public double cloud_area_fraction;
        public double cloud_area_fraction_high;
        public double cloud_area_fraction_low;
        public double cloud_area_fraction_medium;
        public double dew_point_temperature;
        public double fog_area_fraction;
        public double relative_humidity;
        public double ultraviolet_index_clear_sky;
        public double wind_from_direction;
        public double wind_speed;
        public double precipitation_amount;
        public double air_temperature_max;
        public double air_temperature_min;

        public double getAir_temperature() {
            return air_temperature;
        }
    }

    // all classes below are not used in this project
    public static class Meta {
        public Date updated_at;
        public Units units;
    }

    public static class Geometry {
        public String type;
        public ArrayList<Integer> coordinates;
    }

    public static class Next12Hours {
        public Summary summary;
    }

    public static class Next1Hours {
        public Summary summary;
        public Details details;
    }

    public static class Next6Hours {
        public Summary summary;
        public Details details;
    }

    public static class Summary {
        public String symbol_code;
        public String symbol_confidence;
    }

    public static class Units {
        public String air_pressure_at_sea_level;
        public String air_temperature;
        public String air_temperature_max;
        public String air_temperature_min;
        public String cloud_area_fraction;
        public String cloud_area_fraction_high;
        public String cloud_area_fraction_low;
        public String cloud_area_fraction_medium;
        public String dew_point_temperature;
        public String fog_area_fraction;
        public String precipitation_amount;
        public String relative_humidity;
        public String ultraviolet_index_clear_sky;
        public String wind_from_direction;
        public String wind_speed;
    }


}
