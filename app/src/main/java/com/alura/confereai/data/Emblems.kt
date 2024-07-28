package com.alura.confereai.data

import com.alura.confereai.R


open class Emblem(
    var title: String = "",
    val description: String = "",
    val action: String = "",
    val image: Int? = null
)

data class WifiBarcode(
    val ssid: String,
    val password: String,
    val encryptionType: Int
) : Emblem(
    "Boas vindas!",
    "Sinta-se em casa, você pode ser conectar a internet pela WiFi $ssid, senha $password",
    "Conectar",
    image = R.drawable.emblem_wifi
)


data class UrlBarcode(
    val url: String,
    val urTitle: String
) : Emblem(
    "Mergulhe em IA",
    "Conheça os cursos e formações de Inteligência Artificial no maior ecossistema de ensino do Brasil",
    "Visitar site",
    image = R.drawable.emblem_alura,
)


data class PhoneBarcode(
    val number: String,
    val phoneType: Int
) : Emblem(
    "Telefone",
    "Número de telefone",
    "Ligar",
)

data class GeoBarcode(
    val lat: Double,
    val lng: Double
) : Emblem(
    "Achei você",
    "Que conhecer nossas instalações? Venha nos visitar algum dia na sede de São Paulo",
    "Ver no mapa",
    image = R.drawable.emblem_maps,
)

data class CalendarEventBarcode(
    val eventDescription: String,
    val location: String,
    val organizer: String,
    val status: String,
    val summary: String,
    val start: CalendarDateTime?,
    val end: CalendarDateTime?
) : Emblem(
    "Isaac Asimov",
    "O Palestrante Asimov se apresenta no palco principal para falar de Inteligência Artificial na ficção e seu impacto na realidade.",
    "Salvar na agenda",
    image = R.drawable.emblem_asimov,
)

data class ContactInfoBarcode(
    val addresses: List<String>,
    val emails: List<EmailBarcode>,
    val name: String,
    val organization: String,
    val phones: List<String>,
    val contactTitle: String,
    val urls: List<String>
) : Emblem(
    "Informações de contato",
    "Informações do contato",
    "Salvar contato",
)

data class EmailBarcode(
    val body: String,
    val subject: String,
    val address: String,
    val emailType: Int
) : Emblem(
    "Email",
    "Endereço de email",
    "Enviar email",
)

data class SmsBarcode(
    val message: String,
    val phoneNumber: String
) : Emblem(
    "SMS",
    "Mensagem de texto",
    "Enviar SMS",
)

data class CalendarDateTime(
    val year: Int,
    val month: Int,
    val day: Int,
    val hours: Int,
    val minutes: Int,
    val seconds: Int,
    val rawValue: String?
) {
    fun toMillis(): Long {
        val calendar = java.util.Calendar.getInstance()
        calendar.set(year, month, day, hours, minutes, seconds)
        return calendar.timeInMillis
    }
}
