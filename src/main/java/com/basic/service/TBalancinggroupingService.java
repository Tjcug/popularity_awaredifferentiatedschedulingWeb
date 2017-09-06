package com.basic.service;

import com.basic.model.TBalancinggrouping;

import java.sql.Timestamp;
import java.util.List;

/**
 * locate com.basic.service
 * Created by 79875 on 2017/7/14.
 */
public interface TBalancinggroupingService extends BaseService<TBalancinggrouping> {

    public List<Timestamp> getTimeLineInfo();
    public List<Long> getSumListTupleCountByTimeInfo();
    public void deleteALL();
}
