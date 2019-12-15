package com.codiecon.hungerry.web.model.response;

import com.codiecon.hungerry.model.enums.MemberType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDetailsResponse {
    private String nameOfOrganisaton;
    private String emailId;
    private String phoneNumber;
    private String mobileNumber;
    private MemberType memberType;
    private String address;
}
