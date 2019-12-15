package com.codiecon.hungerry.service.serviceImpl;

import com.codiecon.hungerry.model.dao.CollectEntity;
import com.codiecon.hungerry.model.dao.DonateEntity;
import com.codiecon.hungerry.model.dao.MemberEntity;
import com.codiecon.hungerry.repository.CollectRepository;
import com.codiecon.hungerry.repository.DonateRepository;
import com.codiecon.hungerry.repository.MemberRepository;
import com.codiecon.hungerry.service.GetDonatorRequestsService;
import com.codiecon.hungerry.web.model.response.BaseResponse;
import com.codiecon.hungerry.web.model.response.DonatorsNearByResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GetDonatorRequestsServiceImpl implements GetDonatorRequestsService {

    @Autowired
    CollectRepository collectRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    DonateRepository donateRepository;

    @Override
    public BaseResponse getDonatorRequests(Long memberId) {
        CollectEntity collectEntity = collectRepository.findByMemberEntityId(memberId);
        MemberEntity memberEntity = memberRepository.findById(collectEntity.getId()).get();
        String[] location = memberEntity.getLocation().split(",");
        List<DonateEntity> donateEntityList = donateRepository.getDonatorsNearBy(Double.valueOf(location[0]),Double.valueOf(location[1]));
        List<DonatorsNearByResponse> donatorsNearByResponseList = new ArrayList<>();
        donateEntityList.forEach(donateEntity -> {
            if (new Date().getTime() >= donateEntity.getPickUpTime().getTime() && new Date().getTime() <= donateEntity.getExpiryTime().getTime()) {
                DonatorsNearByResponse donatorsNearByResponse = new DonatorsNearByResponse();
                donatorsNearByResponse.setDonateEntity(donateEntity);
                if (donateEntity.getCollectEntityList().size() != 0) {
                    donatorsNearByResponse.setIsAccepted(true);
                }
                donatorsNearByResponseList.add(donatorsNearByResponse);
            }
        });
        return BaseResponse.builder()
                .data(donatorsNearByResponseList)
                .build();
    }
}
