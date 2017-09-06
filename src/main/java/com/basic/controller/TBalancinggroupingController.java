package com.basic.controller;

import com.basic.dto.EChartsLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

/**
 * locate com.basic.controller
 * Created by 79875 on 2017/7/14.
 */
@Controller
public class TBalancinggroupingController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(TBalancinggroupingController.class);

    /**
     * 将tupleCount统计结果输出到Echarts界面上
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/echarts/balancinggrouping"
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody()
    public String balancinggroupingEchartsUI() throws IOException {
        //return gsonUtil.readJsonStream(new FileInputStream(StormSpoutGsonFilePath+fileName));
        List<Timestamp> timeLineInfo = balancinggroupingService.getTimeLineInfo();
        List<Long> tupleCountList=balancinggroupingService.getSumListTupleCountByTimeInfo();
        EChartsLine chartsLine=new EChartsLine(timeLineInfo,tupleCountList);
        return gson.toJson(chartsLine);
    }

    /**
     * 清除数据库所有数据
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/echarts/cleanbalancinggrouping"
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody()
    public String cleanbalancinggrouping() throws IOException {
        balancinggroupingService.deleteALL();
        return gson.toJson(true);
    }
}
