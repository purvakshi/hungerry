package com.codiecon.hungerry.model.dao;

import com.codiecon.hungerry.model.constant.ErrorMessages;
import com.codiecon.hungerry.model.constant.TableName;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.codiecon.hungerry.model.constant.fields.CollectEntityFields.MEMBER_ID;
import static com.codiecon.hungerry.model.constant.fields.CollectEntityFields.QUANTITY_NEEDED;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = TableName.COLLECT_FOOD)
public class CollectEntity extends BaseEntity {

    @Column(name=  QUANTITY_NEEDED)
    private Integer quantityNeeded;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "collectEntityList")
    List<DonateEntity> donateEntityList;

    @NotNull(message = ErrorMessages.MEMBER_ENTITY_NOT_NULL)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = MEMBER_ID)
    private MemberEntity memberEntity;

}
