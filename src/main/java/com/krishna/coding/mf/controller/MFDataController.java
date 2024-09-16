package com.krishna.coding.mf.controller;

import com.krishna.coding.mf.dto.request.RequestDto;
import com.krishna.coding.mf.dto.response.ResponseDto;
import com.krishna.coding.mf.service.MFDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/mf/data")
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
    public ResponseEntity<ResponseDto> filterMFData(@RequestBody RequestDto request){
        ResponseDto response = mfDataService.getAndFilterMFData(request);
        return ResponseEntity.ok(response);
    }

}
