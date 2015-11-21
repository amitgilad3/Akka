package actors

import java.io.FileNotFoundException

import akka.actor.{Actor, ActorRef}
import akka.event.Logging

import scala.io.Source
import scala.sys.process.processInternal.IOException

/**
 * Created by Amit on 22/11/2015.
 */

case class ReadFile(fileLocation : String)

class FileReaderActor(actor : ActorRef) extends Actor{
  val log = Logging(context.system, this)
  var wordCounterActorRef: ActorRef = actor
  def receive: Receive = {
    case ReadFile(fileLocation : String)  => {
      try {
        val source = Source.fromFile(fileLocation).getLines
        wordCounterActorRef ! CountWords(source)
        log.info("finished reading file")
      }
      catch {
        case ex: FileNotFoundException => println("Couldn't find that file.")
        case ex: IOException => println("Had an IOException trying to read that file")
      }

    }

  }

}
