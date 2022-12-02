package com.example.weatherman.component;

import com.example.weatherman.model.back.*;
import com.example.weatherman.repository.GraphRepository;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class GraphComponent {

    private final GraphRepository graphRepository;
    private final YrnoComponent yrnoComponent;
    private final OpenMeteoComponent openMeteoComponent;
    private final WeatherApiComponent weatherApiComponent;

    public GraphComponent(GraphRepository graphRepository,
                          YrnoComponent yrnoComponent,
                          OpenMeteoComponent openMeteoComponent,
                          WeatherApiComponent weatherApiComponent) {
        this.graphRepository = graphRepository;
        this.yrnoComponent = yrnoComponent;
        this.openMeteoComponent = openMeteoComponent;
        this.weatherApiComponent = weatherApiComponent;
    }

    public void addData(Graph graph) {
        graphRepository.save(graph);
    }

    public void deleteAllData() {
        graphRepository.deleteAll();
    }

    public List<Graph> getAllData() {
        return graphRepository.findAll();
    }

    // fixme
    // check contains api names and if no do not make column
    public void parseDataFromModels(List<Yrno> yrnoList, List<OpenMeteo> openMeteoList, List<WeatherApi> weatherApiList, String apiNames) {
        Map.Entry<String, Integer> maxListSize = biggestListSize(yrnoList, openMeteoList, weatherApiList);

        deleteAllData();

        boolean containsYrno = apiNames.contains("yrnoApi");
        boolean containsOpenMeteo = apiNames.contains("openmeteoApi");
        boolean containsWeatherApi = apiNames.contains("weatherApi");

        boolean yrnoMax = maxListSize.getKey().equals("yrnoList");
        boolean openMeteoMax = maxListSize.getKey().equals("openMeteoList");
        boolean weatherApiMax = maxListSize.getKey().equals("weatherApiList");

        // todo implement
        for (int idx = 0; maxListSize.getValue() != idx; idx++) {
            Graph newRow = new Graph();
            Date graphTime;

            if (yrnoMax) {
                graphTime = yrnoList.get(idx).getTime();
            } else if (openMeteoMax) {
                graphTime = openMeteoList.get(idx).getTime();
            } else {
                graphTime = weatherApiList.get(idx).getTime();
            }

            newRow.setTime(graphTime);

            Yrno yrnoTest = yrnoComponent.getDataByDate(graphTime);
            if (yrnoTest != null) {
                newRow.setYrnoTemperature(yrnoTest.getTemperature());
            } else if (containsYrno) {
                newRow.setYrnoTemperature(Double.NaN);
            }

            OpenMeteo openMeteoTest = openMeteoComponent.getDataByDate(graphTime);
            if (openMeteoTest != null) {
                newRow.setOpenmeteoTemperature(openMeteoTest.getTemperature());
            } else if (containsOpenMeteo) {
                newRow.setOpenmeteoTemperature(Double.NaN);
            }

            WeatherApi weatherApiTest = weatherApiComponent.getDataByDate(graphTime);
            if (weatherApiTest != null) {
                newRow.setWeatherApiTemperature(weatherApiTest.getTemperature());
            } else if (containsWeatherApi) {
                newRow.setWeatherApiTemperature(Double.NaN);
            }

            addData(newRow);
        }
    }

    public Map.Entry<String, Integer> biggestListSize(List<Yrno> yrnoList, List<OpenMeteo> openMeteoList, List<WeatherApi> weatherApiList) {
        int sizeYrnoList = yrnoList.size();
        int sizeOpenMeteoList = openMeteoList.size();
        int sizeWeatherApiList = weatherApiList.size();

        Map<String, Integer> map = new HashMap<>();
        map.put("yrnoList", sizeYrnoList);
        map.put("openMeteoList", sizeOpenMeteoList);
        map.put("weatherApiList", sizeWeatherApiList);

        return getMaxEntryInMapBasedOnValue(map);
    }

    // method is taken from here
    // https://www.geeksforgeeks.org/how-to-find-the-entry-with-largest-value-in-a-java-map/#:~:text=map.entrySet())%20%0A%7B%20//%20Operations%20%7D-,Example%3A,-Java
    public static <K, V extends Comparable<V> > Map.Entry<K, V> getMaxEntryInMapBasedOnValue(Map<K, V> map) {
        // To store the result
        Map.Entry<K, V> entryWithMaxValue = null;
        // Iterate in the map to find the required entry
        for (Map.Entry<K, V> currentEntry : map.entrySet()) {
            if (entryWithMaxValue == null || currentEntry.getValue().compareTo(entryWithMaxValue.getValue()) > 0) {
                entryWithMaxValue = currentEntry;
            }
        }
        // Return the entry with highest value
        return entryWithMaxValue;
    }

}
