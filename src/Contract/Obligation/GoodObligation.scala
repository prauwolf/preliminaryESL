package Contract.Obligation

import BalanceSheet.BalanceSheet
import Item.GoodType

case class GoodObligation(from: BalanceSheet, to: BalanceSheet, what: GoodType) extends Obligation