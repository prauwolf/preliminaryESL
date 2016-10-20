package Agent

import BalanceSheet.BalanceSheet

class Agent {
  val balance: BalanceSheet = new BalanceSheet()
  
  def getBalanceSheet() : BalanceSheet = {
    return balance
  }
}