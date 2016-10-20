package Item

/**
 * A Good extends Item and represents a physical item worth 
 * some value
 */
trait Good extends Item {
 
  //The type of Good
  val goodType:GoodType
  
  //The quantity of the good possessed
  var quantity:Double
}