package com.codiecon.hungerry.service.serviceImpl;

import com.codiecon.hungerry.model.dao.*;
import com.codiecon.hungerry.model.enums.ErrorCode;
import com.codiecon.hungerry.model.enums.FoodType;
import com.codiecon.hungerry.model.enums.MemberType;
import com.codiecon.hungerry.repository.CollectRepository;
import com.codiecon.hungerry.repository.DonateRepository;
import com.codiecon.hungerry.repository.FoodRepository;
import com.codiecon.hungerry.repository.MemberRepository;
import com.codiecon.hungerry.service.OrderService;
import com.codiecon.hungerry.web.model.request.CollectorRequest;
import com.codiecon.hungerry.web.model.request.FoodDonationRequest;
import com.codiecon.hungerry.web.model.request.FoodRequest;
import com.codiecon.hungerry.web.model.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private DonateRepository donateRepository;

    @Autowired
    private CollectRepository collectRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private MemberRepository memberRepository;

    public BaseResponse savePickedUpTime(Long orderId, Date date) {
        DonateEntity donateEntity = donateRepository.findById(orderId).get();
        if(donateEntity != null) {
            donateEntity.setPickedUpTime(date);
            donateRepository.save(donateEntity);
            return new BaseResponse(true);
        }
        return new BaseResponse(false);
    }

    public BaseResponse saveDeliveredTime(Long orderId, Date date) {
        DonateEntity donateEntity = donateRepository.findById(orderId).get();
        if(donateEntity != null) {
            donateEntity.setDeliveredTime(date);
            donateRepository.save(donateEntity);
            return new BaseResponse(true);
        }
        return new BaseResponse(false);
    }

    public BaseResponse addCollectorRequest(Long collectorId, CollectorRequest collectorRequest) {
        CollectEntity collectEntity = collectRepository.findByMemberEntityIdAndMemberEntityMemberType(collectorId, MemberType.COLLECTOR);
        if(collectEntity == null || !collectEntity.getIsActive()) {
            collectEntity = CollectEntity.builder()
                    .quantityNeeded(collectorRequest.getCountNeeded())
                    .build();
            collectEntity.setCreatedDate(new Date());
            collectEntity = collectRepository.save(collectEntity);
            return BaseResponse.builder()
                    .data(
                            CollectorResponse.builder()
                                    .id(collectEntity.getId())
                                    .quantityNeeded(collectEntity.getQuantityNeeded())
                                    .requestCreatedDate(collectEntity.getCreatedDate())
                                    .collectorId(collectEntity.getMemberEntity().getId())
                                    .build()
                    )
                    .build();
        }
        List<ErrorResponse> errorResponseList = new ArrayList<>();
        errorResponseList.add(new ErrorResponse(ErrorCode.INVALID_COLLECT_REQUEST.toString(), ErrorCode.getErrorMessage(ErrorCode.INVALID_COLLECT_REQUEST)));
        return BaseResponse.builder()
                .errors(errorResponseList)
                .build();
    }

    public BaseResponse editCollectorRequest(Long collectorId, Long orderId,  CollectorRequest collectorRequest) {
        CollectEntity collectEntity = collectRepository.findByIdAndMemberEntityIdAndMemberEntityMemberType(orderId, collectorId, MemberType.COLLECTOR);
        if(collectEntity != null) {
            collectEntity.setQuantityNeeded(collectorRequest.getCountNeeded());
            collectEntity = collectRepository.save(collectEntity);
            return BaseResponse.builder()
                    .data(
                            CollectorResponse.builder()
                                    .id(collectEntity.getId())
                                    .quantityNeeded(collectEntity.getQuantityNeeded())
                                    .requestCreatedDate(collectEntity.getCreatedDate())
                                    .collectorId(collectEntity.getMemberEntity().getId())
                                    .build()
                    )
                    .build();
        }
        List<ErrorResponse> errorResponseList = new ArrayList<>();
        errorResponseList.add(new ErrorResponse(ErrorCode.INVALID_COLLECT_REQUEST.toString(), ErrorCode.getErrorMessage(ErrorCode.INVALID_COLLECT_REQUEST)));
        return BaseResponse.builder()
                .errors(errorResponseList)
                .build();
    }

    public BaseResponse saveDonorFoodDetails(Long donorId, FoodDonationRequest foodDonationRequest) {
        DonateEntity donateEntity = donateRepository.findByMemberEntityIdAndMemberEntityMemberType(donorId, MemberType.DONOR);
        if(donateEntity == null || donateEntity.getExpiryTime().getTime() < new Date().getTime()) {
            MemberEntity memberEntity = memberRepository.findById(donorId).get();
            int quantity = 0;
            if(memberEntity != null) {
                List<FoodEntity> foodEntityList = new ArrayList<>();
                for(FoodRequest f : foodDonationRequest.getFoodRequestList()) {
                    FoodEntity foodEntity = FoodEntity.builder()
                            .foodName(f.getFoodName())
                            .foodType(f.getFoodType())
                            .quantity(f.getQuantity())
                            .build();
                    if (f.getFoodType().equals(FoodType.BREAD) || f.getFoodType().equals(FoodType.RICE)) {
                        quantity += f.getQuantity().intValue();
                    }
                    foodEntity = foodRepository.saveAndFlush(foodEntity);
                    foodEntityList.add(foodEntity);
                }
                quantity = (int) (quantity / (2.5/3));
                donateEntity = DonateEntity.builder()
                        .vegetarian(foodDonationRequest.getVegetarian())
                        .foodEntityList(foodEntityList)
                        .memberEntity(memberEntity)
                        .feedCount(quantity)
                        .build();

                donateEntity = donateRepository.save(donateEntity);


                List<FoodResponse> foodResponseList = new ArrayList<>();
                donateEntity.getFoodEntityList().forEach(
                        f -> foodResponseList.add(
                                FoodResponse.builder()
                                        .foodName(f.getFoodName())
                                        .foodType(f.getFoodType())
                                        .quantity(f.getQuantity())
                                        .foodId(f.getId())
                                        .build()
                        )
                );
                DonorResponse donorResponse = DonorResponse.builder()
                        .createdDate(donateEntity.getCreatedDate())
                        .donorId(donateEntity.getMemberEntity().getId())
                        .orderId(donateEntity.getId())
                        .vegetarian(donateEntity.getVegetarian())
                        .expiryDate(donateEntity.getExpiryTime())
                        .foodResponseList(foodResponseList)
                        .feedCount(donateEntity.getFeedCount())
                        .build();
                return BaseResponse.builder()
                        .data(donorResponse)
                        .build();
            }
        }
        List<ErrorResponse> errorResponseList = new ArrayList<>();
        errorResponseList.add(new ErrorResponse(ErrorCode.INVALID_REQUEST.toString(), ErrorCode.getErrorMessage(ErrorCode.INVALID_REQUEST)));
        return BaseResponse.builder()
                .errors(errorResponseList)
                .build();
    }

    public BaseResponse donarRequestCheck(Long memberId) {
        return BaseResponse.builder()
                .data(donateRepository.donorRequestCheck(memberId, new Date()))
                .build();
    }

    public BaseResponse collectorRequestCheck(Long memberId) {
        return BaseResponse.builder()
                .data(collectRepository.donorRequestCheck(memberId, new Date()))
                .build();
    }

    @Override
    public BaseResponse getHistory(Long memberId, MemberType memberType) {
        List<DonateEntity> donateEntityList = donateRepository.

        return null;
    }

}