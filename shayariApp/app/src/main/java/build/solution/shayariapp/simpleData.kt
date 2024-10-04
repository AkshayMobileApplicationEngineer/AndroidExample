package build.solution.shayariapp

import android.icu.util.Calendar
import android.icu.util.GregorianCalendar
import build.solution.shayariapp.MOdel.DataEntity
import java.util.Date

object SimpleData {
    private const val data1 = "This is the data one"
    private const val data2 = "This is the second data"

    // Function to get a date by adding milliseconds to the current date
    fun getDate(diff: Int): Date {
        val calendar = GregorianCalendar()
        calendar.add(Calendar.MILLISECOND, diff)
        return calendar.time
    }

    // Property to get a list of DataEntity objects
    val date: List<DataEntity>
        get() {
            val tempList: MutableList<DataEntity> = ArrayList()
            tempList.add(DataEntity(1, getDate(45), data1))
            tempList.add(DataEntity(2, getDate(45), data2))
            return tempList
        }
}

// Example usage (place this in your main activity or a function)
fun printDataEntities() {
    val dataEntities: List<DataEntity> = SimpleData.date
    dataEntities.forEach {
        println("ID: ${it.id}, Date: ${it.date}, Shayari: ${it.shayari}")
    }
}

// Call the function to print the data (make sure to call this in an appropriate lifecycle method, like onCreate)
