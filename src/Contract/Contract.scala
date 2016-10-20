package Contract

import akka.actor.{ Actor, FSM }
import Item.Item
import Item.Tradable
import Contract.FSMData.FSMData

trait Contract extends Item with Actor with FSM[State, FSMData] with Tradable 