package com.krishna.coding.mf.dto.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class UserRequest {

    private HashMap<String, Object> request;
}
