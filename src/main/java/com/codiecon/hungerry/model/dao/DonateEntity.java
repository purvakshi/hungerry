package com.codiecon.hungerry.model.dao;

import com.codiecon.hungerry.model.constant.ErrorMessages;
import com.codiecon.hungerry.model.constant.TableName;
import com.codiecon.hungerry.model.constant.fields.FoodEntityFields;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

import static com.codiecon.hungerry.model.constant.fields.DonateEntityFields.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = TableName.DONATE_FOOD)
public class DonateEntity extends BaseEntity {

    @Column(name = PICK_UP_TIME)
    private Date pickUpTime;

    @Column(name = PICKED_UP_TIME)
    private Date pickedUpTime;

    @Column(name = DELIVERED_TIME)
    private Date deliveredTime;

    @Column(name = EXPIRY_TIME)
    private Date expiryTime;

    @Column(name = VEGETARIAN)
    private Boolean vegetarian;

    @Column(name = FEED_COUNT)
    private Integer feedCount;

    @Column(name = "distance")
    private Double distance;

    @Column(name = "lng")
    private Double lng;

    @Column(name = "lat")
    private Double lat;

    @JsonIgnoreProperties
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,targetEntity = FoodEntity.class)
    @JoinColumn(name = FoodEntityFields.DONATE_ID)
    private List<FoodEntity> foodEntityList;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "donate_collect",
            joinColumns = { @JoinColumn(name = "donate_id") },
            inverseJoinColumns = { @JoinColumn(name = "collect_id") })
    private List<CollectEntity> collectEntityList;

    @NotNull(message = ErrorMessages.MEMBER_ENTITY_NOT_NULL)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = MEMBER_ID)
    private MemberEntity memberEntity;

}