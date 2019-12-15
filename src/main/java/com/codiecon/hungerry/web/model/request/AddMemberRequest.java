package com.codiecon.hungerry.web.model.request;

import com.codiecon.hungerry.model.enums.MemberType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = "true")
public class AddMemberRequest {

  private String nameOfOrganisaton;
  private String registeredId;
  private String emailId;
  private String phoneNumber;
  private String mobileNumber;
  private MemberType memberType;
  private String location;
  private String address;
  private String password;

}
