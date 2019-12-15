package com.codiecon.hungerry.web.model.request;

import com.codiecon.hungerry.model.enums.FoodType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FoodRequest {
    private Long foodId;
    private String foodName;
    private FoodType foodType;
    private Integer quantity;
}
