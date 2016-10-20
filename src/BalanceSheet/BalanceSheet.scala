
package BalanceSheet

import Item._
import BalanceSheet.Exceptions._

/**
 * The purpose of the BalanceSheet class is to maintain an up-to-date 
 * and queryable collection of Items for a particular Actor. Items contained 
 * within a BalanceSheet can potentially represent either physical Goods 
 * or Contracts. 
 */
class BalanceSheet {
  
  /**
   * Collection of all the Items on the balance sheet
   * These are a collection of assets and liabilities
   */
  val items = scala.collection.mutable.Set[Item]()
    
  /**
   * Adds an Item to the BalanceSheet. This method is 
   * protected to maintain stock-flow consistency.  
   * @param item the Item to add
   * @return returns a boolean regarding whether the add was successful.
   */
  protected def add(item: Item): Boolean = {
   items.add(item)
  }
  
  /** 
   *  Removes an Item from the balance sheet. 
   *  @param item the Item to remove.
   *  @return Optional Item. If the Item is contained within
   *  the BalanceSheet then this option will contain the Item
   *  otherwise it will be empty.
   */
  protected def remove(item: Item): Option[Item] = {
	  if (items.contains(item)) {
	    items.remove(item)
	    return Some(item)
	  } else {
	    return None
	  }
	}
	
	/**
	 * Removes some "amount" of cash from the Balancesheet.
	 * If the amount cannot be removed, then a LiquidityException is thrown
	 * @param amount the amount of money to remove from the BalanceSheet
	 */
  @throws(classOf[LiquidityException])
	protected def dishoard(amount: Double): Unit = {}
  //@TODO implement dishoard
	
	/**
	 * Adds some "amount" of cash to the balancesheet
	 * @param amount the amount of money added to the BalanceSheet
	 */
  protected def hoard(amount: Double): Unit = {}
	//@TODO implement hoard
  
  // Method used to decrement the commitment on a particular financial liability.
  //protected def repay(liability: Contract, commitment: Commitment): Unit
  //@TODO implement repay
  
  // Method used to increment the commitment on a particular financial liability.
  //protected def borrow(liability: Contract, commitment: Commitment): Unit
  //@TODO implement borrow
  
  
  /**
   * Transfer some quantity of a Good off the balance sheet.
   * @param good The type of good and quantity to remove
   */
  @throws(classOf[AssetQuantityException])
  @throws(classOf[IllegalArgumentException])
  def expend(good: Good): Unit = {
      
    //Quantity must be greater than zero
    if (good.quantity <= 0.0) {
      throw new IllegalArgumentException("Cannot remove a quantity less than or equal to zero")
    }
    
    //Finds all the goods with 'good' as the GoodType
    val filtered = this.goods().filter { g:Good => g.goodType.getUUID() == good.goodType.getUUID()}
    
    //If the requested good does not appear on the balance sheet, throw an error.
    if (filtered.isEmpty) {
      throw new AssetQuantityException("A good of type: " + good.goodType.getName() + 
          " does not exist on the balance sheet")
      
    //A balancesheet should only contain one instance of a Good with a particular GoodType 
    } else if (filtered.size > 1) {
      throw new IllegalStateException("Two different GoodTypes with the same UUID were found." + 
          " This is a serious exception, as the integrity of BalanceSheet may be comprised.")  
    }
    
    if (filtered.head.quantity < good.quantity) {
      throw new AssetQuantityException("This BalanceSheet only contains " + filtered.head.quantity +
          " of Good: " + good.goodType.getName() + ". Thus, it is impossible to expend " + good.quantity +
          " of the good.")
    }
 
    filtered.head.quantity = filtered.head.quantity - good.quantity
  }
  
  /**
   * Method used to increment the amount of a particular Good. 
   * @param good the type of good and quantity to add.
   */
  @throws(classOf[IllegalArgumentException])
  def receive(good: Good): Unit = {
    
    //Quantity must be greater than zero
    if (good.quantity <= 0.0) {
      throw new IllegalArgumentException("Cannot add a quantity less than or equal to zero")
    }
 
    //Finds all the goods with the same GoodType as good
    val filtered = this.goods().filter { g:Good => g.goodType.getUUID() == good.goodType.getUUID() }
 
    //If the requested good does not appear on the balance sheet, add it.
    if (filtered.isEmpty) {
      this.add(good)
 
    //A balancesheet should only contain one instance of a Good with a particular GoodType 
    } else if (filtered.size > 1) {
      throw new IllegalStateException("Two different GoodTypes with the same UUID were found." + 
          " This is a serious exception, as the integrity of BalanceSheet may be comprised.")  
    //add to the quantity of the existing good
    } else {
      filtered.head.quantity = filtered.head.quantity + good.quantity
    }
  }
  
  
  /**
   * Searches for items on the balancesheet which meet some criteria.
   * @param p The search criteria. p is a function which operates on a tradable, 
   * returning a boolean if the tradable meet the criteria.
   * @return returns an iterable collection of tradables which meet the 
   * filter criteria.
   */
  def filter(p: Item => Boolean): Iterable[Item] = {
	  items.filter(p)
	}

  
  /**
   * Returns all the Items in the BalanceSheet
   * @return an iterable collection of items
   */
  def getAll(): Iterable[Item] = {
    filter((i:Item) => true)
  }
  
  /**
   * Pulls all the assets from a balancesheet
   * @return Iterable list of Items comprised of all the 
   * assets on the balancesheet. 
   */
  def assets(): Iterable[Item] = {return null}
    //@TODO implement assets 
  
  /**
   * Pulls all the liabilities from a balancesheet
   * @return an Iterable list of items comprised of the
   * liabilities on the balancesheet
   */
  def liabilities(): Iterable[Item] = {return null}
    //@TODO implement liabilities
    //tradables.filter(some function to search for liabilities)

  /**
   * Returns a collection of all the Goods from the 
   * items in the Balance Sheet
   * @return a collection of Goods
   */
  def goods(): Iterable[Good] = {
    items.collect {case g: Good => g}
  }
  
  /**
   * Evaluates the balancesheet based on some function f. 
   * @param f the evaluation function. It converts each Item into a Double
   * @return the total value of the BalanceSheet
   */
  def value(f: Item => Double): Double = {
    items.map(f).sum
  }
  
  /**
   * Evaluates some subset of the BalanceSheet based on some value function f.
   * @param f the evaluation function. It converts each Item into a Double
   * @param p function which defines the subset of the BalanceSheet's items
   * @return the total value, according to f, of the subset of the 
   * BalanceSheet, defined by p. 
   */
	def value(f: Item => Double, p: Item => Boolean): Double = {
    items.filter(p).map(f).sum
  }
	
	/**
	 * Returns true if the balancesheet is solvent based on 
	 * some valuation function
	 * @param The function used to value each Item in the balance sheet
	 * @return true if the balance sheet is solvent.
	 */
	def isSolvent(f: Item => Double): Boolean = {
    equity(f) >= 0.0
  }

	/**
	 * Returns false if the balancesheet is solvent based on 
	 * some valuation function
	 * @param The function used to value each Item in the balance sheet
	 * @return false if the balance sheet is solvent.
	 */
  def isNotSolvent(f: Item => Double): Boolean = {
    equity(f) < 0.0
  }

  /**
	 * Returns value of assets less value of liabilities
	 * @param The function used to value each Item in the balance sheet
	 * @return assets - liabilities, given f.
	 */
  def equity(f: Item => Double): Double = { -1.0}
  //@TODO implement equity
  
}