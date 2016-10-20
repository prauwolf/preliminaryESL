package Item

/**
 * A PrivateGood is both a Good and Tradable
 */
class PrivateGood(val goodType:GoodType, var quantity:Double) extends Good with Tradable{
  
}