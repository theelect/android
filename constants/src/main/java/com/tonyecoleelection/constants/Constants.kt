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

    object USERS_CONSTANTS {

        const val USERNAME = "username"
        const val PASSWORD = "password"

        const val LAST_NAME_SEARCHABLE = "searchableLastName"
        const val FIRST_NAME_SEARCHABLE = "searchableFirstName"
        const val FIRST_NAME = "firstName"
        const val LAST_NAME = "lastName"
        const val EMAIL = "email"
        const val DOB = "dob"
        const val EMAIL_VERIFIED = "emailVerified"
        const val COUNTRY = "country"
        const val FULL_NAME = "fullName"
    }


    object REVIEW_CONSTANTS {

        const val LISTING_ID = "listing_id"

    }


    object DATA_SHARING_CONSTANTS {
        const val SERVICE_TYPE = "_metroscan._tcp."

    }

    object BOOKING_CONSTANTS {
        const val DATE_START = "DATE_START"
        const val DATE_END = "DATE_END"

        const val DATE_START_STRING = "DATE_START_STRING"
        const val DATE_END_STRING = "DATE_END_STRING"
    }

    object TICKET_FILTER_CONSTANTS {
        const val TICKET_START_TIME = "TICKET_START_TIME"
        const val TICKET_END_TIME = "TICKET_END_TIME"
        const val TICKET_FILTER_WITH_TIME_RANGE_FLAG = "TICKET_FILTER_WITH_TIME_RANGE_FLAG"
        const val TICKET_OBJ = "TICKET_OBJ"

        const val DATA_TICKET_FETCH_MODE = "DATA_TICKET_FETCH_MODE"
        const val MODE_VIEW_BY_DATE = 0
        const val MODE_VIEW_BY_STATUS = 1 //OPEN OR CLOSED
        const val MODE_VIEW_BY_SCHEDULE = 2 //OPEN OR CLOSED
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

    object UI_COMMANDS {
        const val LAUNCH_SCANNER_COMMAND = "LAUNCH_SCANNER_COMMANDS"
    }

    object SCHEDULE_CONSTANTS {
        const val SCHDID = "SCHDID"
        const val DEPTIME = "DEPTIME"
        const val ARRVTIME = "ARRVTIME"
        const val ROUTENAME = "ROUTENAME"
        const val DEPARTURE = "DEPARTURE"
        const val ARRIVAL = "ARRIVAL"
        const val TRAIN = "TRAIN"
    }


    object STATISTIC_CONSTANTS {
        const val TICKETS_TODAY = "TICKETS_TODAY"
        const val VALID_TICKETS = "VALID_TICKETS"
        const val INVALID_TICKETS = "INVALID_TICKETS"
        const val UNAUTHORIZED_TICKETS = "UNAUTHORIZED_TICKETS"
        const val ALL_REJECTED_TICKETS = "ALL_REJECTED_TICKETS"


        const val VALIDATOR_STATION = "VALIDATOR_STATION"

        const val START_TIME = "START_TIME"
        const val END_TIME = "END_TIME"
        const val SCHEDULE_ID = "SCHEDULE_ID"
        const val TOTAL_COUNT = "TOTAL_COUNT"

        const val OPEN_TICKETS = "OPEN_TICKETS"
        const val CLOSED_TICKETS = "CLOSED_TICKETS"
        const val ADULT_TICKETS = "ADULT_TICKETS"
        const val CHILD_TICKETS = "CHILD_TICKETS"
        const val SPECIAL_TICKETS = "SPECIAL_TICKETS"
        const val TOTAL_TICKETS = "TOTAL_TICKETS"

    }


    object NETWORK_RESPONSES {
        const val ERROR = "Error"
    }

    object LOCATION_ADDRESS_CONSTANTS {
        const val SUCCESS_RESULT = 0

        const val FAILURE_RESULT = 1

        private const val PACKAGE_NAME = "com.hatixa.peep"

        const val RECEIVER = "${PACKAGE_NAME}.RECEIVER"

        const val RESULT_DATA_KEY = "${PACKAGE_NAME}.RESULT_DATA_KEY"

        const val LOCATION_DATA_EXTRA = "${PACKAGE_NAME}.LOCATION_DATA_EXTRA"
    }

    object UI_CONSTANTS {
        val DARKENING_FACTOR = 1.2f
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


            val USERNAME = "password"
            val PREFERENCES = "user_pref"
            val PASSWORD_CONFIRM = "password_confirmation"
            val LOCATION = "address"
            val NETWORK = "network_provider"
            val SEX = "gender"
            val BIRTHDAY = "dob"
            val TOKEN = "idToken"
            val TOKEN2 = "access_token"

            val CURRENT_AUTH_MODE = "CURRENT_AUTH_MODE"
            val AUTH_MODE_FB = 1
            val AUTH_MODE_GOOGLE = 2
        }
    }

    object FILTER_CONSTANTS {
        const val LGA = "lga"
        const val WARD = "ward"
        const val PAGE_COUNT = 10
        const val CURRENT_PAGE = "page"

        const val OCCUPATION = "occupation"
    }

}

