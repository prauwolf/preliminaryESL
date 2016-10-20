
package Contract

import akka.actor.{ Actor, ActorRef, FSM }
import Contract.Messages._
import Contract.FSMData.FSMData
import ContractHandler.Messages.ObligationResponse
import ContractHandler.Messages.RequestObligation
import ContractHandler.Messages.ScheduleEvent
import BalanceSheet.BalanceSheet
import ContractHandler.ContractHandler
import sim.engine.SimState
import sim.engine.Schedule

/**
 * Valid states of the Contract
 */
case object Coupon extends Active
case object Principal extends Active
case object ReturnPrincipal extends Active
case object Matured extends Contract.Terminated

/**
 * Data
 */
case class paymentData(from: BalanceSheet, to: BalanceSheet, amount: Double, deadline: Double) extends FSMData


/**
 * Fixed bond contract:
 * @param principal: The amount of money party 2 gives party 1 for the bond
 * @param coupon: coupon amount
 * @param numPayments: number of coupon payments
 * @param durationBetweenPayments: time between coupon payments
 * @param party1 distributer of the bond.
 * @param party2 bond purchaser
 * @param contractHandler who deals with the balancesheet manipulations for the two parties. 
 */
class FixedBond (principal: Double, coupon: Double, numPayments: Int, durationBetweenPayments: Double,
    party1: BalanceSheet, party2: BalanceSheet, contractHandler: ContractHandler) 
    extends Contract {
  
 /* private var paymentsLeft: Int = numPayments
  
  // The contract begins by party2 purchasing a bond from party1
  startWith(Principal, paymentData(party2, party1, 30.seconds.fromNow, principal))
 
  when (Principal) {
    case Event(ScheduleEvent(s : SimState), _) => 
      //s.schedule.scheduleOn
      stay
    
    //If the initial principal payment is processed, move on to couponPayments
    case Event(ObligationResponse(_,success), _) if success == true => 
      goto (Coupon) using paymentData(party1, party2, coupon, durationBetweenPayments)
  }
  
  when (Coupon) {

    //If a coupon payment is made, and there are additional coupons to pay, move to the next coupon.
    case Event(ObligationResponse(_,success), paymentData(from, to, duration, _)) if success == true & paymentsLeft > 1 => 
      paymentsLeft = paymentsLeft - 1
      goto (Coupon) using paymentData(from, to, coupon, durationBetweenPayments)

    //If no more coupons need to be paid, the Bond has matured.
    case Event(ObligationResponse(_,success), paymentData(from,to,_,_)) if success == true & paymentsLeft == 1 => 
      goto (ReturnPrincipal) using paymentData(to, from,principal, 0) 

  }
  
  when (ReturnPrincipal) {

    //Once the maturation payment has been made, stop.
    case Event(ObligationResponse(_,success), paymentData(_,_,_,_)) if success == true =>
      goto (Matured)
  }
  
  whenUnhandled {
  
    // If the contract handler requests payment information, then send back the 
    //processPayment message.
    case Event(ContractHandler.Messages.RequestObligation, paymentData(from, to, timeleft, amount)) =>
      val obligation: CashObligation = new CashObligation(from, to, timeleft, amount)
      sender ! NextObligation(obligation)
      stay
      
    //If the contractHandler notifies the contract of payment failure, then proceed to default.
    case Event(ObligationResponse(_,success), _) if success == false =>
      goto(Contract.Default) using stateData

  }
  
  onTransition {
      
    case _ -> Coupon =>
    case _ -> Principal => 
    case _ -> ReturnPrincipal =>
      nextStateData match {
        case data: paymentData => {
          //Set counters when contract goes to a new state
          //One timer reminds the contractHandler when half the time has passed
          //the other notifies the contract that the payment is in default. 
          setTimer("Payment due date has passed", FillObligation, data.deadline.timeLeft, false)
          setTimer("Payment almost due", FillObligation, data.deadline.timeLeft / 2, false) 
        }
      }
      
    //Notify the contractHandler that the contract has defaulted then stop
    case _ -> Default =>
      contractHandler ! Contract.Messages.Terminated(Default)
      stop    
      
    //Notify the contractHandler that the contract has matured, then stop
    case _ -> Matured =>
      contractHandler ! Contract.Messages.Terminated(Matured)
      stop
  } */
}