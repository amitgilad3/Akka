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
      val count =
        (for {
          line <- mapOfWords
        } yield {
            val words = line.split("\\s+")
            words.size
          }).sum
      countSaverActorRef ! SaveCountToFile (count)
      log.info("finished counting words")
    }

   log.info("finished counting words")

  }

}
