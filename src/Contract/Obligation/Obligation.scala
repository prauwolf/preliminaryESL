package Contract.Obligation

import BalanceSheet.BalanceSheet

trait Obligation {
  def from: BalanceSheet
  def to: BalanceSheet
}