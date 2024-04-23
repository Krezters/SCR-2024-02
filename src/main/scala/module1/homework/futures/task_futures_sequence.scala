package module1.homework.futures

import module1.homework.futures.HomeworksUtils.TaskSyntax

import scala.concurrent.{ExecutionContext, Future, Promise}
import scala.util.{Failure, Success}

object task_futures_sequence {

  /**
   * В данном задании Вам предлагается реализовать функцию fullSequence,
   * похожую на Future.sequence, но в отличии от нее,
   * возвращающую все успешные и не успешные результаты.
   * Возвращаемое тип функции - кортеж из двух списков,
   * в левом хранятся результаты успешных выполнений,
   * в правово результаты неуспешных выполнений.
   * Не допускается использование методов объекта Await и мутабельных переменных var
   */
  /**
   * @param futures список асинхронных задач
   * @return асинхронную задачу с кортежом из двух списков
   */
  def fullSequence[A](futures: List[Future[A]])
                     (implicit ex: ExecutionContext): Future[(List[A], List[Throwable])] = {
    val promise = Promise[(List[A], List[Throwable])]

    val results = futures.map(_.recover {case e => e})
    Future.sequence(results).map(value => {
      val result = value.partition {
        case _: Throwable => false
        case _ => true
      }.asInstanceOf[(List[A], List[Throwable])]
      promise.success(result)
    })

    promise.future
  }
}
