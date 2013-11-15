package entityextractor

import ner._
import org.scalatra._
import scalate.ScalateSupport
import org.json4s.{DefaultFormats, Formats}

// JSON handling support from Scalatra
import org.scalatra.json._

class EntityMinr extends EntityMinrStack with JacksonJsonSupport {
  protected implicit val jsonFormats: Formats = DefaultFormats

  before() {
    contentType = formats("json")
  }

  get("/entities"){
    EntityWrangler.extract(params("contents"));
  }
  
}
