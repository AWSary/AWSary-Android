package com.android.lzcalderaro.dictionary.presentation.util

import android.content.Context
import android.content.Intent
import android.net.Uri

class Email {

    fun send(context: Context) {
        val emailSend = "lzcalderaro@gmail.com"
        val emailSubject = "AWSary Contact"

        val intent = Intent(
            Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", emailSend, null
            )
        )

        intent.putExtra(Intent.EXTRA_SUBJECT, emailSubject)
        intent.putExtra(Intent.EXTRA_TEXT, "")

        context.startActivity(Intent.createChooser(intent, "Send Email... "))
    }
}