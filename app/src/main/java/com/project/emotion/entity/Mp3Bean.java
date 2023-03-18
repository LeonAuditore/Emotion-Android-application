package com.project.emotion.entity;

import java.io.Serializable;

/**
 * @author 袁茏天
 * @description:
 * @date :2022/4/28 17:52
 */

public class Mp3Bean implements Serializable{
    private String title;
    private String url;

    public Mp3Bean(String title,String url){
        this.title=title;
        this.url=url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
