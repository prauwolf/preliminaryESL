package ContractHandler

import akka.actor.{Props, Actor}
import Contract.Contract
import Contract.Obligation.Obligation
import Contract.Messages.FillObligation
import Contract.Messages.Terminated
import ContractHandler.Messages.ObligationResponse

trait ContractHandler {
  
  def handleObligation(obligation:Obligation): ObligationResponse
  
}

/*abstract class ContractHandler() extends Actor {

  def receive: Receive = {
    case FillObligation(o:Obligation) =>
      sender ! handleObligation(o)

    case t:Terminated =>
      handleContractTermination(t)
  }
  def handleObligation(obligation:Obligation): ObligationResponse
  def handleContractTermination(message: Terminated)
}
  */
