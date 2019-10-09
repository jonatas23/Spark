package com.spark.service;

import com.spark.model.Wkt;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class GeoSparkService {

    @Autowired
    private SparkSession sparkSession;

    private String wkt = "POLYGON((6.494261241177355 24.883513067003886,6.494261241177355 19.017530402666445,8.076292491177355 17.515270994543638,13.173948741177355 22.14372287794682,15.283323741177355 17.179700235003814,17.744261241177355 21.980813073403887,16.513792491177355 25.51970646098458,6.494261241177355 24.883513067003886))";

    public List<Wkt> listWkt(){
        List<Wkt> listwkt = new ArrayList<>();

        String wkt = "POLYGON((6.670042491177355 29.1877194283093,10.009886241177355 15.577606506682802,19.677854991177355 20.25917388793208,13.173948741177355 22.38773421531062,16.865354991177355 27.797359227553287,6.670042491177355 29.1877194283093))";
        String wkt2 = "POLYGON((-2.1190200088226447 31.163052028730487,-4.755738758822645 21.4091619993525,4.912229991177355 19.92901168939879,8.427854991177355 22.38773421531062,5.966917491177355 25.598995998666492,6.494261241177355 28.572043918088397,4.912229991177355 32.50704405055752,1.7481674911773553 33.098045728358706,-2.1190200088226447 31.163052028730487))";
        String wkt3 = "POLYGON((-7.919801258822645 24.963218625405904,-2.4705825088226447 24.803756045234334,-1.9432387588226447 19.266623182667097,-2.2948012588226447 12.336853638008703,-5.810426258822645 7.315685344356024,-9.853395008822645 12.68007089722432,-9.853395008822645 18.768063810389485,-10.029176258822645 24.803756045234334,-7.919801258822645 24.963218625405904))";
        String wkt4 = "POLYGON((4.033323741177355 50.033903710757066,10.713011241177355 49.920860624064105,7.900511241177355 48.775789063000026,4.033323741177355 47.366411678704736,4.033323741177355 50.033903710757066))";
        String wkt5 = "POLYGON((4.318968272427355 26.290525759319628,19.436155772427355 26.290525759319628,19.436155772427355 15.937103671435992,4.318968272427355 15.937103671435992,4.318968272427355 26.290525759319628))";

        listwkt.add(new Wkt(1, wkt));
        listwkt.add(new Wkt(2, wkt2));
        listwkt.add(new Wkt(3, wkt3));
        listwkt.add(new Wkt(4, wkt4));
        listwkt.add(new Wkt(5, wkt5));

        return listwkt;
    }

    public List<Wkt> stIntersects(){
        Dataset<Row> rowWkt = sparkSession.createDataFrame(listWkt(), Wkt.class);
        Dataset<Row> wkts = rowWkt.filter("ST_Intersects(st_geomFromWKT(wkt), st_geomFromWKT('" + this.wkt + "'))");

        wkts.show();
        return wkts.as(Encoders.bean(Wkt.class)).collectAsList();
    }

    public List<Wkt> stContains(){
        Dataset<Row> rowWkt = sparkSession.createDataFrame(listWkt(), Wkt.class);
        Dataset<Row> wkts = rowWkt.filter("ST_Contains(st_geomFromWKT(wkt), st_geomFromWKT('" + this.wkt + "'))");

        wkts.show();
        return wkts.as(Encoders.bean(Wkt.class)).collectAsList();
    }
}
