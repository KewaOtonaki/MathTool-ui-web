package mathtool

import scala.util.parsing.combinator._

case class WordFreq(word: String, count: Int) {
  override def toString = s"Word <$word> occurs with frequency $count"
}

class SimpleParser extends RegexParsers {
  def word: Parser[String]   = """[a-z]+""".r           ^^ { _.toString }
  def number: Parser[Int]    = """(0|[1-9]\d*)""".r     ^^ { _.toInt }
  def double: Parser[Double] = """(0|[1-9]\d*).\d+""".r ^^ { _.toDouble }
  def freq: Parser[WordFreq] = word ~ number            ^^ { case wd ~ fr => WordFreq(wd,fr) }
}

object TestSimpleParser extends SimpleParser {
  def run : String = {
    parse(freq, "johnny 121") match {
      case Success(matched,_) => matched.toString()
      case Failure(msg,_) => s"FAILURE: $msg"
      case Error(msg,_) => s"ERROR: $msg"
    }
  }
}
