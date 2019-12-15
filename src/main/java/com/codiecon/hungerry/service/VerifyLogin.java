package com.codiecon.hungerry.service;

import com.codiecon.hungerry.web.model.request.LogInRequest;
import com.codiecon.hungerry.web.model.response.BaseResponse;

public interface VerifyLogin {
    BaseResponse verifyMember(LogInRequest logInRequest);
}
