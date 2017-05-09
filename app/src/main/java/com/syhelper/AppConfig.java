package com.syhelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by LGL on 2017/3/23.
 */

public class AppConfig {

    public static String userName = "18809482891";
    public static String pwd = "123456";

    /**199302
     * http://mvvideo1.meitudata.com/58ce8dc5de0d85240.mp4
     * http://mvvideo2.meitudata.com/58ce9d5a800fc4823.mp4
     * http://mvvideo1.meitudata.com/58d0e5e8c07e13122.mp4
     *
     */

    public static String meinv="https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=210247948,878525714&fm=23&gp=0.jpg";
    public static String meinv2="https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2180617992,1826994362&fm=23&gp=0.jpg";
    public static String touxiang="https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2061790778,3511140148&fm=23&gp=0.jpg";
    public static String dashi="https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2888058950,796563220&fm=23&gp=0.jpg";
    public static String minjia="https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3690217952,2390921021&fm=23&gp=0.jpg";
    public static String fengjin="https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1693519432,2537248941&fm=11&gp=0.jpg";

    public static List<String> mImage;

    static{
        mImage = new ArrayList<>();
        mImage.add("http://img1.imgtn.bdimg.com/it/u=1542428606,4103287419&fm=23&gp=0.jpg");
        mImage.add("http://img1.imgtn.bdimg.com/it/u=3575834960,2281753887&fm=23&gp=0.jpg");
        mImage.add("http://img4.imgtn.bdimg.com/it/u=3135258808,233131092&fm=23&gp=0.jpg");
        mImage.add("http://img3.imgtn.bdimg.com/it/u=3853888603,898333842&fm=23&gp=0.jpg");
        mImage.add("http://img1.imgtn.bdimg.com/it/u=55442764,2706312597&fm=23&gp=0.jpg");
        mImage.add("http://img3.imgtn.bdimg.com/it/u=963354121,787213310&fm=23&gp=0.jpg");
        mImage.add("http://img5.imgtn.bdimg.com/it/u=3412371719,3305800528&fm=23&gp=0.jpg");
        mImage.add("http://img5.imgtn.bdimg.com/it/u=1062104105,1154269956&fm=23&gp=0.jpg");
        mImage.add("http://img3.imgtn.bdimg.com/it/u=2399383760,3721039433&fm=23&gp=0.jpg");
        mImage.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1122239514,1571166673&fm=23&gp=0.jpg");
        mImage.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1740544340,1640739433&fm=23&gp=0.jpg");
        mImage.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1829724652,3881343831&fm=23&gp=0.jpg");
        mImage.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=172760889,1533739890&fm=23&gp=0.jpg");
        mImage.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3236950055,2352554342&fm=23&gp=0.jpg");
        mImage.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=55442764,2706312597&fm=23&gp=0.jpg");
        mImage.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=963354121,787213310&fm=23&gp=0.jpg");
        mImage.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2374094051,3873990557&fm=23&gp=0.jpg");
        mImage.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3098368113,1918972906&fm=23&gp=0.jpg");
        mImage.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3996679157,1512682360&fm=23&gp=0.jpg");
    }

    public static String getImageUrl(){
        int index=new Random().nextInt(19);
        return AppConfig.mImage.get(index);
    }
}
