package com.krishna.coding.mf.dto.apiResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class MFSchemeHeader {
    private String fund_house;
    private String scheme_type;
    private String scheme_category;
    private Long scheme_code;
    private String scheme_name;
}
