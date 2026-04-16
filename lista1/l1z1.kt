fun main() {
    fun foo(num:Int){
        for (n in 1..num) {
            if (n%5==0 && n%3==0){println("trzypiec")}
            else if (n%3==0){ println("trzy")}
            else if (n%5==0){println("piec")}
            else {println(n)}
    	}
	}
    foo(15)
}
