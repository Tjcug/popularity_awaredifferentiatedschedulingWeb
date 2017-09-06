package com.basic.controller;

import com.basic.util.LinuxShellUitl;
import org.apache.storm.Config;
import org.apache.storm.generated.KillOptions;
import org.apache.storm.generated.Nimbus;
import org.apache.storm.generated.TopologySummary;
import org.apache.storm.thrift.TException;
import org.apache.storm.utils.NimbusClient;
import org.apache.storm.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * locate com.basic.controller
 * Created by 79875 on 2017/7/18.
 */
@Controller
public class StormController extends BaseController {

    private Map conf;

    private Nimbus.Client client ;

    public StormController() {
        conf= Utils.readStormConfig();
        //nimbus服务器地址
        conf.put(Config.NIMBUS_HOST, "root2");
        //nimbus thrift地址
        conf.put(Config.NIMBUS_THRIFT_PORT, 6627);
    }

    /**
     * killTopology 杀死StromTopology
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/storm/killTopology"
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody()
    public String killTopology(@RequestParam String topologyName) throws IOException, TException {
        boolean kill = false;
        client =NimbusClient.getConfiguredClient(conf).getClient();
        List<TopologySummary> topologyList = client.getClusterInfo().get_topologies();
        for(TopologySummary topologySummary : topologyList) {
            if(topologySummary.get_name().equals(topologyName)) {
                KillOptions killOpts = new KillOptions();//延迟杀死时间，单位秒
                killOpts.set_wait_secs(1);
                client.killTopologyWithOpts(topologyName, killOpts);
                kill = true;
                System.out.println("killed " + topologyName);
            }
        }
        if(!kill){
            System.out.println(topologyName + " not started");
            return gson.toJson(false);
        }
        return gson.toJson(true);
    }

    @RequestMapping(value = "/storm/submit/keyGroupingBalancingTopology"
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody()
    public String submitkeyGroupingBalancingTopology() throws IOException, TException {
        boolean reuslt=execLinuxShell("/home/tj/softwares/apache-storm-1.0.2/bin/storm jar /root/TJ/popularity_awaredifferentiatedscheduling-1.0-SNAPSHOT.jar com.basic.benchmark.keyGroupingBalancingTopology keyGroupingBalancing testTopic 18","/root/TJ/logs/keyGroupingBalancing.log");
        return gson.toJson(reuslt);
    }

    @RequestMapping(value = "/storm/submit/partialKeyGroupingTopology"
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody()
    public String partialKeyGroupingTopology() throws IOException, TException {
        boolean reuslt=execLinuxShell("/home/tj/softwares/apache-storm-1.0.2/bin/storm jar /root/TJ/popularity_awaredifferentiatedscheduling-1.0-SNAPSHOT.jar com.basic.benchmark.partialKeyGroupingTopology partialKeyGroupingTopology testTopic 18","/root/TJ/logs/partialKeyGroupingTopology.log");
        return gson.toJson(reuslt);
    }

    private boolean execLinuxShell(String commnd,String outFile) throws IOException {
        return LinuxShellUitl.exec(hostname, user, password, 22, commnd,outFile);
    }
}
