package com.tonyecoleelection.domain.base

import android.arch.lifecycle.MutableLiveData
import java.util.HashMap

/**
 * Created by aliumujib on 12/05/2018.
 */

class Params private constructor() {

    private val parameters = HashMap<String, Any>()

    fun <T> putInLiveData(key: String, value: T) {
        var mutableLiveData: MutableLiveData<*> = if (parameters[key] != null && parameters[key] is MutableLiveData<*>) {
            parameters[key] as MutableLiveData<*>
        } else {
            MutableLiveData<T>()
        }
        mutableLiveData.value = value
        parameters.put(key, mutableLiveData)
    }

    fun <T> getInLiveData(key: String,
                          defaultValue: MutableLiveData<T>): MutableLiveData<T> {
//        var mutableLiveData: MutableLiveData<*> = if (parameters[key] != null && parameters[key] is MutableLiveData<*>) {
//            parameters[key] as MutableLiveData<*>
//        } else {
//            MutableLiveData<T>()
//        }
//        mutableLiveData.value = value
//        parameters.put(key, mutableLiveData)


        val `object` = parameters[key] ?: return defaultValue
        return try {
            `object` as MutableLiveData<T>
        } catch (e: ClassCastException) {
            defaultValue
        }
    }


    fun putInt(key: String,
               value: Int) {
        parameters.put(key, value)
    }

    fun getParameters(): HashMap<String, Any> {
        return parameters
    }

    fun getInt(key: String?,
               defaultValue: Int): Int {
        val `object` = parameters[key] ?: return defaultValue
        return try {
            `object` as Int
        } catch (e: ClassCastException) {
            defaultValue
        }
    }

    fun putString(key: String,
                  value: String) {
        parameters.put(key, value)
    }


    fun putList(key: String,
                value: List<String>) {
        parameters.put(key, value)
    }

    fun notEmptyString(key: String): Boolean {
        return parameters[key] != null && (parameters[key] as String).isNotEmpty()
    }

    fun has(key: String): Boolean {
        return parameters[key] != null
    }

    fun getString(key: String,
                  defaultValue: String?): String? {
        val `object` = parameters[key] ?: return defaultValue
        return try {
            `object` as String
        } catch (e: ClassCastException) {
            defaultValue
        }

    }

    fun putLong(key: String,
                value: Long) {
        parameters.put(key, value)
    }

    fun putFloat(key: String,
                 value: Float) {
        parameters.put(key, value)
    }

    fun putData(key: String,
                value: Any) {
        parameters.put(key, value)
    }

    fun getFloat(key: String,
                 defaultValue: Long): Float {
        val `object` = parameters[key] ?: return defaultValue.toFloat()
        return try {
            `object` as Float
        } catch (e: ClassCastException) {
            e.printStackTrace()
            defaultValue.toFloat()
        }

    }

    fun <T> getList(key: String,
                    defaultValue: List<T>?): List<T>? {
        val `object` = parameters[key] ?: return defaultValue
        return try {
            `object` as List<T>
        } catch (e: ClassCastException) {
            e.printStackTrace()
            defaultValue
        }
    }

    fun getData(key: String,
                defaultValue: Any?): Any? {
        val `object` = parameters[key] ?: return defaultValue
        return try {
            `object`
        } catch (e: ClassCastException) {
            e.printStackTrace()
            defaultValue
        }
    }

    fun getDouble(key: String,
                  defaultValue: Long): Double {
        val `object` = parameters[key] ?: return defaultValue.toDouble()
        return try {
            `object` as Double
        } catch (e: ClassCastException) {
            e.printStackTrace()
            defaultValue.toDouble()
        }
    }
    //TICKET_FILTER_WITH_TIME_RANGE_FLAG


    fun getBoolean(key: String,
                   defaultValue: Boolean): Boolean {
        val `object` = parameters[key] ?: return defaultValue
        return try {
            `object` as Boolean
        } catch (e: ClassCastException) {
            e.printStackTrace()
            defaultValue
        }
    }

    fun putBoolean(key: String,
                   value: Boolean) {
        parameters.put(key, value)
    }

    fun getLong(key: String,
                defaultValue: Long): Long {
        val `object` = parameters[key] ?: return defaultValue
        return try {
            `object` as Long
        } catch (e: ClassCastException) {
            e.printStackTrace()
            defaultValue
        }

    }

    fun clear() {
        parameters.clear()
    }

    fun remove(key: String) {
        parameters.remove(key)
    }

    companion object {
        val EMPTY = create()

        fun create(): Params {
            return Params()
        }
    }
}