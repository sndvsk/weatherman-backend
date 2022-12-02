package com.example.weatherman.component;

import com.example.weatherman.dto.OpenMeteoDTO;
import com.example.weatherman.model.back.OpenMeteo;
import com.example.weatherman.repository.OpenMeteoRepository;
import com.example.weatherman.service.OpenMeteoService;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class OpenMeteoComponent {

    private final OpenMeteoRepository openMeteoRepository;

    private final OpenMeteoService openMeteoService;

    public OpenMeteoComponent(OpenMeteoRepository openMeteoRepository,
                              OpenMeteoService openMeteoService) {
        this.openMeteoRepository = openMeteoRepository;
        this.openMeteoService = openMeteoService;
    }

    public void addData(OpenMeteo openMeteo) {
        openMeteoRepository.save(openMeteo);
    }

    public void getAndFilterResponse(double latitude, double longitude, Date startDate, Date endDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        openMeteoService.setLatitude(Double.toString(latitude));
        openMeteoService.setLongitude(Double.toString(longitude));
        openMeteoService.setStartDate(dateFormat.format(startDate));
        openMeteoService.setEndDate(dateFormat.format(endDate));

        var apiResponse = openMeteoService.getWeather();
        if (apiResponse != null) {
            filterOpenMeteoResponse(apiResponse, startDate, endDate);
        }
    }

    public void deleteAllData() {
        openMeteoRepository.deleteAll();
    }

    public OpenMeteo getDataByDate(Date time) {
        return openMeteoRepository.findByTime(time) == null ? null : openMeteoRepository.findByTime(time);
    }

    public List<OpenMeteo> getAllData() {
        return openMeteoRepository.findAll();
    }

    public void filterOpenMeteoResponse(OpenMeteoDTO.Root root, Date startDate, Date endDate) throws ParseException {

        ArrayList<String> time = root.getHourly().getTime();
        ArrayList<Double> temperature = root.getHourly().getTemperature_2m();

        // they are same size
        boolean check = time.size() == temperature.size();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        if (check) {
            for (int idx = 0; time.size() != idx; idx++) {
                OpenMeteo newData = new OpenMeteo();

                String idxTimeString = time.get(idx);
                Date idxTimeDate = sdf.parse(idxTimeString);

                double idxTemperature = temperature.get(idx);

                newData.setTime(idxTimeDate);
                newData.setTemperature(idxTemperature);

                addData(newData);

            }
        }
    }
}
