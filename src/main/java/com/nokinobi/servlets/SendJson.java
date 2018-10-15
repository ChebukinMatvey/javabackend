package com.nokinobi.servlets;

import com.google.gson.Gson;
import com.nokinobi.items.IPhone;


public class SendJson {

    static  private Gson son;

    public static String Send(IPhone first,IPhone second){
        Gson son=new Gson();
        IPhone[] items={first,second};
        String resp=son.toJson(items);
        return resp;
    }

}
