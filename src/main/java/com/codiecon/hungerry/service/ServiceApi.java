package com.codiecon.hungerry.service;

import com.codiecon.hungerry.web.model.request.AddMemberRequest;
import com.codiecon.hungerry.web.model.response.BaseResponse;

public interface ServiceApi {

  BaseResponse addMemberDetail(AddMemberRequest addMemberRequest);
}
