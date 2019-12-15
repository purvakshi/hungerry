package com.codiecon.hungerry.web.model.response;

import com.codiecon.hungerry.model.enums.FoodType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodResponse {
    private Long foodId;
    private String foodName;
    private FoodType foodType;
    private Integer quantity;
}
