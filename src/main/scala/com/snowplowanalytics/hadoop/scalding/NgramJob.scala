package com.snowplowanalytics.hadoop.scalding

import com.twitter.scalding.Job
import com.twitter.scalding.Args
import com.twitter.scalding.TextLine
import com.twitter.scalding.Tsv

class NgramJob(args : Args) extends Job(args) {
  
  implicit val n = args("n").toInt
  
  TextLine( args("input") )
  	.flatMap('line -> 'body) { line : String => line }
    .map('body -> 'ngram) { body : String => ngrams(tokenize(body)).map{ _.fold("")(_+" "+_) } }
  	.groupBy('ngram) { _.size }
    .write( Tsv( args("output") ) )

  def tokenize(text : String) : List[String] = {
    text.toLowerCase.replaceAll("[^a-zA-Z0-9\\s]", "").split("\\s+").toList.filter( _.length > 0 )
  }
    
  type NgramList = List[List[String]]
    
  def ngrams(list: List[String])(implicit n: Int) : NgramList = {
	    def ngramsHelperFunction(list: List[String], acc: NgramList) : NgramList =
		    if(list.size<n) acc
		    else {
		      val (l,r) = list.splitAt(n)
		      ngramsHelperFunction(r, l :: acc)
		    }
	    ngramsHelperFunction(list, Nil)
    }
    
}