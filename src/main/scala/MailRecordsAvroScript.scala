import java.nio.file.{Path, Paths}

/**
  *  Cc & Bcc example:  /data/src/enron_emails/enron_with_categories/1/70706.txt
  *  Forwarded example: /data/src/enron_emails/enron_with_categories/1/9083.txt
  */


object MailRecordsAvroScript extends App {

  if (args.length == 0) {
    throw new IllegalArgumentException("Input directory or file is required")
  }

  val inPath: Path = Paths.get(args(0))

  if (inPath.toFile.isFile) {
    val rec: MailRecord = MailParser.recordFromFile(inPath.toString)
    println(rec.toString)
  } else if (inPath.toFile.isDirectory) {
    val mailRecordsAvroStream = MailRecordsAvroStream(inPath)
    mailRecordsAvroStream.directoryWalker()
  }

}
