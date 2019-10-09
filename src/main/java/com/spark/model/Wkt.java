package com.spark.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Wkt {

    private Integer id;
    private String wkt;

    public Wkt() {
    }

    public Wkt(Integer id, String wkt) {
        this.id = id;
        this.wkt = wkt;
    }
}
