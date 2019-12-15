package com.codiecon.hungerry.service.serviceImpl;

import com.codiecon.hungerry.model.dao.MemberEntity;
import com.codiecon.hungerry.repository.MemberRepository;
import com.codiecon.hungerry.service.ServiceApi;
import com.codiecon.hungerry.web.model.request.AddMemberRequest;
import com.codiecon.hungerry.web.model.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class ServiceImpl implements ServiceApi {

  @Autowired
  MemberRepository memberRepository;

  @Override
  public BaseResponse addMemberDetail(AddMemberRequest addMemberRequest) {
    MemberEntity memberEntity = memberRepository.findByEmailId(addMemberRequest.getEmailId());
    String generatedPassword = getMd5(addMemberRequest.getPassword());
    memberEntity.setAddress(addMemberRequest.getAddress());
    memberEntity.setRegisteredId(addMemberRequest.getRegisteredId());
    memberEntity.setPhoneNumber(addMemberRequest.getPhoneNumber());
    memberEntity.setMobileNumber(addMemberRequest.getMobileNumber());
    memberEntity.setPassword(generatedPassword);
    memberRepository.save(memberEntity);
    return BaseResponse.builder()
            .data(true)
            .build();
  }

  public String getMd5(String input)
  {
    try {

      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] messageDigest = md.digest(input.getBytes());
      BigInteger no = new BigInteger(1, messageDigest);
      String hashtext = no.toString(16);
      while (hashtext.length() < 32) {
        hashtext = "0" + hashtext;
      }
      return hashtext;
    }
    catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }
}
