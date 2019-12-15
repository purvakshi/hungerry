package com.codiecon.hungerry.web;
import com.codiecon.hungerry.service.OtpService;
import com.codiecon.hungerry.web.model.request.GenerateOtpRequest;
import com.codiecon.hungerry.web.model.request.VerifyOtpRequest;
import com.codiecon.hungerry.web.model.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/otp")
public class OtpController {
  @Autowired
  private OtpService otpService;

  @PostMapping(value = "/generateOtp")
  public ResponseEntity<BaseResponse<Boolean>> generateAndSentOtp(@RequestBody GenerateOtpRequest generateOtpRequest) {
    return new ResponseEntity<>(otpService.generateAndSendOtp(generateOtpRequest), HttpStatus.OK);
  }

  @PostMapping(value = "/verifyOtp")
  public ResponseEntity<BaseResponse<Boolean>> verifyOtp(@RequestBody VerifyOtpRequest verifyOtpRequest) {
    return new ResponseEntity<>(otpService.verifyOtp(verifyOtpRequest.getEmailId(), verifyOtpRequest.getOtpNumber()), HttpStatus.OK);
  }
}