package com.codiecon.hungerry.service.serviceImpl;

import com.codiecon.hungerry.model.dao.MemberEntity;
import com.codiecon.hungerry.model.enums.ErrorCode;
import com.codiecon.hungerry.repository.MemberRepository;
import com.codiecon.hungerry.service.VerifyLogin;
import com.codiecon.hungerry.web.model.request.LogInRequest;
import com.codiecon.hungerry.web.model.response.BaseResponse;
import com.codiecon.hungerry.web.model.response.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
public class VerifyLogInImpl implements VerifyLogin {

    @Autowired
    MemberRepository memberRepository;

    @Override
    public BaseResponse verifyMember(LogInRequest logInRequest) {
        List<ErrorResponse> errorResponseList = new ArrayList<>();
        MemberEntity memberEntity = memberRepository.findByEmailId(logInRequest.getEmailId());
        if (memberEntity == null) {
            errorResponseList.add(new ErrorResponse(ErrorCode.MEMBER_DOES_NOT_EXIST.toString(), ErrorCode.getErrorMessage(ErrorCode.MEMBER_DOES_NOT_EXIST)));
            return BaseResponse.builder()
                    .data(false)
                    .errors(errorResponseList)
                    .build();
        }
        String generatedPassword = getMd5(logInRequest.getPassword());
        if (generatedPassword.equals(memberEntity.getPassword())){
            return BaseResponse.builder()
                    .data(true)
                    .build();}
        errorResponseList.add(new ErrorResponse(ErrorCode.INVALID_CREDENTIALS.toString(), ErrorCode.getErrorMessage(ErrorCode.INVALID_CREDENTIALS)));
        return BaseResponse.builder()
                .data(false)
                .errors(errorResponseList)
                .build();
    }

    public String getMd5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}

