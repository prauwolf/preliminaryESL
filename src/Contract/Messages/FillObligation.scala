package Contract.Messages

import akka.actor.ActorRef
import Item.Item
import Contract.Obligation.Obligation
import Contract.Obligation.Obligation

case class FillObligation(obligation: Obligation) extends ContractMessages