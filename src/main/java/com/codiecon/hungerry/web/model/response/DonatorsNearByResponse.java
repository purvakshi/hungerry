package com.codiecon.hungerry.web.model.response;

import com.codiecon.hungerry.model.dao.DonateEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonatorsNearByResponse {

    private DonateEntity donateEntity;
    private Boolean isAccepted;

}
