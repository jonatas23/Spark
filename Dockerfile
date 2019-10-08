FROM ubuntu:18.04

LABEL image=SparkBaseImage
ENV SPARK_VERSION=2.4.4
ENV HADOOP_VERSION=2.7

RUN apt-get update -qq && \
    apt-get install -qq -y gnupg2 wget openjdk-8-jdk scala

WORKDIR /

# Spark
RUN wget --no-verbose http://www.gtlib.gatech.edu/pub/apache/spark/spark-${SPARK_VERSION}/spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION}.tgz

RUN tar -xzf /spark-${SPARK_VERSION}-bin-hadoop2.7.tgz && \
    mv spark-${SPARK_VERSION}-bin-hadoop2.7 spark

WORKDIR spark

EXPOSE 4040
