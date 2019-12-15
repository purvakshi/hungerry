package com.codiecon.hungerry.model.constant.query;

public interface QueryConstant {

    String DONOR_CHECK = "select count(*) > 0 from donate_food where member_id = :id and created_date < :currTime and expiry_time > :currTime";

    String COLLECTOR_CHECK = "select count(*) > 0 from collect_food where member_id = :id and created_date < :currTime and expiry_time > :currTime";

}
