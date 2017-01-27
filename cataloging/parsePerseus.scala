import scala.io.Source
import scala.xml._
import scala.collection.mutable.ArrayBuffer
import java.io._

case class CitableWork(urn: String, textGroup: String, title: String) {
  def toTsv: String = {
    urn + "\t" + textGroup + "\t" + title
  }
}

var citableWorks = ArrayBuffer[CitableWork]()

val groups = XML.loadFile("perseus_works_catalog.xml") \\ "textgroup"
for (g <- groups) {
  val groupNames = g \\ "groupname"
  val groupName = groupNames(0).text
  val works = g \\ "work"
  for (w <- works) {
    val urnList = w \\ "@urn"
    val urnString = urnList(0) + ":"
    val titleList = w \\ "title"
    val title = titleList(0).text
    val citableWork = CitableWork(urnString, groupName, title)

    citableWorks += citableWork
  }
}

val tabStrings = citableWorks.map(_.toTsv).toVector

val pw = new PrintWriter(new File("perseus_works.tsv" ))
pw.write(tabStrings.mkString("\n"))
pw.close
