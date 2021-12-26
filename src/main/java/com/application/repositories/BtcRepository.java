package com.application.repositories;

import com.application.entities.BTCInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface BtcRepository extends CrudRepository<BTCInfo, Long> {
//   List<BTCInfo>  findAllByTimeStampIsLessThanEqualAndTimeStamp (TimeStamp time1,TimeStamp time2);
   List<BTCInfo> findAllByDateTimeIsLessThanEqual(String t1);
    @Query("select BTCInfo.timestamp , BTCInfo.amount from BTCInfo u where u.timestamp <= ?2 and u.timestamp>?1")
    List<BTCInfo> findByTimeStamp(Timestamp t1, Timestamp t2);
}
