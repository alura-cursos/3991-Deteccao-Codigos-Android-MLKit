package com.alura.confereai.utils
//
//import android.graphics.Point
//import android.graphics.Rect
//import android.util.Log
//import com.alura.confereai.data.CalendarDateTime
//import com.alura.confereai.data.CalendarEventBarcode
//import com.alura.confereai.data.ContactInfoBarcode
//import com.alura.confereai.data.EmailBarcode
//import com.alura.confereai.data.Emblem
//import com.alura.confereai.data.GeoBarcode
//import com.alura.confereai.data.PhoneBarcode
//import com.alura.confereai.data.SmsBarcode
//import com.alura.confereai.data.UrlBarcode
//import com.alura.confereai.data.WifiBarcode
//import com.google.mlkit.vision.barcode.common.Barcode
//import com.google.mlkit.vision.barcode.common.Barcode.TYPE_CALENDAR_EVENT
//import com.google.mlkit.vision.barcode.common.Barcode.TYPE_CONTACT_INFO
//import com.google.mlkit.vision.barcode.common.Barcode.TYPE_EMAIL
//import com.google.mlkit.vision.barcode.common.Barcode.TYPE_GEO
//import com.google.mlkit.vision.barcode.common.Barcode.TYPE_PHONE
//import com.google.mlkit.vision.barcode.common.Barcode.TYPE_SMS
//import com.google.mlkit.vision.barcode.common.Barcode.TYPE_URL
//import com.google.mlkit.vision.barcode.common.Barcode.TYPE_WIFI
//
//
//object HandleBarcode {
//    fun handleBarcode(
//        barcode: Barcode,
//        onResult: (Emblem) -> Unit = {}
//    ) {
//        when (barcode.valueType) {
//            TYPE_URL -> {
//                barcode.url?.let {
//                    val url = it.url.toString()
//                    val title = it.title.toString()
//                    val urlBarcode = UrlBarcode(url, title)
//
//                    if (url == OFFICIAL_SITE) {
//                        urlBarcode.title = "Site oficial"
//                    }
//
//                    onResult(urlBarcode)
//                }
//            }
//
//            TYPE_WIFI -> {
//                barcode.wifi?.let {
//                    val ssid = it.ssid.toString()
//                    val password = it.password.toString()
//                    val encryptionType = it.encryptionType
//                    val wifi = WifiBarcode(ssid, password, encryptionType)
//                    onResult(wifi)
//                }
//            }
//
//            TYPE_SMS -> {
//                barcode.sms?.let {
//                    val message = it.message.toString()
//                    val phoneNumber = it.phoneNumber.toString()
//                    val sms = SmsBarcode(message, phoneNumber)
//                    onResult(sms)
//                }
//            }
//
//            TYPE_PHONE -> {
//                barcode.phone?.let {
//                    val number = it.number.toString()
//                    val type = it.type
//                    val phone = PhoneBarcode(number, type)
//                    onResult(phone)
//                }
//
//            }
//
//            TYPE_GEO -> {
//                barcode.geoPoint?.let { geoPoint: Barcode.GeoPoint ->
//                    val lat = geoPoint.lat
//                    val lng = geoPoint.lng
//
//                    val geo = GeoBarcode(lat, lng)
//                    onResult(geo)
//                }
//            }
//
//            TYPE_CALENDAR_EVENT -> {
//                barcode.calendarEvent?.let {
//                    with(it) {
//                        val description = description.toString()
//                        val location = location.toString()
//                        val organizer = organizer.toString()
//                        val status = status.toString()
//                        val summary = summary.toString()
//                        val start = start?.toCalendarDateTime()
//                        val end = end?.toCalendarDateTime()
//
//                        val calendarEvent = CalendarEventBarcode(
//                            description,
//                            location,
//                            organizer,
//                            status,
//                            summary,
//                            start,
//                            end
//                        )
//                        onResult(calendarEvent)
//                    }
//                }
//            }
//
//            TYPE_CONTACT_INFO -> {
//                barcode.contactInfo?.let {
//                    with(it) {
//                        val addresses: List<String> = addresses.map { address ->
//                            address.addressLines.joinToString(separator = ", ")
//                        }
//                        val emailList: List<EmailBarcode> = emails.map { email ->
//                            EmailBarcode(
//                                email.body.toString(),
//                                email.subject.toString(),
//                                email.address.toString(),
//                                email.type
//                            )
//                        }
//
//                        val name = name?.first.toString()
//                        val organization = organization.toString()
//                        val phones = phones.map { phone ->
//                            phone.number.toString()
//                        }
//                        val title = title.toString()
//                        val urls: List<String> = urls.map { url ->
//                            url.toString()
//                        }
//
//                        val contactInfo = ContactInfoBarcode(
//                            addresses = addresses,
//                            emails = emailList,
//                            name = name,
//                            organization = organization,
//                            phones = phones,
//                            contactTitle = title,
//                            urls = urls
//                        )
//                        onResult(contactInfo)
//                    }
//                }
//            }
//
//            TYPE_EMAIL -> {
//                barcode.email?.let {
//                    with(it) {
//                        val body = body.toString()
//                        val subject = subject.toString()
//                        val address = address.toString()
//                        val type = type
//
//                        val email = EmailBarcode(body, subject, address, type)
//                        onResult(email)
//                    }
//                }
//            }
//
//            else -> {
//                Log.d("barcaCodeGoogle", "typeBarcode: ${barcode.valueType}")
//            }
//        }
//
//        barcode.boundingBox?.let { boundingBox: Rect ->
//            val rect: Rect = boundingBox
//            Log.d("barcaCodeGoogle", "boundingBox: $rect")
//        }
//        barcode.displayValue?.let { displayValue: String ->
//            Log.d("barcaCodeGoogle", "displayValue: $displayValue")
//        }
//        barcode.rawValue?.let { rawValue: String ->
//            Log.d("barcaCodeGoogle", "rawValue: $rawValue")
//        }
//        barcode.format.let { format: Int ->
//            Log.d("barcaCodeGoogle", "format: $format")
//        }
//        barcode.valueType.let { valueType: Int ->
//            Log.d("barcaCodeGoogle", "valueType: $valueType")
//        }
//        barcode.cornerPoints?.let { cornerPoints: Array<Point> ->
//            Log.d("barcaCodeGoogle", "cornerPoints: $cornerPoints")
//        }
//    }
//
//    private const val OFFICIAL_SITE = "https://www.alura.com.br"
//}
//
//fun Barcode.CalendarDateTime.toCalendarDateTime(): CalendarDateTime {
//    return CalendarDateTime(
//        year = this.year,
//        month = this.month,
//        day = this.day,
//        hours = this.hours,
//        minutes = this.minutes,
//        seconds = this.seconds,
//        rawValue = this.rawValue
//    )
//}