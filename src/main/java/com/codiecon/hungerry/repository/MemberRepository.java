package com.codiecon.hungerry.repository;

import com.codiecon.hungerry.model.dao.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

  MemberEntity findByEmailId(String emailId);
  MemberEntity findByEmailIdAndOtpNumber(String emailId, String otpNumber);}
