package com.basic.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * locate com.basic.model
 * Created by 79875 on 2017/7/14.
 */
public class TPkggrouping implements Serializable {

    private int id;
    private Timestamp time;
    private Long tuplecount;

    public TPkggrouping(Timestamp time, Long tuplecount) {
        this.time = time;
        this.tuplecount = tuplecount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Long getTuplecount() {
        return tuplecount;
    }

    public void setTuplecount(Long tuplecount) {
        this.tuplecount = tuplecount;
    }

    @Override
    public String toString() {
        return "TPkggrouping{" +
                "id=" + id +
                ", time=" + time +
                ", tuplecount=" + tuplecount +
                '}';
    }
}
