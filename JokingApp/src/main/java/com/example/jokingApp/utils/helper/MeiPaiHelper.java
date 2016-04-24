package com.example.jokingApp.utils.helper;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by idea-pc on 2016/4/16.
 */
public class MeiPaiHelper {
    ArrayList<String> objects = new ArrayList<String>();
    public  void  split(String  string){
        Pattern p = Pattern.compile("\"video\": \"(.*?)\"");
        Matcher m = p.matcher(string);
        while(m.find()) {
            int i=1;
            objects.add(m.group(i++));
        }
    }
}
