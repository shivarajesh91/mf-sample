package com.krishna.coding.mf.service;

import com.krishna.coding.mf.dto.DataApiResponse;
import com.krishna.coding.mf.dto.apiResponse.MFSchemeResponse;
import com.krishna.coding.mf.dto.request.RequestDto;
import com.krishna.coding.mf.dto.response.MFResponse;
import com.krishna.coding.mf.dto.response.ResponseDto;
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

import java.util.Arrays;
import java.util.List;
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

    public ResponseDto getAndFilterMFData(RequestDto requestDto) {
        ResponseEntity<MFSchemeResponse> response = getMFSchemeDataResponse(requestDto.getRequest().getSchemeId());
        //log.info("response : {}", response);
        if (response.getBody() != null && response.getStatusCode() == HttpStatus.OK) {
            MFSchemeResponse apiResponse = response.getBody();

            return ResponseDto.builder().response(
                    MFResponse.builder().fundHouse(apiResponse.getMeta().getFund_house())
                            .schemeCode(apiResponse.getMeta().getScheme_code())
                            .schemeName(apiResponse.getMeta().getScheme_name())
                            .build()
            ).build();
        } else {
            // Handle errors
            throw new RuntimeException("API call failed: " + response.getStatusCode());
        }
    }

    @Cacheable(value = "MFScheme", key = "#schemeId")
    private ResponseEntity<MFSchemeResponse> getMFSchemeDataResponse(Long schemeId) {
        apiUrl = apiUrl +"/"+ schemeId;
        return restTemplate.getForEntity(apiUrl, MFSchemeResponse.class);
    }
}
