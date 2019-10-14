package com.spark.service;

import com.spark.model.Name;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class WordCountService {

	@Autowired
	public JavaSparkContext sc;

	@Autowired
	public SparkSession sparkSession;

	public Object mapReduce() {
		try {
			FileReader fr = new FileReader( "/biblia-em-txt.txt" );
			BufferedReader br = new BufferedReader( fr );
			List<String> wordList = new ArrayList<>();

			while( br.ready() ) {
				List<String> linha = Arrays.asList(br.readLine().split(" "));
				linha.forEach(l -> wordList.add(l.replace(".", "").replace(":", "").replace(";", "").replace(",", "")));
			}

			JavaRDD<String> words = sc.parallelize(wordList);

			Map<String, Long> wordCounts = words.countByValue();
			return wordCounts;

		} catch (IOException e) {
			System.out.println(e.getMessage());
			return 0l;
		}
	}

	public Long countName(String name) {
		try {
			FileReader fr = new FileReader( "/biblia-em-txt.txt" );
			BufferedReader br = new BufferedReader( fr );
			List<Name> wordList = new ArrayList<>();

			while( br.ready() ) {
				List<String> linha = Arrays.asList(br.readLine().split(" "));
				linha.forEach(l -> wordList.add(new Name(l.replace(".", "").replace(":", "").replace(";", "").replace(",", "").replace("!", "").replace("?", ""))));
			}

            Dataset<Row> mapBiblia = sparkSession.createDataFrame(wordList, Name.class);

            return mapBiblia.filter("name = '" + name + "'").count();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return 0l;
		}
	}

    public Object contains(String name) {
        try {
            FileReader fr = new FileReader( "/biblia-em-txt.txt" );
            BufferedReader br = new BufferedReader( fr );
            List<String> wordList = new ArrayList<>();

            while( br.ready() ) {
                List<String> linha = Arrays.asList(br.readLine().split(" "));
                linha.forEach(l -> wordList.add(l.replace(".", "").replace(":", "").replace(";", "").replace(",", "").replace("!", "").replace("?", "")));
            }

			JavaRDD<String> words = sc.parallelize(wordList);
			Map<String, Long> wordCounts = words.countByValue();

			return wordCounts.entrySet().stream().filter(w ->  w.getKey().toLowerCase().contains(name.toLowerCase()));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return 0l;
        }
    }

//	public Long countName(String name) {
//		try {
//			FileReader fr = new FileReader( "/biblia-em-txt.txt" );
//			BufferedReader br = new BufferedReader( fr );
//			List<String> wordList = new ArrayList<>();
//
//			while( br.ready() ) {
//				String linha = br.readLine();
//				wordList.add(linha);
//			}
//
//			JavaRDD<String> words = sc.parallelize(wordList);
//			JavaRDD<String> filter = words.filter(line -> {
//				if (line.contains(name))
//					return true;
//				else
//					return false;
//			});
//			Long count = filter.count();
//			return count;
//
//		} catch (IOException e) {
//			System.out.println(e.getMessage());
//			return 0l;
//		}
//	}

}
