package com.snowplowanalytics.hadoop.scalding

import org.apache.hadoop
import com.twitter.scalding.Tool

/**
 * Ejecutor del trabajo MapReduce
 */
object JobRunner {
  def main(args : Array[String]) {
    hadoop.util.ToolRunner.run(new hadoop.conf.Configuration, new Tool, args);
  }
}