package com.electionapp.android.utils.extensions

import java.util.*


fun <E> List<E>.random(): E? = if (size > 0) get(Random().nextInt(size)) else null
