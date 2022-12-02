package com.example.weatherman.component;

import com.example.weatherman.dto.YrnoDTO;
import com.example.weatherman.model.back.Yrno;
import com.example.weatherman.repository.YrnoRepository;
import com.example.weatherman.service.YrnoService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class YrnoComponent {

    private final YrnoRepository yrnoRepository;

    private final YrnoService yrnoService;

    public YrnoComponent(YrnoRepository yrnoRepository, YrnoService yrnoService) {
        this.yrnoRepository = yrnoRepository;
        this.yrnoService = yrnoService;
    }

    public void addData(Yrno yrno) {
        yrnoRepository.save(yrno);
    }

    public void getAndFilterResponse(double latitude, double longitude, Date startDate, Date endDate) {
        yrnoService.setLatitude(Double.toString(latitude));
        yrnoService.setLongitude(Double.toString(longitude));

        var apiResponse = yrnoService.getWeather();
        if (apiResponse != null) {
            filterYrnoResponse(apiResponse, startDate, endDate);
        }
    }

    public void deleteAllData() {
        yrnoRepository.deleteAll();
    }

    public Yrno getDataByDate(Date time) {
        return yrnoRepository.findByTime(time) == null ? null : yrnoRepository.findByTime(time);
    }

    public List<Yrno> getAllData() {
        return yrnoRepository.findAll();
    }

    public void filterYrnoResponse(YrnoDTO.Root root, Date startDate, Date endDate) {

        // because without this +1 day add it will not show whole endDate day
        Date endDatePlus = new Date(endDate.getTime() + (1000 * 60 * 60 * 24));

        ArrayList<YrnoDTO.Timeseries> dataFromResponse = root.getProperties().getTimeseries();

        for (int idx = 0; dataFromResponse.size() != idx; idx++) {
            Yrno newData = new Yrno();

            Date idxTime = dataFromResponse.get(idx).getTime();
            if (idxTime.compareTo(startDate) >= 0 && idxTime.compareTo(endDatePlus) <= 0) {
                double idxTemperature = dataFromResponse.get(idx).getData().getInstant().getDetails().getAir_temperature();

                newData.setTime(idxTime);
                newData.setTemperature(idxTemperature);

                addData(newData);
            }
        }
    }

}
