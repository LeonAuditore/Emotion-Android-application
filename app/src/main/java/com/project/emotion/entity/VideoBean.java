package com.project.emotion.entity;

import java.io.Serializable;

/**
 * @author 袁茏天
 * @description:
 * @date :2022/3/20 17:52
 */
public class VideoBean implements Serializable {
    private String title;
    private String url;

    public VideoBean(String title, String url) {
        this.title = title;
        this.url = url;
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
