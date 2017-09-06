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
public class TPkggroupingController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(TPkggroupingController.class);

    /**
     * 将tupleCount统计结果输出到Echarts界面上
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/echarts/pkggrouping"
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody()
    public String pkggroupingEchartsUI() throws IOException {
        //return gsonUtil.readJsonStream(new FileInputStream(StormSpoutGsonFilePath+fileName));
        List<Timestamp> timeLineInfo = pkggroupingService.getTimeLineInfo();
        List<Long> tupleCountList=pkggroupingService.getSumListTupleCountByTimeInfo();
        EChartsLine chartsLine=new EChartsLine(timeLineInfo,tupleCountList);
        return gson.toJson(chartsLine);
    }

    /**
     * 清除数据库所有数据
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/echarts/cleanpkggrouping"
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody()
    public String cleanpkggrouping() throws IOException {
        pkggroupingService.deleteALL();
        return gson.toJson(true);
    }
}
