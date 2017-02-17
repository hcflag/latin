import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._

val edition = "editions/sallust.catilina.tsv"

val corpus = CorpusSource.fromFile(edition)
val lcChars = corpus.nodes.flatMap(_.text.toLowerCase.toList)
val charmaps = lcChars.groupBy(c => c)
val histo = charmaps.map { m => (m._1,m._2.size)}.toSeq.sortBy(_._2).reverse

println("\n\nSet of " histo.size + " characters is distributed as follows:")
for ((k,v) <- histo) {
  println(k + " " + v)
}
