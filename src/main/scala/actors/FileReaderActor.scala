package actors

import java.io.{FileNotFoundException, IOException}

import akka.actor.{Actor, ActorRef}
import akka.event.Logging

import scala.io.Source

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
        log.info(s"Starting to read file: $fileLocation")
        val source = Source.fromFile(fileLocation).getLines
        wordCounterActorRef ! CountWords(fileLocation,source)

      }
      catch {
        case ex: FileNotFoundException =>{
          log.error("failed to read file- file dosent exist" )
          println("Couldn't find that file.")
        }
        case ex: IOException => println("Had an IOException trying to read that file")
      }

    }
    case _ => println("that was unexpected")

  }

}
