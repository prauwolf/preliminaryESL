package ContractHandler

import Contract.Obligation.Obligation
import Contract.Contract
import ContractHandler.Messages.ObligationResponse
import Contract.Obligation.GoodObligation

class SmartContractHandler() extends ContractHandler() {
  
  override def handleObligation(o: Obligation): ObligationResponse = {
     o match {
       case GoodObligation(_,_,_) =>
         //TODO manipulate balancesheets to remove item
         return new ObligationResponse(o, true)
       case _ =>
         return new ObligationResponse(o, false)
     }
  }
}