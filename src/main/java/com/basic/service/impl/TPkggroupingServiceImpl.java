package com.basic.service.impl;

import com.basic.dao.TPkggroupingDAO;
import com.basic.model.TPkggrouping;
import com.basic.service.TPkggroupingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * locate com.basic.service.impl
 * Created by 79875 on 2017/7/14.
 */
@Service("tPkggroupingService")
public class TPkggroupingServiceImpl implements TPkggroupingService {
    @Autowired
    private TPkggroupingDAO pkggroupingDAO;

    @Override
    public void save(TPkggrouping tPkggrouping) {
        pkggroupingDAO.save(tPkggrouping);
    }

    @Override
    public void update(TPkggrouping tPkggrouping) {
        pkggroupingDAO.update(tPkggrouping);
    }

    @Override
    public void merge(TPkggrouping tPkggrouping) {
        pkggroupingDAO.getSession().merge(tPkggrouping);
    }

    @Override
    public void delete(Long id) {
        pkggroupingDAO.delete(id);
    }

    @Override
    public TPkggrouping get(Long id) {
        return pkggroupingDAO.getById(id);
    }

    @Override
    public List<TPkggrouping> queryALL() {
        return pkggroupingDAO.findList("from TPkggrouping");
    }

    @Override
    public List<Timestamp> getTimeLineInfo() {
        List<Timestamp> list = pkggroupingDAO.findList("select distinct(time) from TPkggrouping order by time");
        return list;
    }

    @Override
    public List<Long> getSumListTupleCountByTimeInfo() {
        List<Long> list = pkggroupingDAO.findList("select sum(tuplecount) from TPkggrouping group by time order by time");
        return list;
    }

    @Override
    public void deleteALL() {
        pkggroupingDAO.executeUpdate("delete from TPkggrouping");
    }
}
