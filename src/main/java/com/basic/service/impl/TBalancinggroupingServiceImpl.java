package com.basic.service.impl;

import com.basic.dao.TBalancinggroupingDAO;
import com.basic.model.TBalancinggrouping;
import com.basic.service.TBalancinggroupingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * locate com.basic.service.impl
 * Created by 79875 on 2017/7/14.
 */
@Service("tBalancinggroupingService")
public class TBalancinggroupingServiceImpl implements TBalancinggroupingService {
    @Autowired
    private TBalancinggroupingDAO balancinggroupingDAO;

    @Override
    public void save(TBalancinggrouping tBalancinggrouping) {
        balancinggroupingDAO.save(tBalancinggrouping);
    }

    @Override
    public void update(TBalancinggrouping tBalancinggrouping) {
        balancinggroupingDAO.update(tBalancinggrouping);
    }

    @Override
    public void merge(TBalancinggrouping tBalancinggrouping) {
        balancinggroupingDAO.getSession().merge(tBalancinggrouping);
    }

    @Override
    public void delete(Long id) {
        balancinggroupingDAO.delete(id);
    }

    @Override
    public TBalancinggrouping get(Long id) {
        return balancinggroupingDAO.getById(id);
    }

    @Override
    public List<TBalancinggrouping> queryALL() {
        return balancinggroupingDAO.findList("from TBalancinggrouping");
    }

    @Override
    public List<Timestamp> getTimeLineInfo() {
        List<Timestamp> list = balancinggroupingDAO.findList("select distinct(time) from TBalancinggrouping order by time");
        return list;
    }

    @Override
    public List<Long> getSumListTupleCountByTimeInfo() {
        List<Long> list = balancinggroupingDAO.findList("select sum(tuplecount) from TBalancinggrouping group by time order by time");
        return list;
    }

    @Override
    public void deleteALL() {
        balancinggroupingDAO.executeUpdate("delete from TBalancinggrouping");
    }
}
