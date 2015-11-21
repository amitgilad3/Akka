package actors

import akka.actor.Actor.Receive
import akka.actor.{Actor, ActorRef}
import akka.event.Logging

/**
 * Created by Amit on 22/11/2015.
 */
case class SaveCountToFile(wordCount : Int )
class WordCountSaverActor extends Actor{
  val log = Logging(context.system, this)
  def receive: Receive = {
    case SaveCountToFile (wordCount : Int) => {

    }
  }
}
