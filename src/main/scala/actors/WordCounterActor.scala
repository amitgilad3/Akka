package actors

import akka.actor.{Actor, ActorRef}
import akka.event.Logging

/**
 * Created by Amit on 22/11/2015.
 */

case class CountWords(filePath : String,mapOfWords : Iterator[String])
class WordCounterActor (actor : ActorRef) extends  Actor{
  val log = Logging(context.system, this)
  var countSaverActorRef: ActorRef = actor
  val Word = "\\b([A-Za-z\\-])+\\b".r
  def receive: Receive = {
    case CountWords(filePath : String,mapOfWords : Iterator[String]) => {
     val counter = mapOfWords.flatMap(_.split("\\W+"))
       .foldLeft(Map.empty[String, Int]){
       (count, word) => count + (word -> (count.getOrElse(word, 0) + 1))}
      countSaverActorRef ! SaveCountToFile(filePath,counter.size)


    }

   log.info("finished counting words")

  }

}
