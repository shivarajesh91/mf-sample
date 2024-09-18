package com.krishna.coding.mf.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.krishna.coding.mf.dto.apiResponse.UserRequest;
import com.krishna.coding.mf.dto.apiResponse.UserResponse;
import com.krishna.coding.mf.service.MFDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/mf/data")
@Slf4j
public class MFDataController {

    @Autowired
    private MFDataService mfDataService;

    @GetMapping("/from-api")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<String> getMFData(){
        String response = mfDataService.getAndStoreData();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/filter-api")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<UserResponse> filterMFData(@RequestBody UserRequest request) throws JsonProcessingException {
        log.info("MFDataController here.");
        int schemeId = (int) request.getRequest().get("schemeId");
        String filter = (String) request.getRequest().get("filter");
        Map<String, Object> apiResponse = mfDataService.getMFSchemeDataResponse(schemeId);
        Map<String, Object> filteredData = mfDataService.filterMFData(apiResponse,filter);
        //log.info("filtered data : {}", filteredData);
        UserResponse response = new UserResponse();
        response.setResponse(filteredData);
        //log.info("Controller response : {}", response);
        return ResponseEntity.ok(response);
    }

}
