package com.krishna.coding.mf.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MFRequest {
    private Long schemeId;
    private String filter;
}
