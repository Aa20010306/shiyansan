package com.android.forecast.juhe;

public class URLUtils {
    // 在这里替换自己的KEY，其他不要改动
    public static final  String KEY = "7b464b6b3baf3ab199e1f17ea92eccce";
    public static String temp_url = "http://apis.juhe.cn/simpleWeather/query";

    public static String index_url = "http://apis.juhe.cn/simpleWeather/life";

    public static String getTemp_url(String city){
        String url = temp_url+"?city="+city+"&key="+KEY;
        return url;
    }

    public static String getIndex_url(String city){
        String url = index_url+"?city="+city+"&key="+KEY;
        return url;
    }
}
