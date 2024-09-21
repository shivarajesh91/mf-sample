package com.krishna.coding.mf.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.krishna.coding.mf.dto.DataApiResponse;
import com.krishna.coding.mf.dto.api.MFSchemeData;
import com.krishna.coding.mf.entity.MFData;
import com.krishna.coding.mf.repository.MFDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MFDataService {

    @Value("${data.from.api.url}")
    private String apiUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MFDataRepository repository;

    @Autowired
    private ApiDataMapper mapper;

    @Autowired
    private ObjectMapper objectMapper;

    public String getAndStoreData() {

        // Make the API call
        ResponseEntity<DataApiResponse[]> response = restTemplate.getForEntity(apiUrl, DataApiResponse[].class);
        log.info("response : {}", response);
        if(response.getBody() != null && response.getStatusCode() == HttpStatus.OK){
            List<DataApiResponse> list = Arrays.asList(response.getBody());
            List<MFData> data = convertResponseToData(list);
            repository.saveAll(data);
        }else {
            // Handle errors
            throw new RuntimeException("API call failed: " + response.getStatusCode());
        }

        return "data saved to table successfully!.";

    }

    private List<MFData> convertResponseToData(List<DataApiResponse> list) {
        return list.stream().map(mapper::toTableData).collect(Collectors.toList());
    }

    public Map<String, Object> filterMFData(Map<String, Object> data, String filter) {
        log.info("MFDataService -> filterMFData() here.");
        Map<String, Object> response = new LinkedHashMap<>();

        Map<String, Object> meta = (Map<String, Object>) data.get("meta");
        response.put("fundHouse",meta.get("fund_house"));
        response.put("schemeCode",meta.get("scheme_code"));
        response.put("schemeName",meta.get("scheme_name"));
        //log.info("meta response : {}", response);

        List<Map<String, String>> originalData = (List<Map<String, String>>) data.get("data");
        List<MFSchemeData> jsonData = objectMapper.convertValue(originalData, new TypeReference<List<MFSchemeData>>() {});

        LocalDate now = LocalDate.now();
        LocalDate startDate = getStartDate(now, filter);

        List<MFSchemeData> dateDataList = jsonData.stream()
                .filter(dateData -> LocalDate.parse(dateData.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")).isAfter(startDate))
                .collect(Collectors.toList());
        List<String> dates = dateDataList.stream().map(MFSchemeData::getDate).toList();
        List<String> navs = dateDataList.stream().map(MFSchemeData::getNav).toList();
        List<List<String>> filteredData = new ArrayList<>();
        filteredData.add(dates);
        filteredData.add(navs);
        response.put("data",filteredData);
        //log.info("response: {}", response);
        return response;
    }

    private LocalDate getStartDate(LocalDate now, String filter) {
        switch (filter){
            case "1W" -> {
                return now.minusWeeks(1);
            }
            case "1M" -> {
                return now.minusMonths(1);
            }
            case "1Y" -> {
                return now.minusYears(1);
            }
            case "5Y" -> {
                return now.minusYears(5);
            }
            default -> throw new IllegalArgumentException("Invalid filter : " + filter);
        }
    }

    @Cacheable(value = "MFScheme", key = "#schemeId")
    public Map<String, Object> getMFSchemeDataResponse(int schemeId) throws JsonProcessingException {
        log.info("MFDataService -> getMFSchemeDataResponse() here.");
        apiUrl = apiUrl + "/" + schemeId;
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
        String jsonData = response.getBody();
        //log.info(" API response body : {}", jsonData);
        Map<String, Object> data = objectMapper.readValue(jsonData, new TypeReference<Map<String, Object>>() {});
        return data;
    }
}
