package com.codiecon.hungerry.web;

import com.codiecon.hungerry.model.enums.MemberType;
import com.codiecon.hungerry.service.OrderService;
import com.codiecon.hungerry.web.model.request.CollectorRequest;
import com.codiecon.hungerry.web.model.request.FoodDonationRequest;
import com.codiecon.hungerry.web.model.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/savePickedUpTime")
    public ResponseEntity<BaseResponse> savePickedUpTime(@RequestParam("orderId") Long orderId, @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date) {
        return new ResponseEntity<>(orderService.savePickedUpTime(orderId, date), HttpStatus.OK);
    }

    @PostMapping("/saveDeliveredTime")
    public ResponseEntity<BaseResponse> saveDeliveredTime(@RequestParam("orderId") Long orderId, @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date) {
        return new ResponseEntity<>(orderService.saveDeliveredTime(orderId, date), HttpStatus.OK);
    }

    @PostMapping("/addCollectorRequest")
    public ResponseEntity<BaseResponse> addCollectorRequest(@RequestParam("collectorId") Long collectorId, @RequestBody CollectorRequest collectorRequest) {
        return new ResponseEntity<>(orderService.addCollectorRequest(collectorId, collectorRequest), HttpStatus.OK);
    }

    @PostMapping("/editCollectorRequest")
    public ResponseEntity<BaseResponse> editCollectorRequest(@RequestParam("collectorId") Long collectorId,  @RequestParam("orderId") Long orderId, @RequestBody CollectorRequest collectorRequest) {
        return new ResponseEntity<>(orderService.editCollectorRequest(collectorId, orderId, collectorRequest), HttpStatus.OK);
    }

    @PostMapping("/addDonorFoodDetails")
    public ResponseEntity<BaseResponse> saveDonorFoodDetails(@RequestParam("donorId") Long donorId, @RequestBody FoodDonationRequest foodDonationRequest) {
        return new ResponseEntity<>(orderService.saveDonorFoodDetails(donorId, foodDonationRequest), HttpStatus.OK);
    }

    @GetMapping("/donarRequestCheck")
    public ResponseEntity<BaseResponse> donarRequestCheck(@RequestParam("memberId") Long memberId) {
        return new ResponseEntity<>(orderService.donarRequestCheck(memberId), HttpStatus.OK);
    }

    @GetMapping("/collectorRequestCheck")
    public ResponseEntity<BaseResponse> collectorRequestCheck(@RequestParam("memberId") Long memberId) {
        return new ResponseEntity<>(orderService.collectorRequestCheck(memberId), HttpStatus.OK);
    }

}
