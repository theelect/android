package com.electionapp.android.utils.extensions

import android.view.LayoutInflater
import android.view.View

internal val View.inflater: LayoutInflater get() = LayoutInflater.from(context)
