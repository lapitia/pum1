val <T> List<T>.tail: List<T>
    	get()=this.drop(1) // lista bez 1
    val <T> List<T>.head: T
    	get()=this.first() // tylko 1 element

fun main() {
    val lista=listOf(1,2,3)
    println("Lista: $lista")
    println("Bez pierwszego elementu: ${lista.tail}")
    println("Tylko pierwszy element: ${lista.head}")
}
