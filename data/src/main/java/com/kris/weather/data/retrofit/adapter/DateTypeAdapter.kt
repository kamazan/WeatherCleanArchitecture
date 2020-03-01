package com.kris.weather.data.retrofit.adapter

import android.text.format.DateUtils
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.util.*

object DateTypeAdapter: TypeAdapter<Date?>() {
    override fun write(out: JsonWriter, value: Date?) {
        value?.run { out.value(time / DateUtils.SECOND_IN_MILLIS) }
    }

    override fun read(i: JsonReader): Date? {
        return if (i.peek() == JsonToken.NUMBER) Date(i.nextLong() * DateUtils.SECOND_IN_MILLIS) else {
            i.nextNull()
            null
        }
    }
}