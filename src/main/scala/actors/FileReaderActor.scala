package actors

import akka.actor.Actor
import akka.event.Logging

import scala.io.Source

/**
 * Created by Amit on 22/11/2015.
 */

case class ReadFile(fileLocation : String)

class FileReaderActor extends Actor{
  val log = Logging(context.system, this)
  def receive: Receive = {
    case ReadFile(fileLocation : String)  => {
      val source = Source.fromFile(fileLocation).getLines().flatMap(_.split("\\W+"))

      log.info("finished reading file")
    }

  }

}
