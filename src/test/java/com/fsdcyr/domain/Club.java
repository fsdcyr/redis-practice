package com.fsdcyr.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by fsdcyr on 2017/10/11
 */
public class Club implements Serializable {
    private int id;             // id
    private String name;        // 名称
    private String info;        // 描述
    private Date createDate;    // 创建日期
    private int rank;           // 排名

    public Club(int id, String name, String info, Date createDate, int rank) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.createDate = createDate;
        this.rank = rank;
    }
}
