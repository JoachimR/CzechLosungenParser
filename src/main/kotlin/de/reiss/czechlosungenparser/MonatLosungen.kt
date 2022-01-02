package de.reiss.czechlosungenparser

import java.util.*

fun main() {
    val inCzech = "@Ježíš Kristus praví: Pojďte a uvidíte!@Jan 1,39" +
            "@Hněváte-li se, nehřešte. Nenechte nad svým hněvem zapadnout slunce.@Efezským 4,26" +
            "@V každý čas se v Duchu svatém modlete a proste, bděte na modlitbách a vytrvale se přimlouvejte za všechny bratry.@Efezským 6,18" +
            "@Marie Magdalská šla k učedníkům a oznámila jim: Viděla jsem Pána a toto mi řekl.@Jan 20,18" +
            "@Modlím se za tebe, milovaný, aby se ti ve všem dobře dařilo a abys byl zdráv – tak jako se dobře daří tvé duši.@3.Janova 1,2" +
            "@Polož si mě na srdce jako pečeť, jako pečeť na své rámě. Vždyť silná jako smrt je láska.@Píseň 8,6" +
            "@Po Bohu žízním, po živém Bohu.@Žalm 42,3" +
            "@Tehdy zaplesají stromy v lese vstříc Hospodinu, že přichází soudit zemi.@1.Paralipomenon 16,33" +
            "@Láska k Bohu je ta nejvznešenější moudrost.@Sírachovec 1,10 L17" +
            "@Veliké a podivuhodné jsou tvé činy, Pane Bože všemohoucí; spravedlivé a pravdivé jsou tvé cesty, Králi národů.@Zjevení 15,3" +
            "@Běda těm, kdo říkají zlu dobro a dobru zlo, kdo vydávají tmu za světlo a světlo za tmu, kdo vydávají hořké za sladké a sladké za hořké!@Izaiáš 5,20" +
            "@Vlk bude pobývat s beránkem, levhart s kůzletem odpočívat. Tele a lvíče i žírný dobytek budou spolu a malý hoch je bude vodit.@Izaiáš 11,6"


    out(inCzech)
}

const val losungsJahr = "22"

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
