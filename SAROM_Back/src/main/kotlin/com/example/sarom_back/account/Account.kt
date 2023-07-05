/*
data class UserDto(
    var id: Long? = null,
    var name: String? = null,
    var pwd: String? = null
) {
    companion object {
        fun toUserDto(userEntity: UserEntity): UserDto {
            return UserDto(
                id = userEntity.id,
                name = userEntity.name,
                pwd = userEntity.pwd
            )
        }
    }
}

class UserEntity(
    var id: Long? = null,
    var name: String? = null,
    var pwd: String? = null
)

class UserRepository {
    private val users: MutableList<UserEntity> = mutableListOf()
    private var idCounter: Long = 1L

    private fun generateId(): Long {
        return System.currentTimeMillis()
    }

    fun save(userEntity: UserEntity): UserEntity {
        userEntity.id = generateId()
        users.add(userEntity)
        return userEntity
    }

    fun findByName(name: String): UserEntity? {
        return users.find { it.name == name }
    }
}

class UserService(private val userRepository: UserRepository) {
    fun save(userDto: UserDto) {
        val userEntity = UserEntity().apply {
            this.name = userDto.name
            this.pwd = userDto.pwd
        }
        userRepository.save(userEntity)
    }

    fun login(userDto: UserDto): UserDto? {
        val userEntity: UserEntity? = userDto.name?.let { userRepository.findByName(it) }
        if (userEntity != null && userEntity.pwd == userDto.pwd) {
            return UserDto(userEntity.id, userEntity.name, userEntity.pwd)
        }
        return null
    }

    fun userExists(name: String): Boolean {
        return userRepository.findByName(name) != null
    }
}
*/
