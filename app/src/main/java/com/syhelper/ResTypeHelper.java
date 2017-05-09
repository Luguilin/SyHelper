package com.syhelper;

/**
 * Created by LGL on 2017/5/9.
 */

public class ResTypeHelper {
    public static ResType getType(String type) {
        switch (type) {
            case "recImageShow":
                return ResType.Picture;
            case "recVideo":
                return ResType.VIDEO;
            case "recMasterShow":
                return ResType.Picture;
            case "route":
                return ResType.Route;
            default:
                return ResType.Default;
        }
    }
}
