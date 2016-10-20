package Contract.Messages

import akka.actor.ActorRef
import Item.Item
import scala.concurrent.duration._
import Contract.Obligation.Obligation

case class NextObligation(obligation: Obligation) extends ContractMessages
