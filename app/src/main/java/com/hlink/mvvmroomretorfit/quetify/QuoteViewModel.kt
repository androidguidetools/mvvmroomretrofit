package com.hlink.mvvmroomretorfit.quetify


import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.hlink.mvvmroomretorfit.quetify.model.Quote

class QuoteViewModel(val context: Context) : ViewModel() {

    private var quoteList: Array<Quote> = emptyArray()
    private var index = 0


    init {
        quoteList = loadQuoteListFromAssets()
    }

    private fun loadQuoteListFromAssets(): Array<Quote> {
        val inputStream = context.assets.open("quote.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json, Array<Quote>::class.java)
    }

    fun getQuote() = quoteList[index]

    fun nextQuote(): Quote {
        if (quoteList.lastIndex == index) {
            index = -1
        }
        return quoteList[++index]
    }

    fun previousQuote(): Quote {
        if (index == 0) {
            index = quoteList.size
        }
        return quoteList[--index]
    }


}