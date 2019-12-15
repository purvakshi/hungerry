package com.codiecon.hungerry.service;

import com.codiecon.hungerry.model.enums.MemberType;
import com.codiecon.hungerry.web.model.request.CollectorRequest;
import com.codiecon.hungerry.web.model.request.FoodDonationRequest;
import com.codiecon.hungerry.web.model.response.BaseResponse;

import java.util.Date;

public interface OrderService {

    BaseResponse savePickedUpTime(Long orderId, Date date);

    BaseResponse saveDeliveredTime(Long orderId, Date date);

    BaseResponse addCollectorRequest(Long collectorId, CollectorRequest collectorRequest);

    BaseResponse editCollectorRequest(Long collectorId, Long orderId, CollectorRequest collectorRequest);

    BaseResponse saveDonorFoodDetails(Long donorId, FoodDonationRequest foodDonationRequest);

    BaseResponse donarRequestCheck(Long memberId);

    BaseResponse collectorRequestCheck(Long memberId);

    BaseResponse getHistory(Long memberId, MemberType memberType);


}
