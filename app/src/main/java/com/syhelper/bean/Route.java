package com.syhelper.bean;

/**
 * Created by LGL on 2017/3/22.
 */

public class Route {

    /**
     * id : 108
     * hText :
     * titleImageURL : /syhelp/images/route/tmp_1485277815868308132.jpg
     * explanation : 西藏自治区（藏文：བོད་རང་སྐྱོང་ལྗོངས།，藏语拼音：Poi Ranggyong Jong，
     * 威利转写：Bod rang skyong ljongs），简称“藏”，通称西藏，位于中国西南边陲，首府拉萨，是中国五个少数民族自治区之一
     */

    private String id;
    private String hText;
    private String titleImageURL;
    private String explanation;

    //拓展的
    private Resource resource;

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHText() {
        return hText;
    }

    public void setHText(String hText) {
        this.hText = hText;
    }

    public String getTitleImageURL() {
        return titleImageURL;
    }

    public void setTitleImageURL(String titleImageURL) {
        this.titleImageURL = titleImageURL;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
