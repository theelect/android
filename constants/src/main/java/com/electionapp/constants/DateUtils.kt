package com.electionapp.constants

import java.util.*


class DateUtils {

    companion object {
        fun getStartOfToday(): Long {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DATE)
            calendar.set(year, month, day, 1, 0, 0)
            return getStartOfDay(Calendar.getInstance()).time.time
        }

        fun getCurrentSystemTime(): Long {
            return Calendar.getInstance().time.time
        }


        fun getFirstDayOfWeek(): Calendar {
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.getInstance().firstDayOfWeek)
            return calendar
        }

        fun getStartOfDay(calendar: Calendar): Calendar {
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DATE)
            calendar.set(year, month, day, 1, 0, 0)
            return calendar
        }

        fun getEndOfDay(calendar: Calendar): Calendar {
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DATE)
            calendar.set(year, month, day, 23, 59, 59)
            return calendar
        }
    }
}