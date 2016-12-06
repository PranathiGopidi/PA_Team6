package org.apache.spark.examples.graphx

import org.apache.hadoop.hive.ql.exec.spark.session.SparkSession
import org.apache.spark.graphx.GraphLoader

/**
  * A PageRank example on social network dataset
  * Run with
  * {{{
  * bin/run-example graphx.PageRankExample
  * }}}
  */


object PageRank {
  def main(args: Array[String]): Unit = {
    // Creates a SparkSession.
    val spark = SparkSession
      .builder()
      .appName(s"${this.getClass.getSimpleName}")
      .getOrCreate()
    val sc = spark.sparkContext
    //import spark.implicits._
    // $example on$
    // Load the edges as a graph
    val graph = GraphLoader.edgeListFile(sc, "data/graphx/followers.txt")
    // Run PageRank
    val ranks = graph.pageRank(0.0001).vertices
    // Join the ranks with the usernames
    val users = sc.textFile("data/graphx/users.txt").map { line =>
      val fields = line.split(",")
      (fields(0).toLong, fields(1))
    }
    val ranksByUsername = users.join(ranks).map {
      case (id, (username, rank)) => (username, rank)
    }
    // Print the result
    println(ranksByUsername.collect().mkString("\n"))
    // $example off$
    spark.stop()
  }


}
