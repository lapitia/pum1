data class UserInput(val name: String?, val email: String?, val age: String?) //definicja klasy, wszystkie argumenty mogą być nullami
data class UserProfile(
    var name: String = "",
    var email: String = "",
    var age: Int = 0,
    var isAdult: Boolean = false
) //klasa do wyniku końcowego po walidacji danych z wartościami domyślnymi które można zmienić (var)

fun buildProfile(input: UserInput?, logs: MutableList<String>): UserProfile? { //przyjmuje dane wejściowe i listę logów, zwraca zbudowany userprofile lub null
    return input?.run { // wykonane tylko wtedy gdy input nie null
        name?.trim() //usuwa spacje
            ?.takeIf { it.length >= 3 } //zostawiamy wartość tylko przy tym warunku
            ?.let { validName -> //jeśli przeszło, let wykonuje blok i zapisuje poprawne imię
                email?.trim()
                    ?.lowercase()
                    ?.takeIf { it.contains("@") }
                    ?.let { validEmail ->
                        age?.toIntOrNull()
                            ?.let { validAge ->
                                UserProfile().apply { //tworzy pusty obiekt, a apply konfiguruje go
                                    name = validName
                                    email = validEmail
                                    age = validAge
                                    isAdult = validAge >= 18
                                }.also { //dodatkowo
                                    logs.add("Profile created for $validEmail")
                                }
                            } ?: run { //jeśli te warunki wcześniej zwrócili null, wykonujemy ten blok
                            if (age == null) {
                                logs.add("Age is null")
                            } else {
                                logs.add("Age is not a number")
                            }
                            null //profil nie zostaje utworzony
                        }
                    } ?: run {
                    if (email == null) {
                        logs.add("Email is null")
                    } else {
                        logs.add("Invalid email")
                    }
                    null
                }
            } ?: run {
            if (name == null) {
                logs.add("Name is null")
            } else {
                logs.add("Name too short")
            }
            null
        }
    } ?: run {
        logs.add("Input is null")
        null
    }
}

fun main() {
    val logs1 = mutableListOf<String>()
    val input1 = UserInput(" Ala ", " TEST@EXAMPLE.COM ", "20")
    val profile1 = buildProfile(input1, logs1)

    println("Przypadek 1:")
    println(profile1)
    println(logs1)
    println()

    val logs2 = mutableListOf<String>()
    val input2 = UserInput("Al", "abc@test.com", "20")
    val profile2 = buildProfile(input2, logs2)

    println("Przypadek 2:")
    println(profile2)
    println(logs2)
    println()

    val logs3 = mutableListOf<String>()
    val input3 = UserInput("Adam", "zly-email", "20")
    val profile3 = buildProfile(input3, logs3)

    println("Przypadek 3:")
    println(profile3)
    println(logs3)
    println()

    val logs4 = mutableListOf<String>()
    val input4 = UserInput("Adam", "adam@test.com", "abc")
    val profile4 = buildProfile(input4, logs4)

    println("Przypadek 4:")
    println(profile4)
    println(logs4)
    println()

    val logs5 = mutableListOf<String>()
    val profile5 = buildProfile(null, logs5)

    println("Przypadek 5:")
    println(profile5)
    println(logs5)
}