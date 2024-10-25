package com.daromi.chirp.core.users.domain

class User
private constructor(
    private val id: UserId,
    private var name: UserName,
    private var handle: UserHandle,
) {
  companion object {
    fun create(
        rawId: String,
        rawName: String,
        rawHandle: String,
    ): User? {
      val id     = UserId.from(rawId)         ?: return null
      val name   = UserName.from(rawName)     ?: return null
      val handle = UserHandle.from(rawHandle) ?: return null

      return User(id, name, handle)
    }
  }

  fun changeName(rawName: String): Boolean {
    this.name = UserName.from(rawName) ?: return false

    return true
  }

  fun changeHandle(rawHandle: String): Boolean {
    this.handle = UserHandle.from(rawHandle) ?: return false

    return true
  }

  override fun toString(): String {
    return "User(${this.id.value}, ${this.name.value}, ${this.handle.value})"
  }
}

@JvmInline
value class UserId(val value: String) {

  companion object {
    fun from(value: String): UserId? {
      if (value.isBlank()) return null

      return UserId(value)
    }
  }
}

@JvmInline
private value class UserName(val value: String) {

  companion object {
    fun from(value: String): UserName? {
      if (value.isBlank()) return null

      return UserName(value)
    }
  }
}

@JvmInline
private value class UserHandle(val value: String) {

  companion object {
    private const val PREFIX: String = "@"

    fun from(value: String): UserHandle? {
      if (value.isBlank() || !value.startsWith(PREFIX)) return null

      return UserHandle(value)
    }
  }
}
