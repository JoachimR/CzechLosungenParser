package de.reiss.czechlosungenparser

import java.util.*

fun main() {
    val inCzech = buildString {
        append("@Bůh viděl, že všechno, co učinil, je velmi dobré.@1.Mojžíšova 1,31")
        append("@Tu Sára řekla: \"Bůh mi dopřál, že se mohu smát.\"@1.Mojžíšova 21,6")
        append("@Kdo nás odloučí od lásky Kristovy?@Římanům 8,35")
        append("@Proto Kristus umřel i ožil, aby se stal Pánem i mrtvých i živých.@Římanům 14,9")
        append("@Neodpírej dobrodiní těm, kteří je potřebují, je-li v tvé moci je prokázat.@Přísloví 3,27")
        append("@Dej ti Bůh z rosy nebes a ze žírnosti země, i hojnost obilí a moštu.@1.Mojžíšova 27,28")
        append("@Já však vám pravím: Milujte své nepřátele a modlete se za ty, kdo vás pronásledují, abyste byli syny nebeského Otce.@Matouš 5,44-45")
        append("@Neboť ty jsi má pomoc, a proto ve stínu tvých křídel se budu radovat.@Žalm 63,8 F")
        append("@Řekl jim: \"A za koho mě pokládáte vy?\"@Matouš 16,15")
        append("@Podle slova však také jednejte, nebuďte jen posluchači – to byste klamali sami sebe.@Jakubova 1,22")
        append("@Sám nebesa roztahuje, kráčí po hřebenech mořských vln, on udělal souhvězdí Lva, Orióna i Plejády a souhvězdí jižní.@Jób 9,8-9")
        append("@Mé oči viděly tvé spasení, které jsi připravil přede všemi národy.@Lukáš 2,30-31")
    }


    out(inCzech)
}

const val losungsJahr = "23"

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
