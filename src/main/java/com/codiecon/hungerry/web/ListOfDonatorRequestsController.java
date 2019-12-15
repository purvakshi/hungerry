package com.codiecon.hungerry.web;

import com.codiecon.hungerry.service.GetDonatorRequestsService;
import com.codiecon.hungerry.web.model.response.BaseResponse;
import com.codiecon.hungerry.web.model.response.DonatorsNearByResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/hungerry")
public class ListOfDonatorRequestsController {

    @Autowired
    GetDonatorRequestsService getDonatorRequestsService;

    @GetMapping(value = "/getDonators")
    public ResponseEntity<BaseResponse<List<DonatorsNearByResponse>>> getDonatorRequests(@RequestParam(value = "memberId") Long memberId){
        return new ResponseEntity<>(getDonatorRequestsService.getDonatorRequests(memberId), HttpStatus.OK);
    }
}
