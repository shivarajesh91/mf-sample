package com.krishna.coding.mf.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "mf_data")
public class MFData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long schemeCode;
    private String schemeName;

}
