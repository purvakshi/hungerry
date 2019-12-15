package com.codiecon.hungerry.web;

import com.codiecon.hungerry.model.enums.MemberType;
import com.codiecon.hungerry.service.GetMemberDetails;
import com.codiecon.hungerry.service.ServiceApi;
import com.codiecon.hungerry.service.VerifyLogin;
import com.codiecon.hungerry.web.model.request.AddMemberRequest;
import com.codiecon.hungerry.web.model.request.LogInRequest;
import com.codiecon.hungerry.web.model.response.BaseResponse;
import com.codiecon.hungerry.web.model.response.MemberDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hungerry")
public class MemberController {

  @Autowired
  private ServiceApi addMemberCommand;

  @Autowired
  private VerifyLogin verifyLogin;

  @Autowired
  private GetMemberDetails getMemberDetails;

  @PostMapping(value = "/addMember")
  public ResponseEntity<BaseResponse<Boolean>> addMember(@RequestBody AddMemberRequest addMemberRequest) {
    return new ResponseEntity<>(addMemberCommand.addMemberDetail(addMemberRequest), HttpStatus.OK);
  }

  @PostMapping(value = "/verifyLogIn")
  public ResponseEntity<BaseResponse<Boolean>> verifyMember(@RequestBody LogInRequest logInRequest) {
    return new ResponseEntity<>(verifyLogin.verifyMember(logInRequest), HttpStatus.OK);
  }

  @GetMapping(value = "/getMemBerDetails")
  public ResponseEntity<BaseResponse<MemberDetailsResponse>> getMemberDetails(@RequestParam(value = "memberId") String emailId) {
    return new ResponseEntity<>(getMemberDetails.getMemberDetails(emailId), HttpStatus.OK);
  }

}