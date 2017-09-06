<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>KeyGroupingBalancing 负载均衡实例负载情况UI</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- ECharts单文件引入 -->
<#--<script src="http://echarts.baidu.com/build/dist/echarts-all.js"></script>-->
    <script src="/js/echarts.min.js"></script>
    <script src="/js/mycharts.js"></script>
    <script src="/js/jquery.min.js" type="text/javascript"></script>
    <script src="/js/bootstrap.min.js" type="text/javascript"></script>
</head>

<body>

<div class="container" style="width:94%">
    <div class="jumbotron">
        <h2>GroupingBalancing 吞吐量情况UI</h2>
        <div class="row center-block">
            <div class="col-md-6">
                <div class="btn-group" role="group" aria-label="...">
                    <button id="submitPkgGroupingTopology" type="button" class="btn btn-primary">提交Topology任务</button>
                    <button id="killPkgGroupingTopology" type="button" class="btn btn-primary">清除Topology任务</button>
                    <button id="cleanPkgGroupingresult" type="button" class="btn btn-primary">清空pkgGrouping数据</button>
                </div>
            </div>
            <div class="col-md-6">
                <div class="btn-group" role="group" aria-label="...">
                    <button id="submitKeyBalancingTopology" type="button" class="btn btn-primary">提交Topology任务</button>
                    <button id="killKeyBalancingTopology" type="button" class="btn btn-primary">清除Topology任务</button>
                    <button id="cleankeyGroupingresult" type="button" class="btn btn-primary">清空DSGrouping数据</button>
                </div>
            </div>
        </div>
    </div>

    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div class="row" style="width:100%">
        <div class="col-md-6" id="pkgGrouping" style="height:400px;background: #b0c1d2"></div>
        <div class="col-md-6" id="mainKeyGrouping" style="height:400px;background: #b0c1d2"></div>
    </div>

</div>



<!-- 保存当前结果模态框（Modal） -->
<div class="modal fade" id="saveresultModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    保存当前统计结果
                </h4>
            </div>
            <div class="modal-body">
                <div class="input-group">
                    <input id="fileNameText" type="text" class="form-control" placeholder="输入文件名"
                           aria-describedby="basic-addon2">
                    <span class="input-group-addon" id="basic-addon2">.json</span>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button id="saveresultbtn" type="button" class="btn btn-primary">
                    提交更改
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="loaddata" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <h3>正在加载中........</h3>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<script type="text/javascript">
    // init()方法
    var timer1={};
    var timer2={};
    $(function () {
        var keyGroupingChart = echarts.init(document.getElementById('mainKeyGrouping'));
        var pkgGroupingChart = echarts.init(document.getElementById('pkgGrouping'));
        showEchartsBalancing("/echarts/balancinggrouping",keyGroupingChart,"区分调度流处理系统总体吞吐量情况");
        showEchartsPkggrouping("/echarts/pkggrouping",pkgGroupingChart,"pkg调度流处理系统总体吞吐量情况");
        $("#cleankeyGroupingresult").click(function () {
            requestURLData("/echarts/cleanbalancinggrouping");
        });
        $("#cleanPkgGroupingresult").click(function () {
            requestURLData("/echarts/cleanpkggrouping");
        });
        $("#submitKeyBalancingTopology").click(function () {
            requestURLData("/storm/submit/keyGroupingBalancingTopology");
        });
        $("#killKeyBalancingTopology").click(function () {
            requestURLDataByData("/storm/killTopology",{'topologyName':'keyGroupingBalancing'});
        });
        $("#submitPkgGroupingTopology").click(function () {
            requestURLData("/storm/submit/partialKeyGroupingTopology");
        });
        $("#killPkgGroupingTopology").click(function () {
            requestURLDataByData("/storm/killTopology",{'topologyName':'partialKeyGroupingTopology'});
        });
    });
</script>
<script type="text/javascript">

    //请求URL请求
    function requestURLData(url){
        $('#loaddata').modal('show');
        $.ajax({
            url: url,
            dataType: 'json',
            data: {},
            type: "post",
            success: function (data) {
                $('#loaddata').modal('hide');
                alert(data);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
            }
        });
    }

    //请求URL请求 含有请求Request 数据
    function requestURLDataByData(url,data){
        $('#loaddata').modal('show');
        $.ajax({
            url: url,
            dataType: 'json',
            data: data,
            type: "post",
            success: function (data) {
                $('#loaddata').modal('hide');
                alert(data);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
            }
        });
    }

    //实时显示当前spout输入源的吞吐量
    function showEchartsBalancing(url,myChart,titleText) {
        timer1=window.setInterval(function () {
            $.ajax({
                url: url,
                dataType: 'json',
                data: {},
                type: "post",
                success: function (data) {
                    echartsRedBrokenLineInit(myChart, data, titleText);
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
//                    alert(XMLHttpRequest.status);
//                    alert(XMLHttpRequest.readyState);
//                    alert(textStatus);
                }
            });
        },1000);
    }

    function showEchartsPkggrouping(url,myChart,titleText) {
        timer2=window.setInterval(function () {
            $.ajax({
                url: url,
                dataType: 'json',
                data: {},
                type: "post",
                success: function (data) {
                    echartsRedBrokenLineInit(myChart, data, titleText);
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
//                        alert(XMLHttpRequest.status);
//                        alert(XMLHttpRequest.readyState);
//                        alert(textStatus);
                }
            });
        },1000);
    }

</script>
</body>
</html>
