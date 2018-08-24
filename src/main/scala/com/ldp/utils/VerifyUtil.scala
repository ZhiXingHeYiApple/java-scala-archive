package cn.bestsign.flink.utils

import org.joda.time.DateTimeZone
import org.joda.time.format.{DateTimeFormat, DateTimeFormatterBuilder, DateTimeParser}

import scala.util.{Success, Try}

/**
  * @description
  * @author Depu Lai
  * @company BestSign
  * @date 2018/8/23
  * @version
  * @since
  */
object VerifyUtil {
  val formatter = {
    val parsers: Array[DateTimeParser] = Array(DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ").getParser, // timestamp和datetime类型
      DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").getParser, // date类型时间
      DateTimeFormat.forPattern("yyyy-MM-dd").getParser, // time类型时间
      DateTimeFormat.forPattern("HH:mm:ss").getParser)
    new DateTimeFormatterBuilder()
      .append(null, parsers)
      .toFormatter
      .withZone(DateTimeZone.getDefault) // DateTimeZone.forOffsetHours(8)
  }


  def verify(str: String, dtype: String): Boolean = {
    val c: Try[Any] = dtype match {
      case "int" | "INT" => scala.util.Try(str.toInt)
      case "long" | "LONG" => scala.util.Try(str.toLong)
      case "float" | "Float" => scala.util.Try(str.toFloat)
      case "double" | "DOUBLE" => scala.util.Try(str.toDouble)
      case "date" | "DATE" => scala.util.Try(formatter.parseDateTime(str))
    }
    c match {
      case Success(_) => true;
      case _ => false;
    }
  }
}
