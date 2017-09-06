package com.basic.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * locate com.basic.dto
 * Created by 79875 on 2017/7/14.
 */
public class EChartsLine implements Serializable{
    private List<Timestamp> timeinfo;
    private List<Long> tupleCount;

    public EChartsLine() {
    }

    public EChartsLine(List<Timestamp> timeinfo, List<Long> tupleCount) {
        this.timeinfo = timeinfo;
        this.tupleCount = tupleCount;
    }

    public List<Timestamp> getTimeinfo() {
        return timeinfo;
    }

    public void setTimeinfo(List<Timestamp> timeinfo) {
        this.timeinfo = timeinfo;
    }

    public List<Long> getTupleCount() {
        return tupleCount;
    }

    public void setTupleCount(List<Long> tupleCount) {
        this.tupleCount = tupleCount;
    }

    @Override
    public String toString() {
        return "EChartsLine{" +
                "timeinfo=" + timeinfo +
                ", tupleCount=" + tupleCount +
                '}';
    }
}
