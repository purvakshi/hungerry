package com.codiecon.hungerry.service.serviceImpl;

import com.codiecon.hungerry.model.dao.MemberEntity;
import com.codiecon.hungerry.model.enums.ErrorCode;
import com.codiecon.hungerry.repository.MemberRepository;
import com.codiecon.hungerry.service.GetMemberDetails;
import com.codiecon.hungerry.web.model.response.BaseResponse;
import com.codiecon.hungerry.web.model.response.ErrorResponse;
import com.codiecon.hungerry.web.model.response.MemberDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetMemberDetailsImpl implements GetMemberDetails {

    @Autowired
    MemberRepository memberRepository;

    @Override
    public BaseResponse getMemberDetails(String emailId) {
        MemberEntity memberEntity = memberRepository.findByEmailId(emailId);
        if(memberEntity!=null){
            MemberDetailsResponse memberDetailsResponse = new MemberDetailsResponse();
            memberDetailsResponse.setAddress(memberEntity.getAddress());
            memberDetailsResponse.setEmailId(memberEntity.getEmailId());
            memberDetailsResponse.setMemberType(memberEntity.getMemberType());
            memberDetailsResponse.setMobileNumber(memberEntity.getMobileNumber());
            memberDetailsResponse.setPhoneNumber(memberEntity.getPhoneNumber());
            memberDetailsResponse.setNameOfOrganisaton(memberEntity.getNameOfOrganisaton());
            return BaseResponse.builder()
                    .data(memberDetailsResponse)
                    .build();
        }
        List<ErrorResponse> errorResponseList = new ArrayList<>();
        errorResponseList.add(new ErrorResponse(ErrorCode.MEMBER_DOES_NOT_EXIST.toString(),ErrorCode.getErrorMessage(ErrorCode.MEMBER_DOES_NOT_EXIST)));
        return BaseResponse.builder().errors(errorResponseList).build();
    }
}
