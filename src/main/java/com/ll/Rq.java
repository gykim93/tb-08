package com.ll;

import java.util.ArrayList;
import java.util.List;

public class Rq {
    String cmd;
    String action;
    String queryString;
    List<String> paramNames;
    List<String> paramValues;

    Rq(String cmd){
        paramNames = new ArrayList<>();
        paramValues = new ArrayList<>();

        this.cmd = cmd;

        String[] cmdBits = cmd.split("\\?", 2);
        action = cmdBits[0].trim();
        queryString = cmdBits[1].trim();

        String[] queryStringBits = queryString.split("&");

        for (int i = 0; i < queryStringBits.length; i++) {
            String queryParamStr = queryStringBits[i];
            String[] queryParamStrBits = queryParamStr.split("=", 2);

            String paramName = queryParamStrBits[0];
            String paramVale = queryParamStrBits[1];
        }
    }
    String getAction(){
        return action;
    }
    public int getParamAsInt(String paramName, int defaultVale){
        int index = paramNames.indexOf(paramName);

        if (index == -1) return defaultVale;
        String paramValue = paramValues.get(index);
        try {
            return Integer.parseInt(paramValue);
        }catch(NumberFormatException e){
            return defaultVale;
        }
    }
}
