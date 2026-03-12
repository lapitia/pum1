fun <A> isSorted(lst: List<A>, order: (A, A) -> Boolean): Boolean{
    if (lst.size<2) return true
    for (i in 0 until lst.size-1){
        if (!order(lst[i],lst[i+1])) return false
    }
    return true
}

fun main() {
	println(isSorted(listOf(1,2,3)){i: Int, j: Int -> i<j})
    println(isSorted(listOf(2,2,2)){i: Int, j: Int -> i==j})
    println(isSorted(listOf(1,2,1)){i: Int, j: Int -> i>j})
}
