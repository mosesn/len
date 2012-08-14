object Len {
  val DefaultLineLength = 100
  val HelpText = """len [-n int] [input]\n
    the -n flag specifies how long a line should be.  If no argument is passed, the default
    of length 100 is used.\n
    input specifies which file to use as input.  If no argument is passed, stdin is used."""

  def main(args: Array[String]) {
    if (args.length > 3) {
      throw new IllegalArgumentException("Too many arguments")
    }

    if (args.length == 1 && args(0) == "-h") {
      println(HelpText)
      scala.sys.exit(0)
    }

    val lines = longLines(getLength(args), getFile(args))
    if (lines.hasNext) {
      println("The following lines have problems.")
      for ((line, number) <- lines) {
        println("L%d: %s, length: %d".format(number, line, line.length))
      }
      scala.sys.exit(1)
    }
    else {
      println("All lines are ok.")
      scala.sys.exit(0)
    }
  }

  private[this] def getLength(args: Array[String]): Int =
    if (args.length > 1) {
      parseInt(args)
    }
    else {
      DefaultLineLength
    }

  private[this] def parseInt(args: Array[String]): Int =
    if (args(0) == "-n") {
      try {
        args(1).toInt
      }
      catch {
        case e: NumberFormatException =>
          throw new IllegalArgumentException("Expected an integer argument")
      }
    }
    else {
      throw new IllegalArgumentException("Bad arguments")
    }

  private[this] def getFile(args: Array[String]): scala.io.BufferedSource = args.length match {
    case x if (x == 3) => io.Source.fromFile(args(2))
    case x if (x == 1) => io.Source.fromFile(args(0))
    case _ => io.Source.stdin
  }

  private[this] def longLines(length: Int,
    fp: scala.io.BufferedSource): Iterator[Tuple2[String, Int]] =
    fp.getLines().zipWithIndex filter (_._1.length > length)
}