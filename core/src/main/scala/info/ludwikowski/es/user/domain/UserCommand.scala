package info.ludwikowski.es.user.domain

import java.time.LocalDateTime

import info.ludwikowski.es.base.application.Clock
import info.ludwikowski.es.user.domain.UserEvent.{EmailUpdated, NameUpdated, UserCreated}

sealed trait UserCommand {
  def userId: UserId
  //useful for debugging
  def operationId: OperationId
}

object UserCommand {
  final case class CreateUser(userId: UserId, operationId: OperationId, name: String, email: Email) extends UserCommand {
    def toUserCreated(): UserCreated = {
      UserCreated(userId, operationId, Clock.nowUtc(), name, email)
    }
  }
  final case class UpdateName(userId: UserId, operationId: OperationId, newName: String) extends UserCommand {
    def toNameUpdated(): NameUpdated = {
      NameUpdated(userId, operationId, Clock.nowUtc(), newName)
    }
  }
  final case class UpdateEmail(userId: UserId, operationId: OperationId, newEmail: Email) extends UserCommand {
    def toEmailUpdated(): EmailUpdated = {
      EmailUpdated(userId, operationId, Clock.nowUtc(), newEmail)
    }
  }
}
