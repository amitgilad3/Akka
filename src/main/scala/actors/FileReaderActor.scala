package actors

import _root_.models.HandleResponse
import akka.actor.{ActorRef, Actor}
import akka.event.Logging

import scala.io.Source

/**
 * Created by Amit on 22/11/2015.
 */

case class ReadFile(fileLocation : String)

class FileReaderActor(actor : ActorRef) extends Actor{
  val log = Logging(context.system, this)
  var responseHandlerActor: ActorRef = actor
  def receive: Receive = {
    case ReadFile(fileLocation : String)  => {
      val source = Source.fromFile(fileLocation).getLines().flatMap(_.split("\\W+"))
      //responseHandlerActor ! HandleResponse(response.body)
      log.info("finished reading file")
    }

  }

}
