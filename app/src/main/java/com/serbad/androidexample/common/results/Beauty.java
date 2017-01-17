package com.serbad.androidexample.common.results;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhoutingjie on 2016/12/12.
 */

public class Beauty implements Serializable {
    public String id;
    public Date createdAt;
    public String desc;
    public Date publishedAt;
    public String source;
    public String type;
    public String url;
    public boolean used;
    public String who;

}
