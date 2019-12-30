package de.reiss.czechlosungenparser

import java.util.*

fun main() {
    val inCzech = (""
            + "\$Věrný je Bůh.\$1.Korintským 1,9"
            + "\$Bylo za vás zaplaceno výkupné, nebuďte otroky lidí!\$1.Korintským 7,23"
            + "\$Co vám říkám, říkám všem: Bděte!\$Marek 13,37"
            + "\$Co je zaseto jako pomíjitelné, vstává jako nepomíjitelné.\$1.Korintským 15,42"
            + "\$Každý ať slouží druhým tím darem milosti, který přijal.\$1.Petrova 4,10"
            + "\$Ty sám znáš srdce všech lidí.\$1.Královská 8,39 K"
            + "\$Hospodinův anděl se Eliáše dotkl a řekl: \"Vstaň a jez, máš před sebou dlouhou cestu!\"\$1.Královská 19,7"
            + "\$Díky ti vzdávám za to, jak zázračně jsi mne stvořil. Plna divů jsou tvá díla, a má duše to dobře ví.\$Žalm 139,14 F"
            + "\$V Kristu Bůh usmířil svět se sebou.\$2.Korintským 5,19"
            + "\$Usilujte o pokoj toho města, do něhož jsem vás přestěhoval, modlete se za ně k Hospodinu, neboť v jeho pokoji i vy budete mít pokoj.\$Jeremiáš 29,7"
            + "\$Přijdou s pláčem, ale já je utěším a povedu.\$Jeremiáš 31,9"
            + "\$Cožpak nemáš lámat svůj chléb hladovému, přijímat do domu utištěné, ty, kdo jsou bez přístřeší? Vidíš-li nahého, obléknout ho, nebýt netečný k vlastní krvi?\$Izaiáš 58,7")

    out(inCzech)
}

const val losungsJahr = "20"

fun out(`in`: String?) {
    val st = StringTokenizer(`in`, "$")
    var counter = 0
    val set: ArrayList<MonatsLosungXmlObject> = ArrayList<MonatsLosungXmlObject>()
    var current = MonatsLosungXmlObject()
    val setSize = 2
    var monthCounter = 0
    while (st.hasMoreTokens()) {
        val s = st.nextToken()
        if (counter % setSize == 0) {
            current = MonatsLosungXmlObject()
            monthCounter++
            current.datum = monthString(monthCounter)
            current.losungstext = s
        } else if (counter % setSize == 1) {
            current.losungsvers = s
            set.add(current)
        }
        counter++
    }
    println("===========================================")
    println("\n")
    for (o in set) {
        println(o.toString())
    }
    println("\n")
    println("\n")
}

fun monthString(counter: Int): String? {
    return ("20" + losungsJahr + "-" + String.format("%02d", counter) + "-" + "01")
}
