package Contract

sealed trait State

trait Active extends State
trait Terminated extends State
case object Default extends Terminated


