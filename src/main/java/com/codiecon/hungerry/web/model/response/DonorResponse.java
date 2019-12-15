package com.codiecon.hungerry.web.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonorResponse {
    private Long donorId;
    private Long orderId;
    private Boolean vegetarian;
    private Date createdDate;
    private Date expiryDate;
    private Integer feedCount;
    private List<FoodResponse> foodResponseList;
}
