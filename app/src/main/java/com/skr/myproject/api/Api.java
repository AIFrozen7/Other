package com.skr.myproject.api;

public class Api {
    public static final String BASE_URL="http://172.17.8.100";
    //商品列表
    public static String SHOPLIST=BASE_URL+"/small/commodity/v1/commodityList";
    //登录
    public static String LOGIN=BASE_URL+"/small/user/v1/login";
    //轮播
    public static final String BUNNER = BASE_URL+"/small/commodity/v1/bannerShow";
    //注册
    public static final String REGIST = BASE_URL+"/small/user/v1/register";


}
