package com.krishna.coding.mf.dto.apiResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Cacheable(value ="MFScheme")
public class MFSchemeResponse {
    private MFSchemeHeader meta;
    private List<MFSchemeData> data;
    private String status;
}
