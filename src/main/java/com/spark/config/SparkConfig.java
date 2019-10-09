package com.spark.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.serializer.KryoSerializer;
import org.apache.spark.sql.SparkSession;
import org.datasyslab.geospark.serde.GeoSparkKryoRegistrator;
import org.datasyslab.geosparksql.utils.GeoSparkSQLRegistrator;
import org.datasyslab.geosparkviz.sql.utils.GeoSparkVizRegistrator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SparkConfig {

	@Value("${spark.app.name}")
	private String appName;

	@Value("${spark.master}")
	private String masterUri;

	@Bean
	public SparkSession sparkSession() {
		SparkSession sparkSession = SparkSession.builder()
				.appName(appName)
				.master(masterUri)
				.config("spark.ui.port","4041")
				.getOrCreate();

		GeoSparkSQLRegistrator.registerAll(sparkSession);
		GeoSparkVizRegistrator.registerAll(sparkSession);
		return sparkSession;
	}

	@Bean
	public JavaSparkContext javaSparkContext() {
		return new JavaSparkContext(sparkSession().sparkContext());
	}

}
