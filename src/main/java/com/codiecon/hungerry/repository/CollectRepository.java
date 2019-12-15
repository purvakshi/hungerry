package com.codiecon.hungerry.repository;

import com.codiecon.hungerry.model.dao.CollectEntity;
import com.codiecon.hungerry.model.enums.MemberType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

import static com.codiecon.hungerry.model.constant.query.QueryConstant.COLLECTOR_CHECK;

@Repository
public interface CollectRepository extends JpaRepository<CollectEntity, Long> {

    CollectEntity findByIdAndMemberEntityIdAndMemberEntityMemberType(Long orderId, Long memberId, MemberType memberType);

    CollectEntity findByMemberEntityIdAndMemberEntityMemberType(Long memberId, MemberType memberType);

    @Query(value = COLLECTOR_CHECK)
    Boolean donorRequestCheck(@Param("id") Long id, @Param("curr_time") Date time);

    CollectEntity findByMemberEntityId(Long memberId);

}
