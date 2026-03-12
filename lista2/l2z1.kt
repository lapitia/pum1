data class UserInput(val name: String?, val email: String?, val age: String?)
data class UserProfile(
    var name: String = "",
    var email: String = "",
    var age: Int = 0,
    var isAdult: Boolean = false
)

fun buildProfile(input: UserInput?, logs: MutableList<String>): UserProfile? {
    if (input == null) {
        logs.add("Input is null")
        return null
    }

    if (input.name == null) {
        logs.add("Name is null")
        return null
    }
    val name = input.name.trim()
    if (name.length < 3) {
        logs.add("Name too short")
        return null
    }

    if (input.email == null) {
        logs.add("Email is null")
        return null
    }
    val email = input.email.trim().lowercase()
    if (!email.contains("@")) {
        logs.add("Invalid email")
        return null
    }

    if (input.age == null) {
        logs.add("Age is null")
        return null
    }
    val age = input.age.toIntOrNull()
    if (age == null) {
        logs.add("Age is not a number")
        return null
    }

    val profile = UserProfile()
    profile.name = name
    profile.email = email
    profile.age = age
    profile.isAdult = age >= 18

    logs.add("Profile created for $email")
    return profile
}

fun main() {
}
