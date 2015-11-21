package actors

import akka.actor.Actor.Receive
import akka.actor.{Actor, ActorRef}
import akka.event.Logging

/**
 * Created by Amit on 22/11/2015.
 */

case class CountWords(mapOfWords : Iterator[String])
class WordCounterActor (actor : ActorRef) extends  Actor{
  val log = Logging(context.system, this)
  var countSaverActorRef: ActorRef = actor
  def receive: Receive = {
    case CountWords(mapOfWords : Iterator[String]) => {
     val count = mapOfWords.flatMap(_.split("\\W+")).foldLeft(Map.empty[String, Int]){
      (count, word) => count + (word -> (count.getOrElse(word, 0) + 1))
    }
  }
  }
}
