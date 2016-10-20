package ContractHandler.Messages

import Contract.Obligation.Obligation

case class ObligationResponse(obligation: Obligation, hasFilled: Boolean) extends ContractHandlerMessages

