package com.codiecon.hungerry.service;

import com.codiecon.hungerry.web.model.response.BaseResponse;

public interface GetMemberDetails {
    BaseResponse getMemberDetails(String emailId);
}
