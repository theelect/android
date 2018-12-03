package com.tonyecoleelection.constants

import java.text.SimpleDateFormat

/**
 * Created by Abdul-Mujeeb Aliu on 5/12/2018.
 *
 */


interface Constants {


    object STATS_CONSTANTS {
        const val TYPE = "type"


        const val GENDER = "gender"
        const val OCCUPATION = "occupation"
        const val LGA = "lga"
        const val WARD = "ward"

    }


    object DATE_FORMATTERS {
        val TIME_FORMAT_12_HR = SimpleDateFormat("hh:mm aa")
        val DATE_FORMAT_FULL_WITH_TIME = SimpleDateFormat("MMMM dd, yyyy hh:mm aa")
        val DATE_FORMAT_FULL_WITH_DAY = SimpleDateFormat("MMMM EEEE dd, yyyy hh:mm aa")
        val DATE_DAY = SimpleDateFormat("EEE")
        val DATE_DAY_WITH_NUM = SimpleDateFormat("EEE, dd")

        val DATE_FORMAT_FULL = SimpleDateFormat("MMMM dd, yyyy")
        val DATE_FORMATTER_SERVER_TIME_STAMP = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    }

    /**
     * firstname  [user-email-address]
     * lastname  [user-last-name]
     * phone  [user-phone-number]
     * email  [user-email-address]
     * password  [user-password]
     * password_confirmation  [confirm-password]
     */


    object PVC_VERIFICATION_CONSTANTS {
        val IS_VERIFICATION_ONLINE = "IS_VERIFICATION_ONLINE"
        val LAST_NAME = "last_name"
    }

    interface AUTH_CONSTANTS {
        companion object {
            val FIRST_NAME = "first_name"
            val LAST_NAME = "last_name"
            val PHONE = "phone"
            val EMAIL = "email"
            val PASSWORD = "password"
            val WARD = "ward"
            val LGA = "lga"
            val VIN = "vin"


        }
    }

    object FILTER_CONSTANTS {
        const val LGA = "lga"
        const val WARD = "ward"
        const val PAGE_COUNT = 10
        const val CURRENT_PAGE = "page"
        const val LAST_CHECKED_POSITION = "position"

        const val OCCUPATION = "profession"
    }

}

