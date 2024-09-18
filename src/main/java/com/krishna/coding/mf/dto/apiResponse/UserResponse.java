package com.krishna.coding.mf.dto.apiResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class UserResponse {

    private Map<String, Object> response;
}
