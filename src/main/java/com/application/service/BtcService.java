package com.application.service;

import com.application.entities.BTCInfo;
import com.application.json.SaveInputJson;
import com.application.repositories.BtcRepository;
import com.application.response.BtcInfoResp;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

@Service
public class BtcService {

    BtcRepository repository;
    BTCInfo btcInfo;

    public BtcService(BtcRepository repository) {
        this.repository = repository;
    }

    public List<BtcInfoResp> listBtc(String t1, String t2) {
        List<BTCInfo> btcList = new ArrayList<>();
        List<BtcInfoResp> btcInfoResps = new ArrayList<>();
        StringBuilder sbT1 = new StringBuilder(t1.replaceAll("T", " "));
        int plusPos = sbT1.indexOf("+");
        sbT1 = sbT1.delete(plusPos, plusPos + 5);
        StringBuilder sbT2 = new StringBuilder(t2.replaceAll("T", " "));
        sbT2 = sbT2.delete(plusPos, plusPos + 5);

        Timestamp t = Optional.ofNullable(sbT1.toString())
                .map(Timestamp::valueOf).orElse(null);
        Timestamp tt2 = Optional.ofNullable(sbT2.toString())
                .map(Timestamp::valueOf).orElse(null);
        ZoneId zoneId = ZoneId.of("GMT");
//        ZonedDateTime now = ZonedDateTime.now(zoneId);
        ZonedDateTime start = ZonedDateTime.ofInstant(t.toInstant(), zoneId);
        ZonedDateTime end = ZonedDateTime.ofInstant(tt2.toInstant(), zoneId);
        ZonedDateTime nextDay = start.plusDays(1);
        ZonedDateTime zdt = start;
        while (zdt.isBefore(end)) {
            ZonedDateTime zdtHour = zdt;
            ZonedDateTime zdtNextHour = zdtHour.plusHours(1);
            while (zdtHour.isBefore(zdtNextHour)) {
                BtcInfoResp resp = new BtcInfoResp();
                resp.setTimestamp(zdtHour.toString());
                
                System.out.println(zdtHour.toString());
                // Prepare for next loop.
                zdtHour = zdtHour.plusHours(1);
            }
            // Prepare for next loop.
            zdt = zdt.plusHours(1);
        }

        return null;
    }

    public BTCInfo saveBtc(SaveInputJson inputJson) {
        btcInfo.setTimestamp(convertToGMTStamp(inputJson.getTime().toString()));
        return repository.save(btcInfo);
    }

    public static Timestamp convertToGMTStamp(String timeDateformat) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:sszzzz");
        Date result;
        StringBuilder stb = new StringBuilder(timeDateformat);
        String finalDate = "";
        try {
            int zja = timeDateformat.indexOf("+");
            String timecorform = String.valueOf(stb.deleteCharAt(zja + 3));
            result = df.parse(timecorform);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            finalDate = sdf.format(result);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Timestamp t = Optional.ofNullable(finalDate) // wrap the String into an Optional
                .map(Timestamp::valueOf).orElse(null);
        System.out.println(finalDate);
        return t;
    }

    public static void createHourfromT(Timestamp t, Timestamp t2) {
        ZoneId zoneId = ZoneId.of("GMT");
//        ZonedDateTime now = ZonedDateTime.now(zoneId);
        ZonedDateTime start = ZonedDateTime.ofInstant(t.toInstant(), zoneId);
        ZonedDateTime end = ZonedDateTime.ofInstant(t2.toInstant(), zoneId);
        ZonedDateTime nextDay = start.plusDays(1);
        ZonedDateTime zdt = start;
        while (zdt.isBefore(end)) {
            ZonedDateTime zdtHour = zdt;
            ZonedDateTime zdtNextHour = zdtHour.plusHours(1);
            while (zdtHour.isBefore(zdtNextHour)) {

                System.out.println(zdtHour.toString());
                // Prepare for next loop.
                zdtHour = zdtHour.plusHours(1);
            }
            // Prepare for next loop.
            zdt = zdt.plusHours(1);
        }
    }

    public static void main(String[] args) {
        StringBuilder sbT1 = new StringBuilder("2011-10-05T10:48:01+00:00".replaceAll("T", " "));
        int plusPos = sbT1.indexOf("+");
        sbT1 = sbT1.delete(plusPos, plusPos + 5);
        Timestamp t1 = Optional.ofNullable(sbT1.toString()) // wrap the String into an Optional
                .map(Timestamp::valueOf).orElse(null);
        StringBuilder sbT2 = new StringBuilder("2011-10-05T18:48:02+00:00".replaceAll("T", " "));
        sbT2 = sbT2.delete(plusPos, plusPos + 5);
        Timestamp t2 = Optional.ofNullable(sbT2.toString()) // wrap the String into an Optional
                .map(Timestamp::valueOf).orElse(null);
        createHourfromT(t1, t2);
    }
}
