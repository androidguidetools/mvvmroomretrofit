package com.hlink.mvvmroomretorfit.quetify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.hlink.mvvmroomretorfit.R
import com.hlink.mvvmroomretorfit.quetify.model.Quote

class QuetifyHomeScreen : AppCompatActivity() {

    private val tvQuoteText: TextView
        get() = findViewById(R.id.tvQuoteText)

    private val tvQuoteAuthor: TextView
        get() = findViewById(R.id.tvQuoteAuthor)

    lateinit var quoteViewModel: QuoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quetify_home_screen)

        quoteViewModel = ViewModelProvider(
            this, QuoteViewModelFactory(application)
        ).get(QuoteViewModel::class.java)

        setQuote(quoteViewModel.getQuote())
    }

    private fun setQuote(quote: Quote) {
        tvQuoteText.setText(quote.text)
        tvQuoteAuthor.setText(quote.author)
    }


    fun onShare(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT, quoteViewModel.getQuote().text)
        startActivity(intent)
    }

    fun onPrevious(view: View) {
        setQuote(quoteViewModel.previousQuote())
    }

    fun onNext(view: View) {
        setQuote(quoteViewModel.nextQuote())
    }
}