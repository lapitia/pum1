fun main() {
    val number=12
    var sum=0
    fun isPerfect(num:Int){
        for (n in 1..num){
            if (num%n==0 && num!=n){
                sum+=n
            }
        }
        if (sum==num){println("doskonała")}
        if (sum<num){println("deficytowa (niedomiarowa)")}
        if (sum>num){println("nadmiarowa (obfita)")}
	}
    isPerfect(number)
}
