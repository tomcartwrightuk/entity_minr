package entityextractor

import ner._
import org.scalatra._
import scalate.ScalateSupport
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._

class EntityMinr extends EntityMinrStack with JacksonJsonSupport {
  protected implicit val jsonFormats: Formats = DefaultFormats

  before() {
    contentType = formats("json")
  }

  post("/entities"){
    val toProcess = parse(request.body).extract[ToProcess].contents
    EntityWrangler.extract(toProcess);
  }
  
}

case class ToProcess(contents: String)
