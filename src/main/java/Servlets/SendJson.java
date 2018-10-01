package Servlets;

import Items.IPhone;
import com.google.gson.Gson;

public class SendJson {

    static  private Gson son;

    public static String Send(IPhone first,IPhone second){
        Gson son=new Gson();
        IPhone[] items={first,second};
        String resp=son.toJson(items);
        return resp;
    }

}
