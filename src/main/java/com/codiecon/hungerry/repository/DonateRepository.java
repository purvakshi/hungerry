package com.codiecon.hungerry.repository;

import com.codiecon.hungerry.model.dao.DonateEntity;
import com.codiecon.hungerry.model.enums.MemberType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import static com.codiecon.hungerry.model.constant.query.QueryConstant.DONOR_CHECK;

@Repository
public interface DonateRepository extends JpaRepository<DonateEntity, Long> {

    DonateEntity findByMemberEntityIdAndMemberEntityMemberType(Long memberId, MemberType memberType);

    @Query(value = DONOR_CHECK)
    Boolean donorRequestCheck(@Param("id") Long id, @Param("curr_time") Date time);

    @Query(value = "SELECT * , ( 3959 * acos( cos( radians(?1) ) * cos( radians( member.lat ) ) \n" +
            "* cos( radians( member.lng ) - radians(?2) ) + sin( radians(?1) ) * sin(radians(member.lat)) ) ) AS distance \n" +
            "FROM donate_food "+
            "having donate_food.distance < 7 \n" +
            "ORDER BY donate_food.distance",nativeQuery = true)
    List<DonateEntity> getDonatorsNearBy(Double lat,Double lng);

    List<DonateEntity> findByMemberEntityId(Long memberId);

}
