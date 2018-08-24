package cn.bestsign.flink.utils

/**
  * @description
  * @author Depu Lai
  * @company BestSign
  * @date 2018/8/23
  * @version
  * @since
  */
object IPUtil {
  def isIPWithPort(hostport: String): Boolean = {
    if (hostport == null || hostport == "") return false
    val arr = hostport.split(":")
    if (arr.length == 2) {
      isIP(arr(0)) && VerifyUtil.verify(arr(1), "INT")
    } else {
      false
    }
  }

  def isIP(ip: String): Boolean = {
    import com.ldp.utils.RegexUtil._
    val IP =
      """(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)""".r
    IP.matches(ip)
    //    ip match {
    //      case IP() => true
    //      case _ => false
    //    }
  }


  def getIpWithPort(str: String): (String, Option[Int]) = {
    if (isIPWithPort(str)) {
      val arr = str.split(":")
      (arr(0), Option(arr(1).toInt))
    } else if (isIP(str)) {
      (str, Option.empty)
    } else {
      throw new IllegalArgumentException("the string format is not IP:PORT")
    }
  }

  /**
    * PING remote host
    *
    * @return
    */
  def ping(ip: String, time: Long): Boolean = {
    var isReach = false
    try {
      val cmd = "ping -c 1 " + " -w " + time + " " + ip
      val p = Runtime.getRuntime.exec(cmd)
      val status = p.waitFor
      if (status == 0) isReach = true
      println(s">>>>>>>>>cmd:$cmd  result:$status")
    } catch {
      case e: Exception =>
        e.printStackTrace()
    }
    isReach
  }
}
