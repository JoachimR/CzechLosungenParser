package de.reiss.czechlosungenparser

import java.util.*

fun main() {
    val inCzech =
        "@Mnozí se ptají: Kdo ukáže nám, co je dobré? Dej dopadat na nás světlu tváře své, ó Bože.@Žalm 4,7 F" +
                "@Radujte se, že vaše jména jsou zapsána v nebesích.@Lukáš 10,20" +
                "@Pravím vám, budou-li oni mlčet, bude volat kamení.@Lukáš 19,40" +
                "@Kristus je obraz Boha neviditelného, prvorozený všeho stvoření.@Koloským 1,15" +
                "@Otevři svá ústa za němého, za právo všech postižených.@Přísloví 31,8" +
                "@Boha je třeba víc poslouchat než lidi.@Skutky 5,29" +
                "c@Bůh od nikoho z nás není daleko. V něm totiž žijeme, hýbáme se a jsme.@Skutky 17,27 P" +
                "@Nakloň, Hospodine, své ucho a slyš, otevři, Hospodine, své oči a viz!@2.Královská 19,16" +
                "@Sejete mnoho, a sklízí se málo. Jen jezte, nenasytíte se; jen pijte, žízeň neuhasíte; jen se oblékejte, nezahřejete se. Kdo se dává najmout za mzdu, ukládá ji do děravého váčku.@Aggeus 1,6" +
                "@Mějme zájem jeden o druhého a povzbuzujme se k lásce a k dobrým skutkům.@Židům 10,24" +
                "@Pán nechť řídí vaše srdce k Boží lásce a k trpělivosti Kristově.@2.Tesalonickým 3,5" +
                "@Plesej a raduj se, sijónská dcero, neboť už přicházím a budu bydlet uprostřed tebe, je výrok Hospodinův.@Zachariáš 2,14"

    out(inCzech)
}

const val losungsJahr = "21"

fun out(`in`: String?) {
    val st = StringTokenizer(`in`, "@")
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
