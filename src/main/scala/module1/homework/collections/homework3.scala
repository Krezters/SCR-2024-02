package module1.homework.collections
import scala.util.Random

class Balls {
  private val balls: List[Int] = List(0, 0, 0, 1, 1, 1)
  def isWhite(): List[Boolean] = {
    Random.shuffle(balls).take(2).map(_ == 1)
  }
}

object Balls {
  def whiteBallProbability(pullsCount: Int = 10000): Double = {
    val balls = List.fill(pullsCount)(new Balls).flatMap(_.isWhite())
    val whitesCount = balls.count(_ == true)
    whitesCount / balls.size.toDouble
  }
}
