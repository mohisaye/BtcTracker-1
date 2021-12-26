package com.application.controllers;

import com.application.entities.BTCInfo;
import com.application.json.GetListInputJson;
import com.application.json.SaveInputJson;
import com.application.response.BtcInfoResp;
import com.application.service.BtcService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/btc")
public class BtcController {
    BtcService btcService;

    public BtcController(BtcService btcService) {
        this.btcService = btcService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BtcInfoResp> getBtcInfo(@RequestBody GetListInputJson inputJson ) {
        List<BtcInfoResp> btcInfoResp=new ArrayList<>();
        btcService.listBtc(inputJson.getT1(),inputJson.getT2());
        return btcInfoResp;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public BTCInfo saveBtc(@RequestBody final SaveInputJson inputJson) {
        return btcService.saveBtc(inputJson);
    }

}
