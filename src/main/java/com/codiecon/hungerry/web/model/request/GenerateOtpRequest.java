package com.codiecon.hungerry.web.model.request;
import com.codiecon.hungerry.model.enums.MemberType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenerateOtpRequest {
  private String email;
  private String organisationName;
  private MemberType memberType;
}
