package com.grymco.akka.main

import actors._
import akka.actor.{ActorSystem, Props}
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory
/**
 * Created by Amit on 21/11/2015.
 */
object Boot extends App {

  //logger
  val logger = Logger(LoggerFactory.getLogger("ReverseBoot"))
  if (!args.isEmpty) {

  logger.info("initializing application")

  // ActorSystem to host our application in
  logger.info("creating actor system for  application")
  implicit val actorSystem = ActorSystem("akka-system")

  logger.info("creating actor  of type WordCountSaverActor")
  val wordCounterSaverActor = actorSystem.actorOf(Props[WordCountSaverActor], "word-counter-saver-actor")


  logger.info("creating actor  of type WordCounterActor")
  val wordCounterActor = actorSystem.actorOf(Props(new WordCounterActor(wordCounterSaverActor)), "word-counter-actor")

  logger.info("creating actor  of type FileReaderActor")
  val reader = actorSystem.actorOf(Props(new FileReaderActor(wordCounterActor)), "file-reading-actor")


    reader ! ReadFile(args(0))


}else{
    logger.error("failed to provide file")
  }
}
