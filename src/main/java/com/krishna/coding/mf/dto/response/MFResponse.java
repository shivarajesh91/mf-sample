package com.krishna.coding.mf.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MFResponse {
    private String fundHouse;
    private Long schemeCode;
    private String schemeName;
    private List<List<String>> data;

}
