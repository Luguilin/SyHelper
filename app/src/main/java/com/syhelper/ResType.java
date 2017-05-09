package com.syhelper;

/**
 * Created by LGL on 2017/5/9.
 */

public enum ResType {
    Default("",-1),Picture("recImageShow",0),VIDEO("recVideo",1),recMasterShow("recMasterShow",2),Route("route",3);

    private String type;
    private int values;

    ResType(String type, int values) {
        this.type = type;
        this.values = values;
    }

    public String getType() {
        return type;
    }

    public int getValues() {
        return values;
    }
}
