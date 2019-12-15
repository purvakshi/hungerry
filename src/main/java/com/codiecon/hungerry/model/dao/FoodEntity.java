package com.codiecon.hungerry.model.dao;

import com.codiecon.hungerry.model.constant.ErrorMessages;
import com.codiecon.hungerry.model.constant.TableName;
import com.codiecon.hungerry.model.enums.FoodType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static com.codiecon.hungerry.model.constant.fields.FoodEntityFields.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = TableName.FOOD)
public class FoodEntity extends BaseEntity{

    @NotNull(message = ErrorMessages.DONATE_ENTITY_NOT_NULL)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = DONATE_ID)
    private DonateEntity donateEntity;

    @Column(name = FOOD_NAME)
    private String foodName;

    @Column(name = FOOD_TYPE)
    private FoodType foodType;

    @Column(name = QUANTITY)
    private Integer quantity;

}