package com.snowplowanalytics.hadoop.scalding

import com.twitter.scalding._

class BigramJob(args : Args) extends Job(args) {
  TextLine( args("input") )
    .flatMap('line -> 'bigram) { line : String => pairs(tokenize(line)).map(bigram => bigram._1+" "+bigram._2) }
    .groupBy('bigram) { _.size }
    .write( Tsv( args("output") ) )

  def tokenize(text : String) : List[String] = {
    text.toLowerCase.replaceAll("[^a-zA-Z0-9\\s]", "").split("\\s+").toList
  }
    
  def pairs(array: List[String]) : List[(String,String)] = 
    array match {
    	case Nil => Nil
    	case _::Nil => Nil
    	case x::(y::arrayp) => (x,y)::pairs(y::arrayp)
  	}
}