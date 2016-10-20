package Item

import java.util.UUID

/**
 * GoodType represents a class of Good. For example, a 
 * Widget and a Gadget would be two different instantiations
 * of GoodType.
 * @param name is the name of the GoodType
 */
class GoodType(name:String) {
  
  //Unique identifier for the GoodType
  //This is set at construction and cannot be changed.
  private val uuid:UUID = UUID.randomUUID()
  
  //name of the GoodType, this does not have to 
  //be unique across GoodTypes.
  val this.name:String = name
  
  /**
   * Returns a String representation of the UUID
   * @return the UUID as a String
   */
  def getUUID() : String = {
    uuid.toString()
  }
  
  def getName() : String = {
    name
  }
}