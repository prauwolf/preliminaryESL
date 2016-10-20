package test.balancesheet

import org.scalatest.FunSpec
import org.scalatest.BeforeAndAfter
import Item._
import BalanceSheet.BalanceSheet
import BalanceSheet.Exceptions.AssetQuantityException

class BalanceSheetTest extends FunSpec with BeforeAndAfter {
  
  var spice = new GoodType("spice")
  var sugar = new GoodType("sugar")
  var balance = new BalanceSheet
  
  describe("A BalanceSheet") {
  
    it("begins empty") {
       assert(balance.getAll().isEmpty)
    }
    it("and attempting to remove anything fails.") {
      intercept[AssetQuantityException]{
        balance.expend(new PrivateGood(sugar, 10.0))
      }
    }
    it("It should accept goods like sugar and spice such that they both appear on the balancesheet") {
      balance.receive(new PrivateGood(sugar, 20.0))
      balance.receive(new PrivateGood(spice, 10.0))
      assert(balance.getAll().size == 2)
    }
    it("You can remove some sugar") {
      balance.expend(new PrivateGood(sugar, 15.0))
    }
    it("But not too much") {
      intercept[AssetQuantityException]{
        balance.expend(new PrivateGood(sugar, 10.0))
      }
    }
    it("You can always add spice") {
      balance.receive(new PrivateGood(spice, 50.2))
    }
    
    it("leaving you with a bit of sugar and a lot of spice") {
      for (g <- balance.goods()) {
        g.goodType.getName() match {
          case "sugar" => assert(g.quantity == 5.0)
          case "spice" => assert(g.quantity == 60.2)
        }
      }
    }
  }
}