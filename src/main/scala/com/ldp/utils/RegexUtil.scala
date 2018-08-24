package com.ldp.utils

import scala.util.matching.Regex

/**
  * @description
  * @author Depu Lai
  * @company BestSign
  * @date 2018/8/24
  * @version
  * @since
  */
object RegexUtil {
  class RichRegex(underlying: Regex) {
    def matches(s: String) = underlying.pattern.matcher(s).matches
  }
  implicit def regexToRichRegex(r: Regex) = new RichRegex(r)
}
