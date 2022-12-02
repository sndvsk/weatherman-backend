package com.example.weatherman.component;

import com.example.weatherman.dto.WeatherApiDTO;
import com.example.weatherman.model.back.WeatherApi;
import com.example.weatherman.repository.WeatherApiRepository;
import com.example.weatherman.service.WeatherApiService;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class WeatherApiComponent {

    private final WeatherApiRepository weatherApiRepository;

    private final WeatherApiService weatherapiService;

    public WeatherApiComponent(WeatherApiRepository weatherApiRepository, WeatherApiService weatherapiService) {
        this.weatherApiRepository = weatherApiRepository;
        this.weatherapiService = weatherapiService;
    }

    public void addData(WeatherApi weatherApi) {
        weatherApiRepository.save(weatherApi);
    }

    @SuppressWarnings("CommentedOutCode")
    public void getAndFilterResponse(double latitude, double longitude, Date startDate, Date endDate) {

        /*
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String start = dateFormat.format(startDate);
        String end = dateFormat.format(endDate);
        Date firstDate = dateFormat.parse(start);
        Date secondDate = dateFormat.parse(end);
        long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) + 1;
        */
        //weatherapiService.setDayCount(Long.toString(diff));

        weatherapiService.setLatitude(Double.toString(latitude));
        weatherapiService.setLongitude(Double.toString(longitude));
        // this is max days for this api
        weatherapiService.setDayCount("10");

        var apiResponse = weatherapiService.getWeather();
        if (apiResponse != null) {
            filterWeatherApiResponse(apiResponse, startDate, endDate);
        }
    }

    public void deleteAllData() {
        weatherApiRepository.deleteAll();
    }

    public WeatherApi getDataByDate(Date time) {
        return weatherApiRepository.findByTime(time) == null ? null : weatherApiRepository.findByTime(time);
    }

    public List<WeatherApi> getAllData() {
        return weatherApiRepository.findAll();
    }

    private void filterWeatherApiResponse(WeatherApiDTO.Root root, Date startDate, Date endDate) {

        // because without this +1 day add it will not show whole endDate day
        Date endDatePlus = new Date(endDate.getTime() + (1000 * 60 * 60 * 24));

        ArrayList<WeatherApiDTO.Forecastday> dataFromResponse = root.getForecast().getForecastday();
        String timezone = root.getLocation().getTz_id();

        // "2022-11-30 00:00"
        String dateFormat = "yyyy-MM-dd HH:mm";

        for (int idxDay = 0; dataFromResponse.size() != idxDay; idxDay++) {

            ArrayList<WeatherApiDTO.Hour> dataByHour = dataFromResponse.get(idxDay).getHour();

            for (int idxHour = 0; dataByHour.size() != idxHour; idxHour++) {

                WeatherApi newData = new WeatherApi();

                String idxTimeString = dataByHour.get(idxHour).getTime();

                // convert to UTC
                // https://stackoverflow.com/questions/24240896/convert-date-and-time-in-any-timezone-to-utc-zone#:~:text=13-,tl%3Bdr,-LocalDateTime.parse(
                Instant parsedToUTCDate = LocalDateTime.parse(
                        idxTimeString,
                        DateTimeFormatter.ofPattern(dateFormat))
                        .atZone(ZoneId.of(timezone))
                        .toInstant();

                Date idxTimeDate = Date.from(parsedToUTCDate);

                if (idxTimeDate.compareTo(startDate) >= 0 && idxTimeDate.compareTo(endDatePlus) <= 0) {

                    double idxTemperature = dataByHour.get(idxHour).getTemp_c();

                    newData.setTime(idxTimeDate);
                    newData.setTemperature(idxTemperature);

                    addData(newData);
                }
            }
        }
    }

}
