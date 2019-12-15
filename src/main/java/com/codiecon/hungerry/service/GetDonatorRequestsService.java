package com.codiecon.hungerry.service;

import com.codiecon.hungerry.web.model.response.BaseResponse;

public interface GetDonatorRequestsService {
    BaseResponse getDonatorRequests(Long memberId);
}
