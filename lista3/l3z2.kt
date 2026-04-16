fun addToBoolean(): Map<Int, Boolean> {
    return (1..20).associateWith { it % 2 == 0 }
    // mapa: klucz -> wynik lambdy, czyli klucz kojarzymy z wynikiem
    // it % 2 == 0: true dla parzystych, false dla nieparzystych
}

fun main(){
println(addToBoolean()) // >> {1=false, 2=true, 3=false, 4=true, ...}
}