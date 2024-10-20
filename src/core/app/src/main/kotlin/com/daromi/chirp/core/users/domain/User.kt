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
      val id = UserId.from(rawId) ?: return null
      val name = UserName.from(rawName) ?: return null
      val handle = UserHandle.from(rawHandle) ?: return null

      return User(id, name, handle)
    }
  }

  fun rename(rawName: String): Boolean {
    this.name = UserName.from(rawName) ?: return false

    return true
  }

  fun updateHandle(rawHandle: String): Boolean {
    this.handle = UserHandle.from(rawHandle) ?: return false

    return true
  }

  override fun toString(): String = "User(id=${this.id}, name=${this.name}, handle=${this.handle})"
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
