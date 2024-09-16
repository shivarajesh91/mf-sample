package com.krishna.coding.mf.service;

import com.krishna.coding.mf.dto.DataApiResponse;
import com.krishna.coding.mf.entity.MFData;
import org.springframework.stereotype.Service;

@Service
public class ApiDataMapper {
    public MFData toTableData(DataApiResponse response) {
        if(response == null){
            return null;
        }
        return MFData.builder()
                .schemeCode(response.getSchemeCode())
                .schemeName(response.getSchemeName())
                .build();
    }
}
