data class Point(val x: Int, val y: Int) {
// p1 + p2  - dodajemy składowe
    operator fun plus(other: Point) = Point(x + other.x, y + other.y)
// p1 + 1  =>  dodajemy skalar do obu składowych
    operator fun plus(scalar: Int) = Point(x + scalar, y + scalar)
// p1 - p2
    operator fun minus(other: Point) = Point(x - other.x, y - other.y)
// p1 * p2
    operator fun times(other: Point) = Point(x * other.x, y * other.y)
// p1++: inc() zwraca nowy obiekt z wartościami +1
    operator fun inc() = Point(x + 1, y + 1)
// p1--:  dec() zwraca nowy obiekt z wartościami -1
    operator fun dec() = Point(x - 1, y - 1)
// !p1; negacja
    operator fun not() = Point(-x, -y)
}

fun main(){
var p1 = Point(1, 1)
val p2 = Point(2, 2)
println(p1 + p2)   // Point(x=3, y=3)
p1 += 1
println(p1) // Point(x=2, y=2)
println(p1 - p2) // Point(x=0, y=0)
println(p1 * p2) // Point(x=4, y=4)
p1++
println(p1) // Point(x=3, y=3)
p1--
println(p1) // Point(x=2, y=2)
println(!p1) // Point(x=-2, y=-2)
}