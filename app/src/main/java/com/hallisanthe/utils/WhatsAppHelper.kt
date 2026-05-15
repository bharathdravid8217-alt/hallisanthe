package com.hallisanthe.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

object WhatsAppHelper {
    fun open(context: Context, phone: String, productName: String) {
        val message = Uri.encode("Namaste, I am interested in $productName from Halli-Santhe.")
        val cleanPhone = phone.filter { it.isDigit() }.let { if (it.startsWith("91")) it else "91$it" }
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/$cleanPhone?text=$message")))
    }
}
