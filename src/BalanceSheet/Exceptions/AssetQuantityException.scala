package BalanceSheet.Exceptions

/**
 * Exception is thrown when a BalanceSheet does not possess enough
 * of an Asset to fulfill some contractual need.
 * @param msg Error message.
 */
class AssetQuantityException(msg: String) extends Exception(msg) {
  
} 