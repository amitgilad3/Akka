package actors

import java.io.{FileNotFoundException, File, PrintWriter}

import akka.actor.Actor
import akka.event.Logging

/**
 * Created by Amit on 22/11/2015.
 */
case class SaveCountToFile(filePath : String , wordCount : Int )

class WordCountSaverActor extends Actor{
  val log = Logging(context.system, this)
  def receive: Receive = {
    case SaveCountToFile (filePath : String,wordCount : Int) => {
      log.info("saving")
      val file = new File(filePath);
      val path = file.getAbsoluteFile().getParent();
      try {
        val writer = new PrintWriter(new File(path + File.separator + "fileWithNumberOfWords.txt"))
        writer.write(wordCount.toString)
        writer.close()
      }
      catch{
        case ex: FileNotFoundException =>{
          log.error("failed to read file- file dosent exist" )
        }
      }

    }
  }
}
