fun main() {
    val number=2
    var sum=0
    fun isFirst(num:Int){
        for (n in 1..num){
            if (num%n==0 && num!=n){
                sum+=n
            }
        }
        if (sum==1){println("liczba pierwsza")}
        else{println("nie")}
	}
    isFirst(number)
}
