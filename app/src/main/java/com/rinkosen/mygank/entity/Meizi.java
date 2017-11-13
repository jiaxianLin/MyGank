package com.rinkosen.mygank.entity;

import java.util.Date;

/**
 * Created by rinkousen on 17/10/17.
 */

public class Meizi extends BaseEntity{

    public boolean used;
    public String type;//干货类型，如Android，iOS，福利等
    public String url;//链接地址
    public String who;//作者
    public String desc;//干货内容的描述
    public Date createdAt;
    public Date updatedAt;
    public Date publishedAt;
}
