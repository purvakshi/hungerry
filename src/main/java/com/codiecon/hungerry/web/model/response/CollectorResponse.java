package com.codiecon.hungerry.web.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectorResponse {
    private Integer quantityNeeded;
    private Date requestCreatedDate;
    private Long id;
    private Long collectorId;
}
