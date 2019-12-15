package com.codiecon.hungerry.service;
import com.codiecon.hungerry.web.model.request.GenerateOtpRequest;
import com.codiecon.hungerry.web.model.response.BaseResponse;
public interface OtpService {
  BaseResponse generateAndSendOtp(GenerateOtpRequest generateOtpRequest);
  BaseResponse verifyOtp(String emailId, String otpNUmber);
}
