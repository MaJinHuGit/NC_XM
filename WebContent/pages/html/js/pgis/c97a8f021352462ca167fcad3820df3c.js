/**
 * Created by csy on 2017/6/23.
 */

/**
 * 系统配置
 */
var SysConfig_Founder = {
     //POI WMTS 服务地址
    EzPOIWMTSV1 : 'http://10.65.4.73:3839/EzPOISearchNew/PoiWMTSV1',
    //POI REST 服务地址
    EzPOIRESTV1 : 'http://10.65.4.73:3839/EzPOISearchNew/PoiSearchV1REST',
    //PGIS地图后台地址
    PGIS_MAP_SERVER : "http://10.65.4.73:4769",
    //颜色配置，1为绿，2为黄，3为橙，4为红
    Colors:{1:"#008000",2:"#FFFF00",3:"#cf38ff",4:"#FF0000"},
    //PGIS地图前台地址
    PGIS_MAP_CLIENT : "http://10.65.4.73:81",
    //PGIS地图客户端服务地址
    PGIS_MAP:"http://10.65.4.73:3535/EzServerClientV7.1/",
    //redisServer 地址
    GPSserver :  "http://10.65.4.73:3838/RedisServer",
    //GPS信息对接类型（mlfw或redis）,为目录联网服务时需部署对应GPS后台,为redis时需要部署redisServer相关一套
    GPSConnect : "redis",
    //用户名，当GPSConnect类型为redis时需要该配置
    GPSQXUser:"admin",

    GPSRefresh:1000*10,
    //GPS下线时间 (毫秒)
    GPSOffline:1000*60*60*24,
    //GPS过滤时间，超过时间认为是无用数据
    GPSFileter:1000*60*60*24*90,
    //PGIS地图打印服务后台地址
	
    EzPrintServer:"http://10.65.4.75:3536/EzPrintServer/PrintService",
    //EzServer地图底图地址
    EzServer:'http://10.65.4.74:3534/PGIS_S_TileMapServer',
      //图层字段展示级别
    LayerFieldDisplayLevel:'03',
	//默认地图底图名称,打印地图时无法读取地图名称时使用
    EzMapDefaultName:'SL',
	//全文检索图层撒点字段统一配置（根据图层进行配置）
    EzPOISearchLayer:{
        PGIS_JZ_ZDRKXX: {
            X:"JD",  //经度
            Y:"WD",  //纬度
            XM:"XM", //姓名
            SHIJXZQH:"SHIJXZQH",  //市级行政区划
            SJXZQH:"SJXZQH",   //省级行政区划
            ZJHM:"ZJHM" //证件号码
        }
    },
DynamicHeatLayers:[
    {
        CHNAME:"警情",
        ENNAME:"B_ASJ_JQ_PT"
    }
]

};

//成都开发环境（根据成都开发环境EzServerClient结构配置，根据实际情况注释，上下只释放一个）
reloadCss(SysConfig_Founder.PGIS_MAP+'/lib/EzServerClient.min.css');
reloadJs(SysConfig_Founder.PGIS_MAP+'/lib/EzServerClient.min.js');
reloadJs(SysConfig_Founder.PGIS_MAP+'/lib/EzMapAPI.js');
reloadJs(SysConfig_Founder.PGIS_MAP_CLIENT+'/map/lib/echarts.source.js');
//生产环境（根据生产环境EzServerClient结构配置）
/*reloadCss(SysConfig_Founder.PGIS_MAP+'/lib/EzServerClient.min.css');
reloadJs(SysConfig_Founder.PGIS_MAP+'/lib/EzServerClient.min.js');
reloadJs(SysConfig_Founder.PGIS_MAP+'/lib/EzMapAPI.js');*/

reloadCss(SysConfig_Founder.PGIS_MAP_CLIENT+'/css/main.css');
reloadCss(SysConfig_Founder.PGIS_MAP_CLIENT+'/css/ol.css');
reloadCss(SysConfig_Founder.PGIS_MAP_CLIENT+'/css/p-ol3.min.css');
reloadCss(SysConfig_Founder.PGIS_MAP_CLIENT+'/css/defaults.css');
reloadCss(SysConfig_Founder.PGIS_MAP_CLIENT+'/css/mapTool.css');
reloadCss(SysConfig_Founder.PGIS_MAP_CLIENT+'/css/popup.css');
reloadCss(SysConfig_Founder.PGIS_MAP_CLIENT+'/css/timeline.css');
reloadCss(SysConfig_Founder.PGIS_MAP_CLIENT+'/css/iconfont/iconfontPop.css');

reloadJs(SysConfig_Founder.PGIS_MAP_CLIENT+'/map/lib/javascript.util.js');
reloadJs(SysConfig_Founder.PGIS_MAP_CLIENT+'/map/lib/assign.polyfill.js');
reloadJs(SysConfig_Founder.PGIS_MAP_CLIENT+'/map/lib/jquery-3.2.1.min.js');
reloadJs(SysConfig_Founder.PGIS_MAP_CLIENT+'/map/lib/reconnecting-websocket.js');
reloadJs(SysConfig_Founder.PGIS_MAP_CLIENT+'/map/lib/WebSocket.js');

reloadJs(SysConfig_Founder.PGIS_MAP_CLIENT+'/map/mapProvider/Interface.js');
reloadJs(SysConfig_Founder.PGIS_MAP_CLIENT+'/map/lib/ajaxTool.js');
reloadJs(SysConfig_Founder.PGIS_MAP_CLIENT+'/map/lib/GeoUtils.js');
reloadJs(SysConfig_Founder.PGIS_MAP_CLIENT+'/map/mapProvider/pgis/pgisHotMapLayerServer.js');
reloadJs(SysConfig_Founder.PGIS_MAP_CLIENT+'/map/mapProvider/pgis/pgisMarkerLayerServer.js');
reloadJs(SysConfig_Founder.PGIS_MAP_CLIENT+'/map/mapProvider/pgis/pgisContentServer.js');
reloadJs(SysConfig_Founder.PGIS_MAP_CLIENT+'/map/mapProvider/pgis/pgisInitMapServer.js');
reloadJs(SysConfig_Founder.PGIS_MAP_CLIENT+'/map/mapProvider/pgis/pgisAreaMapServer.js');
reloadJs(SysConfig_Founder.PGIS_MAP_CLIENT+'/map/mapProvider/pgis/pgisGpsMapLayerServer.js');
reloadJs(SysConfig_Founder.PGIS_MAP_CLIENT+'/map/mapProvider/pgis/pgisTrackMapServer.js');
reloadJs(SysConfig_Founder.PGIS_MAP_CLIENT+'/map/mapProvider/trackMapServer.js');
reloadJs(SysConfig_Founder.PGIS_MAP_CLIENT+'/map/mapProvider/pgis/pgisPrintMapServer.js');
reloadJs(SysConfig_Founder.PGIS_MAP_CLIENT+'/map/mapProvider/printMapServer.js');

EzTileLayerMultiHotSpot = null;
goog = null;
P = null;
MapToolServer = null;
javascript = null;
Interface = null;
util = null;
jQuery = null;

setTimeout(function(){
    if(Interface) {
        reloadJs(SysConfig_Founder.PGIS_MAP_CLIENT+"/map/mapProvider/hotMapLayerServer.js");
        reloadJs(SysConfig_Founder.PGIS_MAP_CLIENT+"/map/mapProvider/markerLayerServer.js");
        reloadJs(SysConfig_Founder.PGIS_MAP_CLIENT+"/map/mapProvider/contentServer.js");
        reloadJs(SysConfig_Founder.PGIS_MAP_CLIENT+"/map/mapProvider/initMapServer.js");
        reloadJs(SysConfig_Founder.PGIS_MAP_CLIENT+"/map/mapProvider/areaMapServer.js");
        reloadJs(SysConfig_Founder.PGIS_MAP_CLIENT+"/map/mapProvider/gpsMapLayerServer.js");
    }else{
        setTimeout(arguments.callee,100);
    }
},100);

setTimeout(function () {
    if(goog){
        reloadJs(SysConfig_Founder.PGIS_MAP_CLIENT+'/map/lib/p-ol3.min.js');
        reloadJs(SysConfig_Founder.PGIS_MAP_CLIENT+'/map/lib/EzServerClient_GPS.min.js');
        reloadJs(SysConfig_Founder.PGIS_MAP_CLIENT+'/map/lib/EzServerClient-Echarts.min.js');
    }else {
        setTimeout(arguments.callee,100);
    }
},100);

setTimeout(function () {
    if(P){
        reloadJs(SysConfig_Founder.PGIS_MAP_CLIENT+'/map/mapProvider/pgis/pgisMapToolServer.js');
        reloadJs(SysConfig_Founder.PGIS_MAP_CLIENT+'/map/mapProvider/mapToolServer.js');
    }else {
        setTimeout(arguments.callee,100);
    }
},100);

setTimeout(function () {
    if(javascript){
        reloadJs(SysConfig_Founder.PGIS_MAP_CLIENT+'/map/lib/jsts.js');
    }else {
        setTimeout(arguments.callee,100);
    }
},100);

setTimeout(function () {
    if(EzTileLayerMultiHotSpot&&MapToolServer){
        reloadJs(SysConfig_Founder.PGIS_MAP_CLIENT+'/map/pgisMap.js');
    }else {
        setTimeout(arguments.callee,100);
    }
    if(jQuery){
          reloadJs(SysConfig_Founder.PGIS_MAP_CLIENT+'/map/lib/timeline.js');
    }else {
        setTimeout(arguments.callee,100);
    }

},100);

function reloadAbleJSFn(id,newJS)
{
    var oldjs = null;
    var t = null;
    var oldjs = document.getElementById(id);
    if(oldjs) oldjs.parentNode.removeChild(oldjs);
    var scriptObj = document.createElement("script");
    scriptObj.src = newJS;
    scriptObj.type = "text/javascript";
    scriptObj.id   = id;
    document.getElementsByTagName("head")[0].appendChild(scriptObj);
}


function reloadJs(src)
{
    var oHead = document.getElementsByTagName('HEAD').item(0);
    var oScript= document.createElement("script");
    oScript.type = "text/javascript";
    oScript.src=src;
    oHead.appendChild( oScript);
}

function reloadCss(src)
{
    var oHead = document.getElementsByTagName('HEAD').item(0);
    var oLink= document.createElement("link");
    oLink.type = "text/css";
    oLink.rel = "stylesheet";
    oLink.href=src;
    oHead.appendChild( oLink);
}