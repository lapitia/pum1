import kotlin.random.*
import java.time.LocalDate
import java.time.Month

enum class CostType(val costType: String) {
    REFUELING("Tankowanie"),
    SERVICE("Serwis"),
    PARKING("Parking"),
    INSURANCE("Ubezpieczenie"),
    TICKET("Mandat")
}

data class Cost (
    val type: CostType,
    val date: LocalDate,
    val amount: Int
)

object DataProvider {
    val generalCosts = List(5) {
        Cost(
            CostType
                .values()[Random.nextInt(CostType.values().size)],
            LocalDate.of(
                2025, 
                Random.nextInt(1,13), 
                Random.nextInt(1,28)),
            Random.nextInt(5000)
        )
    }
}

//zad1
fun groupedCostMap(costs: List<Cost>):Map<Month,List<Cost>>{
    return costs
		.groupBy{it.date.month}
        .toSortedMap(compareBy {it.ordinal})
}

//zad2
fun printAllCosts(costs: List<Cost>){
    costs
    	.sortedBy{it.date}
        .groupBy{it.date.month}
        .toSortedMap(compareBy {it.ordinal})
        
        .forEach{(month, monthlyCosts)->
        	println(month)
            monthlyCosts.forEach{ cost->
                val day = cost.date.dayOfMonth.toString().padStart(2,'0')
                println("$day ${cost.type} ${cost.amount} zł")
            }
        }
}

//zad3
sealed class MonthlyCostStatus{
    object NoCosts:  MonthlyCostStatus()
    data class WithinLimit(val total: Int): MonthlyCostStatus()
    data class OverLimit(val total: Int, val exceededBy: Int): MonthlyCostStatus()
}


fun classifyMonthlyCosts(costs: List<Cost>, month: Month, limit: Int): MonthlyCostStatus{
    val total = costs
    	.filter{it.date.month==month}
    	.sumOf{it.amount}
    return when{
        total == 0 -> MonthlyCostStatus.NoCosts
        total<= limit -> MonthlyCostStatus.WithinLimit(total)
        else -> MonthlyCostStatus.OverLimit(total,total-limit)
    }
}

//zad4
interface CostFormatter {
    fun format(cost: Cost): String
}

object PlCostFormatter: CostFormatter{
override fun format(cost: Cost): String {
        val day = cost.date.dayOfMonth.toString().padStart(2, '0')
        return "$day ${cost.type} ${cost.amount} zł"
    }
}

fun formatCosts(costs: List<Cost>, formatter: CostFormatter): String =
    costs
        .sortedBy { it.date }
        .map { formatter.format(it) }
        .joinToString("\n")

fun main(){
    println("--zad1--\n")
    val grouped = groupedCostMap(DataProvider.generalCosts)
    println("$grouped \n")
    println("--zad2--\n")
    printAllCosts(DataProvider.generalCosts)
    println("\n--zad3--\n")
    println(classifyMonthlyCosts(DataProvider.generalCosts, Month.MARCH, 1000))
    println("\n--zad4--\n")
    println(formatCosts(DataProvider.generalCosts, PlCostFormatter))
}
