package actors

import java.io.{FileNotFoundException, File, PrintWriter}

import akka.actor.Actor
import akka.event.Logging

import scala.collection.immutable.ListMap

/**
 * Created by Amit on 22/11/2015.
 */
case class SaveCountToFile(filePath : String , wordCount : Map[String,Int])

class WordCountSaverActor extends Actor {
  val log = Logging(context.system, this)

  def receive: Receive = {
    case SaveCountToFile(filePath: String, wordCount: Map[String,Int]) => {
      log.info("creating file")
      val file = new File(filePath);
      val path = file.getAbsoluteFile().getParent();
      val sortedWordCount=ListMap(wordCount.toSeq.sortWith(_._2 > _._2):_*)
      try {
        log.info("saving to file ")
        val writer = new PrintWriter(new File(path + File.separator + "fileWithNumberOfWords.txt"))
        sortedWordCount foreach ( (t2) =>  writer.println (t2._1 + " " + t2._2 + "\n")  )
        writer.close()
        log.info("write to file ok")
      }
      catch {
        case ex: FileNotFoundException => {
          log.error("failed to read file- file dosent exist")

        }
      }
    }
  }
}
