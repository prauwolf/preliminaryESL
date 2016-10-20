package test.item

import org.scalatest.FunSpec
import org.scalatest.BeforeAndAfter
import Item._

class PrivateGoodTest extends FunSpec{
  
  val  sugar = new GoodType("sugar")
  val  mySugar = new PrivateGood(sugar, 20.0)
  
  describe("This Private Good") {
    it("consists of sugar") {
      assert(mySugar.goodType.getName() == "sugar")
    }
    it("with a quantity of 20 units") {
      assert(mySugar.quantity == 20)
    }
  }
  
}