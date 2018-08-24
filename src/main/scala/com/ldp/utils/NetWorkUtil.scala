package cn.bestsign.flink.utils

/**
  * @description
  * @author Depu Lai
  * @company BestSign
  * @date 2018/8/23
  * @version
  * @since
  */
object NetWorkUtil {

  import java.net.InetAddress

  /** *
    * Convert a IPv4 address from an integer to an InetAddress.
    *
    * @param hostAddress an int corresponding to the IPv4 address in network byte order
    */
  def intToInetAddress(hostAddress: Int): InetAddress = {
    val addressBytes = Array((0xff & hostAddress).toByte, (0xff & (hostAddress >> 8)).toByte, (0xff & (hostAddress >> 16)).toByte, (0xff & (hostAddress >> 24)).toByte)
    try
      InetAddress.getByAddress(addressBytes)
    catch {
      case _ =>
        throw new AssertionError
    }
  }

  /** *
    * Convert a IPv4 address from an InetAddress to an integer
    *
    * @param inetAddr is an InetAddress corresponding to the IPv4 address
    * @return the IP address as an integer in network byte order
    */
  @throws[IllegalArgumentException]
  def inetAddressToInt(inetAddr: InetAddress): Int = {
    val addr = inetAddr.getAddress
    if (addr.length != 4) throw new IllegalArgumentException("Not an IPv4 address")
    ((addr(3) & 0xff) << 24) | ((addr(2) & 0xff) << 16) | ((addr(1) & 0xff) << 8) | (addr(0) & 0xff)
  }

  def getLocalHostAddress(): Option[InetAddress] = {
    var address: InetAddress = null
    try {
      address = InetAddress.getLocalHost
      println(address)
    } catch {
      case _ =>
        println("Could not find this computer's address.")
    }
    if (address != null) Option(address) else Option.empty
  }


}
