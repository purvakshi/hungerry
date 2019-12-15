package com.codiecon.hungerry.service.serviceImpl;
import com.codiecon.hungerry.model.dao.MemberEntity;
import com.codiecon.hungerry.model.enums.ErrorCode;
import com.codiecon.hungerry.repository.MemberRepository;
import com.codiecon.hungerry.service.OtpService;
import com.codiecon.hungerry.web.model.request.GenerateOtpRequest;
import com.codiecon.hungerry.web.model.response.BaseResponse;
import com.codiecon.hungerry.web.model.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@Service
@Slf4j
public class OtpServiceImpl implements OtpService {
  @Autowired
  private MemberRepository memberRepository;
  @Autowired
  private JavaMailSender javaMailSender;
  @Override
  public BaseResponse generateAndSendOtp(GenerateOtpRequest generateOtpRequest) {
    MemberEntity memberEntity = memberRepository.findByEmailId(generateOtpRequest.getEmail());
    List<ErrorResponse> errorResponseList = new ArrayList<>();
    if(memberEntity != null) {
      errorResponseList.add(new ErrorResponse(ErrorCode.MEMBER_EXISTS.toString(), ErrorCode.getErrorMessage(ErrorCode.MEMBER_EXISTS)));
      return BaseResponse.builder()
              .errors(errorResponseList)
              .build();
    }
    Random rnd = new Random();
    int number = rnd.nextInt(9999);
    String otp = String.format("%04d", number);
    SimpleMailMessage msg = new SimpleMailMessage();
    msg.setTo(generateOtpRequest.getEmail());
    msg.setSubject("Hunggery Verification");
    msg.setText("Your verification code for Hungerry is " + otp);
    memberEntity = MemberEntity.builder()
            .emailId(generateOtpRequest.getEmail())
            .nameOfOrganisaton(generateOtpRequest.getOrganisationName())
            .memberType(generateOtpRequest.getMemberType())
            .otpNumber(otp)
            .build();
    memberEntity = memberRepository.save(memberEntity);
    if(memberEntity != null) {
      try {
        javaMailSender.send(msg);
        log.info("Mail sent to {}", generateOtpRequest.getEmail());
      } catch (Exception ex) {
        log.info("Mail not sent {}", generateOtpRequest.getEmail());
        errorResponseList.add(new ErrorResponse(ErrorCode.MAIL_NOT_SENT.toString(), ErrorCode.getErrorMessage(ErrorCode.MAIL_NOT_SENT)));
        return BaseResponse.builder()
                .errors(errorResponseList)
                .build();
      }
      return BaseResponse.builder()
              .data(true)
              .build();
    }
    errorResponseList.add(new ErrorResponse(ErrorCode.INVALID_MEMBER.toString(), ErrorCode.getErrorMessage(ErrorCode.INVALID_MEMBER)));
    return BaseResponse.builder()
            .errors(errorResponseList)
            .build();
  }
  @Override
  public BaseResponse verifyOtp(String emailId, String otpNUmber) {
    MemberEntity memberEntity = memberRepository.findByEmailIdAndOtpNumber(emailId, otpNUmber);
    if(memberEntity != null) {
      log.info("Valid credentials {}", emailId);
      return BaseResponse.builder()
              .data(true)
              .build();
    }
    log.info("invalid credentials {}", emailId);
    return BaseResponse.builder()
            .data(false)
            .build();
  }
}
