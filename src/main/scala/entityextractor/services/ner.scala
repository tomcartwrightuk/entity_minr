package ner;

import edu.stanford.nlp.ie.crf.CRFClassifier
import edu.stanford.nlp.ling.CoreAnnotations

object EntityWrangler {

  val classifier = CRFClassifier.getClassifierNoExceptions("stanford/english.all.3class.distsim.crf.ser.gz")

  def extract(contents: String): List[NamedEntity] = {
   
    val classified = classifier.classify(contents)
    var entities = List[NamedEntity]()

    for (i <- 0 to classified.size() - 1) {
      var sentenceEntities = extractFromSentence(classified.get(i))
      entities = entities ++ sentenceEntities
    }
    return entities
  }

  def extractFromSentence(sentence: java.util.List[edu.stanford.nlp.ling.CoreLabel]): List[NamedEntity] = {

    var sentenceEntities = List[NamedEntity]()
    var prevWordClass = ""
    var entity = NamedEntity("", "")
 
    for (j <- 0 to sentence.size() - 1) {
      val word = sentence.get(j)
      val wordClass = word.get(classOf[CoreAnnotations.AnswerAnnotation]).toString()
 
      if (!prevWordClass.equals(wordClass)) {
        if (!prevWordClass.equals("O") && !prevWordClass.equals("")) {
          sentenceEntities = sentenceEntities :+ entity
          entity = NamedEntity("", "")
        }
      }

      if (!wordClass.equals("O") && !wordClass.equals("")) {
        if (!prevWordClass.equals(wordClass)) {
          entity = entity.copy(entity_type = wordClass, entity_text = word.toString())
        }
        if (wordClass.equals(prevWordClass)) {
          entity = entity.copy(entity_type = wordClass, entity_text = entity.entity_text + " " + word)
        }
      }
      prevWordClass = wordClass
 
    }
    return sentenceEntities
  }

}

case class NamedEntity(entity_type: String, entity_text: String)
