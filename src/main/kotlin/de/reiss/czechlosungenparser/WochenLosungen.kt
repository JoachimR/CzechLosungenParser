package de.reiss.czechlosungenparser

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object WochenLosungen {

    @JvmStatic
    fun main(args: Array<String>) {
        val inCzech =
            buildString {
        append("@01.01.2023@Nový rok – neděle po Vánocích@Ježíš Kristus je tentýž včera i dnes i na věky.@Židům 13,8")
        append("@08.01.2023@1. neděle po Zjevení Páně@Ti, kdo se dají vést Duchem Božím, jsou synové Boží.@Římanům 8,14")
        append("@15.01.2023@2. neděle po Zjevení Páně@Z jeho plnosti jsme byli obdarováni my všichni milostí za milostí.@Jan 1,16")
        append("@22.01.2023@3. neděle po Zjevení Páně@A přijdou od východu i západu, od severu i jihu, a budou stolovat v Božím království.@Lukáš 13,29")
        append("@29.01.2023@Poslední neděle po Zjevení Páně@Nad tebou vzejde Hospodin a ukáže se nad tebou jeho sláva.@Izaiáš 60,2b")
        append("@05.02.2023@Neděle Devítník – Septuagesimae@Ne pro své spravedlivé činy ti předkládáme své prosby o smilování, ale pro tvé velké slitování.@Daniel 9,18b")
        append("@12.02.2023@Neděle po Devítníku – Sexagesimae@Jestliže dnes uslyšíte jeho hlas, nezatvrzujte svá srdce!@Židům 3,15")
        append("@19.02.2023@Neděle masopustní – Estomihi@Hle, jdeme do Jeruzaléma a na Synu člověka se naplní všechno, co je psáno u proroků.@Lukáš 18,31")
        append("@26.02.2023@1. neděle postní – Invocavit@Proto se zjevil Syn Boží, aby zmařil činy ďáblovy.@1.Janova 3,8b")
        append("@05.03.2023@2. neděle postní – Reminiscere@Bůh však prokazuje svou lásku k nám tím, že Kristus za nás zemřel, když jsme ještě byli hříšní.@Římanům 5,8")
        append("@12.03.2023@3. neděle postní – Oculi@Kdo položí ruku na pluh a ohlíží se zpět, není způsobilý pro království Boží.@Lukáš 9,62")
        append("@19.03.2023@4. neděle postní – Laetare@Jestliže pšeničné zrno nepadne do země a nezemře, zůstane samo. Zemře-li však, vydá mnohý užitek.@Jan 12,24")
        append("@26.03.2023@5. neděle postní – Judica@Syn člověka nepřišel, aby si dal sloužit, ale aby sloužil a dal svůj život jako výkupné za mnohé.@Matouš 20,28")
        append("@02.04.2023@Květná neděle@Tak musí být vyvýšen Syn člověka, aby každý, kdo v něho věří, měl život věčný.@Jan 3,14b.15")
        append("@09.04.2023@Velikonoční neděle@Byl jsem mrtev – a hle, živ jsem na věky věků. Mám klíče od smrti i pekla.@Zjevení 1,18")
        append("@16.04.2023@1. neděle po Velikonocích – Quasimodogeniti@Veleben buď Bůh a Otec Pána našeho Ježíše Krista, neboť nám ze svého velikého milosrdenství dal vzkříšením Ježíše Krista nově se narodit k živé naději.@1.Petrova 1,3")
        append("@23.04.2023@2. neděle po Velikonocích – Misericordias Domini@Já jsem dobrý pastýř. Moje ovce slyší můj hlas, já je znám, jdou za mnou a já jim dávám věčný život.@Jan 10,11a.27-28a")
        append("@30.04.2023@3. neděle po Velikonocích – Jubilate@Kdo je v Kristu, je nové stvoření. Co je staré, pominulo, hle, je tu nové!@2.Korintským 5,17")
        append("@07.05.2023@4. neděle po Velikonocích – Cantate@Zpívejte Hospodinu píseň novou, neboť učinil podivuhodné věci.@Žalm 98,1")
        append("@14.05.2023@5. neděle po Velikonocích – Rogate@Požehnán buď Bůh, že mou modlitbu nezamítl a své milosrdenství mi neodepřel.@Žalm 66,20")
        append("@21.05.2023@Neděle po nanebevstoupení – Exaudi@A já, až budu vyvýšen ze země, přitáhnu všecky k sobě.@Jan 12,32")
        append("@28.05.2023@Neděle svatodušní@Ne mocí ani silou, nýbrž mým duchem, praví Hospodin zástupů.@Zachariáš 4,6b")
        append("@04.06.2023@Neděle svaté Trojice@Milost našeho Pána Ježíše Krista a láska Boží a přítomnost Ducha svatého se všemi vámi.@2.Korintským 13,13")
        append("@11.06.2023@1. neděle po sv. Trojici@Kdož vás slyší, mne slyší; a kdo vámi pohrdá, mnou pohrdá.@Lukáš 10,16a K")
        append("@18.06.2023@2. neděle po sv. Trojici@Pojďte ke mně, všichni, kdo se namáháte a jste obtíženi břemeny, a já vám dám odpočinout.@Matouš 11,28")
        append("@25.06.2023@3. neděle po sv. Trojici@Neboť Syn člověka přišel, aby hledal a spasil, co zahynulo.@Lukáš 19,10")
        append("@02.07.2023@4. neděle po sv. Trojici@Berte na sebe břemena jedni druhých, tak naplníte zákon Kristův.@Galatským 6,2")
        append("@09.07.2023@5. neděle po sv. Trojici@Milostí tedy jste spaseni skrze víru. Spasení není z vás, je to Boží dar.@Efezským 2,8-9")
        append("@16.07.2023@6. neděle po sv. Trojici@Nyní toto praví Hospodin, tvůj stvořitel, Jákobe, tvůrce tvůj, Izraeli: Neboj se, já jsem tě vykoupil, povolal jsem tě tvým jménem, jsi můj.@Izaiáš 43,1")
        append("@23.07.2023@7. neděle po sv. Trojici@Nejste již tedy cizinci a přistěhovalci, máte právo Božího lidu a patříte k Boží rodině.@Efezským 2,19")
        append("@30.07.2023@8. neděle po sv. Trojici@Žijte proto jako děti světla – ovocem světla je vždy dobrota, spravedlnost a pravda.@Efezským 5,9")
        append("@06.08.2023@9. neděle po sv. Trojici@Komu bylo mnoho dáno, od toho se mnoho očekává, a komu mnoho svěřili, od toho budou žádat tím více.@Lukáš 12,48b")
        append("@13.08.2023@10. neděle po sv. Trojici@Blaze národu, jemuž je Hospodin Bohem, lidu, jejž si zvolil za dědictví.@Žalm 33,12")
        append("@20.08.2023@11. neděle po sv. Trojici@Bůh se staví proti pyšným, ale pokorným dává milost.@1.Petrova 5,5b")
        append("@27.08.2023@12. neděle po sv. Trojici@Nalomenou třtinu nedolomí, nezhasí knot doutnající.@Izaiáš 42,3a")
        append("@03.09.2023@13. neděle po sv. Trojici@Cokoliv jste učinili jednomu z těchto mých nepatrných bratří, mně jste učinili.@Matouš 25,40b")
        append("@10.09.2023@14. neděle po sv. Trojici@Dobrořeč, má duše, Hospodinu, nezapomínej na žádné jeho dobrodiní!@Žalm 103,2")
        append("@17.09.2023@15. neděle po sv. Trojici@Všechnu svou starost vložte na něj, neboť mu na vás záleží.@1.Petrova 5,7")
        append("@24.09.2023@16. neděle po sv. Trojici@[Ježíš Kristus] zlomil moc smrti a zjevil nepomíjející život v evangeliu.@2.Timoteovi 1,10b")
        append("@01.10.2023@17. neděle po sv. Trojici@Oči všech s nadějí vzhlížejí k tobě a ty jim v pravý čas dáváš pokrm.@Žalm 145,15")
        append("@08.10.2023@18. neděle po sv. Trojici@A tak máme od něho toto přikázání: Kdo miluje Boha, ať miluje i svého bratra.@1.Janova 4,21")
        append("@15.10.2023@19. neděle po sv. Trojici@Uzdrav mě, Hospodine, a budu zdráv, spas mě a budu spasen.@Jeremiáš 17,14")
        append("@22.10.2023@20. neděle po sv. Trojici@Člověče, bylo ti oznámeno, co je dobré a co od tebe Hospodin žádá: jen to, abys zachovával právo, miloval milosrdenství a pokorně chodil se svým Bohem.@Micheáš 6,8")
        append("@29.10.2023@21. neděle po sv. Trojici@Nedej se přemoci zlem, ale přemáhej zlo dobrem.@Římanům 12,21")
        append("@05.11.2023@22. neděle po sv. Trojici@Ale u tebe je odpuštění; tak vzbuzuješ bázeň.@Žalm 130,4")
        append("@12.11.2023@23. neděle po sv. Trojici@Blaze těm, kdo působí pokoj, neboť oni budou nazváni syny Božími.@Matouš 5,9")
        append("@19.11.2023@24. neděle po sv. Trojici@Všichni se musíme ukázat před soudným stolcem Kristovým.@2.Korintským 5,10a")
        append("@26.11.2023@Poslední neděle církevního roku@Buďte připraveni a vaše lampy ať hoří.@Lukáš 12,35")
        append("@03.12.2023@1. neděle adventní@Hle, přichází k tobě tvůj král, spravedlivý a nesoucí spásu.@Zachariáš 9,9b")
        append("@10.12.2023@2. neděle adventní@Napřimte se a zvedněte hlavy, neboť vaše vykoupení je blízko.@Lukáš 21,28")
        append("@17.12.2023@3. neděle adventní@Připravte na poušti cestu … Panovník Hospodin přichází s mocí.@Izaiáš 40,3.10")
        append("@24.12.2023@4. neděle adventní@Nebojte se, hle, zvěstuji vám velikou radost, která bude pro všechen lid. Dnes se vám narodil Spasitel, Kristus Pán, v městě Davidově. @Lukáš 2,10b.11")
        append("@31.12.2023@Neděle po vánocích@V rukou tvých jsou časové moji.@Žalm 31,16a K")
    }

        val st = StringTokenizer(inCzech, "@")
        var counter = 0
        val setWithoutEndDatum: TreeSet<WochenLosungXMlObject> = TreeSet()
        var current = WochenLosungXMlObject()
        // XmlObject last = new XmlObject();
        while (st.hasMoreTokens()) {
            val s = st.nextToken()
            if (counter % 4 == 0) {
                current = WochenLosungXMlObject()
                current.startDatum = getDateOfString(s)
                // Calendar cal = Calendar.getInstance();
// cal.setTime(current.startDatum);
// cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) -
// 1);
// last.endDatum = cal.getTime();
// objs.add(last);
            } else if (counter % 4 == 1) {
                current.sonntag = s
            } else if (counter % 4 == 2) {
                current.losungstext = s
            } else if (counter % 4 == 3) {
                current.losungsvers = s
                setWithoutEndDatum.add(current)
            }
            counter++
        }
        val resultSet: TreeSet<WochenLosungXMlObject> = TreeSet()
        // add enddatum for all wochenlosungen
        var before: WochenLosungXMlObject = setWithoutEndDatum.first()
        for (o in setWithoutEndDatum) {
            val cal = Calendar.getInstance()
            cal.time = o.startDatum
            cal[Calendar.DAY_OF_YEAR] = cal[Calendar.DAY_OF_YEAR] - 1
            before.endDatum = cal.time
            resultSet.add(before)
            before = o
        }
        // add the last wochenlosung too
        val cal = Calendar.getInstance()
        cal[Calendar.DAY_OF_YEAR] = 366
        before.endDatum = cal.time
        resultSet.add(before)
        for (o in resultSet) {
            println(o.toString())
        }
        // TODO last element takes old year on enddatum
    }

    fun getDateOfString(dateString: String?): Date {
        var d: Date? = null
        val cal = Calendar.getInstance()
        try {
            val sdf = SimpleDateFormat("dd.MM.yyyy")
            d = sdf.parse(dateString)
            cal.time = d
        } catch (e: ParseException) {
        }
        return cal.time
    }
}
