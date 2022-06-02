package com.kicsit.myfirstapp

import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.webkit.URLUtil
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val next_btn = findViewById<Button>(R.id.next_btn)
        next_btn.setOnClickListener{
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        val dataSendBtn = findViewById<Button>(R.id.data_send_btn)
        dataSendBtn.setOnClickListener {
            val dataBundle = Bundle()
            dataBundle.putString("data_send_text", findViewById<EditText>(R.id.data_send_text).text.toString())

            val dataIntent = Intent(this, SecondActivity::class.java)
            dataIntent.putExtras(dataBundle)

            startActivity(dataIntent)
        }

        // performing webpage/Web Search open action
        val openBrowser = findViewById<View>(R.id.open_browser) as Button
        val webQueryUrlText = findViewById<View>(R.id.web_queryurl_text) as EditText

        openBrowser.setOnClickListener {
            val webQueryUrl = webQueryUrlText.text.toString()

            var browserIntent = if(URLUtil.isValidUrl(webQueryUrl)) {
                Intent(Intent.ACTION_VIEW, Uri.parse(webQueryUrl))

            } else{
                Intent(Intent.ACTION_WEB_SEARCH )
                    .putExtra(SearchManager.QUERY, webQueryUrl)
            }
            startActivity(browserIntent)
        }


        // opening phone dialer
        val openDialer = findViewById<View>(R.id.open_dialer)
        val dialerNumber = findViewById<View>(R.id.dialer_number) as EditText
        openDialer.setOnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse(dialerNumber.text.toString())
            startActivity(dialIntent)
        }

        val openContact = findViewById<Button>(R.id.open_contacts)
        openContact.setOnClickListener {
            val contactsIntent = Intent(Intent.ACTION_VIEW)
            contactsIntent.data = ContactsContract.Contacts.CONTENT_URI
            startActivity(contactsIntent)
        }


        val emailSendBtn = findViewById<Button>(R.id.email_send_btn)
        emailSendBtn.setOnClickListener{
            val emailIntent = Intent(Intent.ACTION_SEND)
            emailIntent.setType("plain/text")
            emailIntent.putExtra(Intent.EXTRA_EMAIL,
                arrayOf(findViewById<EditText>(R.id.email_to).text.toString()))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT,
                findViewById<EditText>(R.id.email_subject).text.toString())
            emailIntent.putExtra(Intent.EXTRA_TEXT,
                findViewById<EditText>(R.id.email_text).text.toString())

            startActivity(emailIntent)
        }
    }
}