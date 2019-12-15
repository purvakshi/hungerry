package com.codiecon.hungerry.model.dao;

import com.codiecon.hungerry.model.constant.TableName;
import com.codiecon.hungerry.model.enums.MemberType;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import static com.codiecon.hungerry.model.constant.fields.MemberEntityFields.*;

@Builder(toBuilder = true)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = TableName.MEMBER)
public class MemberEntity extends BaseEntity {

    @Column(name = NAME_OF_ORGANISATION)
    private String nameOfOrganisaton;

    @Column(name = REGISTERED_ID, unique = true)
    private String registeredId;

    @Column(name = EMAIL_ID, unique = true)
    private String emailId;

    @Column(name = PHONE_NUMBER)
    private String phoneNumber;

    @Column(name = MOBILE_NUMBER)
    private String mobileNumber;

    @Column(name = OTP_NUMBER)
    private String otpNumber;

    @Column(name = MEMBER_TYPE)
    private MemberType memberType;

    @Column(name = LONGITUDE)
    private Double lng;

    @Column(name = LATITUDE)
    private Double lat;

    @Column(name = ADDRESS)
    private String address;

    @Column(name = IS_APPROVED, columnDefinition = "boolean default false")
    private Boolean isApproved;

    @Column(name = PASSWORD)
    private String password;
}
