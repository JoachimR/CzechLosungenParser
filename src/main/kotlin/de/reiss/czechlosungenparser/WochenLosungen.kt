package de.reiss.czechlosungenparser

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object WochenLosungen {

    @JvmStatic
    fun main(args: Array<String>) {
        val inCzech = "" +
                "\$05.01.2020\$2. neděle po Vánocích\$Spatřili jsme jeho slávu, slávu, jakou má od Otce jednorozený Syn, plný milosti a pravdy.\$Jan 1,14b" +
                "\$12.01.2020\$1. neděle po Zjevení Páně\$Ti, kdo se dají vést Duchem Božím, jsou synové Boží.\$Římanům 8,14" +
                "\$19.01.2020\$2. neděle po Zjevení Páně\$Z plnosti jeho my všickni vzali jsme, a to milost za milost.\$Jan 1,16 K" +
                "\$26.01.2020\$3. neděle po Zjevení Páně\$Přijdou od východu i západu, od severu i jihu, a budou stolovat v Božím království.\$Lukáš 13,29" +
                "\$02.02.2020\$Poslední neděle po Zjevení Páně\$Nad tebou vzejde Hospodin a ukáže se nad tebou jeho sláva.\$Izaiáš 60,2" +
                "\$09.02.2020\$Septuagesimae\$Ne pro nějaké naše spravedlnosti padajíce, pokorně prosíme tebe, ale pro milosrdenství tvá mnohá.\$Daniel 9,18 K" +
                "\$16.02.2020\$Sexagesimae\$Jestliže dnes uslyšíte jeho hlas, nezatvrzujte svá srdce!\$Židům 3,15" +
                "\$23.02.2020\$Estomihi\$Hle, jdeme do Jeruzaléma, a na Synu člověka se naplní všechno, co je psáno u proroků.\$Lukáš 18,31" +
                "\$01.03.2020\$Invocavit\$Proto se zjevil Syn Boží, aby zmařil činy ďáblovy.\$1.Janova 3,8b" +
                "\$08.03.2020\$Reminiscere\$Bůh prokazuje svou lásku k nám tím, že Kristus za nás zemřel, když jsme ještě byli hříšní.\$Římanům 5,8" +
                "\$15.03.2020\$Oculi\$Kdo položí ruku na pluh a ohlíží se zpět, není způsobilý pro království Boží.\$Lukáš 9,62" +
                "\$22.03.2020\$Laetare\$Jestliže pšeničné zrno nepadne do země a nezemře, zůstane samo. Zemře-li však, vydá mnohý užitek.\$Jan 12,24" +
                "\$29.03.2020\$Judica\$Syn člověka nepřišel, aby si dal sloužit, ale aby sloužil a dal svůj život jako výkupné za mnohé.\$Matouš 20,28" +
                "\$05.04.2020\$Květná neděle\$Musí být vyvýšen Syn člověka, aby každý, kdo v něho věří, měl život věčný.\$Jan 3,14.15" +
                "\$12.04.2020\$Velikonoční neděle\$Byl jsem mrtev – a hle, živ jsem na věky věků. Mám klíče od smrti i hrobu.\$Zjevení 1,18" +
                "\$19.04.2020\$Quasimodogeniti\$Veleben buď Bůh a Otec Pána našeho Ježíše Krista, neboť nám ze svého velikého milosrdenství dal vzkříšením Ježíše Krista nově se narodit k živé naději.\$1.Petrova 1,3" +
                "\$26.04.2020\$Misericordias Domini\$Já jsem dobrý pastýř. Moje ovce slyší můj hlas, já je znám, jdou za mnou a já jim dávám věčný život.\$Jan 10,11.27.28" +
                "\$03.05.2020\$Jubilate\$Kdo je v Kristu, je nové stvoření. Co je staré, pominulo, hle, je tu nové!\$2.Korintským 5,17" +
                "\$10.05.2020\$Cantate\$Zpívejte Hospodinu píseň novou, neboť učinil podivuhodné věci.\$Žalm 98,1" +
                "\$17.05.2020\$Rogate\$Požehnaný Bůh, kterýž neodstrčil modlitby mé, a milosrdenství svého ode mne neodjal.\$Žalm 66,20 K" +
                "\$24.05.2020\$Exaudi\$Až budu vyvýšen ze země, přitáhnu všecky k sobě.\$Jan 12,32" +
                "\$31.05.2020\$Neděle svatodušní\$ Ne mocí ani silou, nýbrž mým duchem, praví Hospodin zástupů.\$Zachariáš 4,6" +
                "\$07.06.2020\$Neděle svaté Trojice\$Milost Pána Ježíše Krista a Boží láska a společenství Svatého Ducha se všemi vámi.\$2.Korintským 13,13 MP" +
                "\$14.06.2020\$1. neděle po sv. Trojici\$Kdo slyší vás, slyší mne, a kdo odmítá vás, odmítá mne.\$Lukáš 10,16" +
                "\$21.06.2020\$2. neděle po sv. Trojici\$Pojdtež ke mně všickni, kteříž pracujete a obtíženi jste, a já vám odpočinutí dám.\$Matouš 11,28 K" +
                "\$28.06.2020\$3. neděle po sv. Trojici\$Přišel Syn člověka, aby hledal a spasil, což bylo zahynulo.\$Lukáš 19,10 K" +
                "\$05.07.2020\$4. neděle po sv. Trojici\$Jedni druhých břemena neste a tak plňte zákon Kristův.\$Galatským 6,2 K" +
                "\$12.07.2020\$5. neděle po sv. Trojici\$Milostí spaseni jste skrze víru.\$Efezským 2,8 K" +
                "\$19.07.2020\$6. neděle po sv. Trojici\$Toto praví Hospodin, tvůj stvořitel: Neboj se, já jsem tě vykoupil, povolal jsem tě tvým jménem.\$Izaiáš 43,1" +
                "\$26.07.2020\$7. neděle po sv. Trojici\$Nejste již cizinci a přistěhovalci, máte právo Božího lidu a patříte k Boží rodině.\$Efezským 2,19" +
                "\$02.08.2020\$8. neděle po sv. Trojici\$Žijte jako děti světla – ovocem světla je vždy dobrota, spravedlnost a pravda.\$Efezským 5,8.9" +
                "\$09.08.2020\$9. neděle po sv. Trojici\$Komu bylo mnoho dáno, od toho se mnoho očekává, a komu mnoho svěřili, od toho budou žádat tím více.\$Lukáš 12,48" +
                "\$16.08.2020\$10. neděle po sv. Trojici\$Blaze národu, jemuž je Hospodin Bohem, lidu, jejž si zvolil za dědictví.\$Žalm 33,12" +
                "\$23.08.2020\$11. neděle po sv. Trojici\$Bůh se staví proti pyšným, ale pokorným dává milost.\$1.Petrova 5,5" +
                "\$30.08.2020\$12. neděle po sv. Trojici\$Třtiny nalomené nedolomí, a lnu kouřícího se neuhasí.\$Izaiáš 42,3 K" +
                "\$06.09.2020\$13. neděle po sv. Trojici\$Cožkoli jste činili jednomu z bratří těchto mých nejmenších, mně jste učinili.\$Matouš 25,40 K" +
                "\$13.09.2020\$14. neděle po sv. Trojici\$Oslavuj Pána, duše má, na jeho dobra nezapomeň!\$Žalm 103,2 R" +
                "\$20.09.2020\$15. neděle po sv. Trojici\$Všechnu svou starost vložte na něj, neboť mu na vás záleží.\$1.Petrova 5,7" +
                "\$27.09.2020\$16. neděle po sv. Trojici\$Ježíš Kristus zlomil moc smrti a zjevil nepomíjející život v evangeliu.\$2.Timoteovi 1,10" +
                "\$04.10.2020\$Den díkůvzdání za úrodu\$Oči všech vyhlížejí k tobě a ty jim dáváš jejich pokrm v pravý čas.\$Žalm 145,15 Z" +
                "\$11.10.2020\$18. neděle po sv. Trojici\$Máme od něho toto přikázání: Kdo miluje Boha, ať miluje i svého bratra.\$1.Janova 4,21" +
                "\$18.10.2020\$19. neděle po sv. Trojici\$Uzdrav mne, Hospodine, a zdráv budu; vysvoboď mne, a vysvobozen budu.\$Jeremiáš 17,14 K" +
                "\$25.10.2020\$20. neděle po sv. Trojici\$Člověče, bylo ti oznámeno, co je dobré a co od tebe Hospodin žádá: jen to, abys zachovával právo, miloval milosrdenství a pokorně chodil se svým Bohem.\$Micheáš 6,8" +
                "\$01.11.2020\$21. neděle po sv. Trojici\$Nedej se přemoci zlému, ale přemáhej v dobrém zlé.\$Římanům 12,21 K" +
                "\$08.11.2020\$22. neděle po sv. Trojici\$Blaze těm, kdo působí pokoj, neboť oni budou nazváni syny Božími.\$Matouš 5,9" +
                "\$15.11.2020\$23. neděle po sv. Trojici\$Musíme se ukázat před soudným stolcem Kristovým.\$2.Korintským 5,10" +
                "\$22.11.2020\$Poslední neděle církevního roku\$Mějte bedra přepásána a vaše lampy ať hoří.\$Lukáš 12,35 P" +
                "\$29.11.2020\$1. neděle adventní\$Aj, král tvůj přijde tobě spravedlivý a spasení plný.\$Zachariáš 9,9 K" +
                "\$06.12.2020\$2. neděle adventní\$Napřimte se a zvedněte hlavy, neboť vaše vykoupení je blízko.\$Lukáš 21,28" +
                "\$13.12.2020\$3. neděle adventní\$Připravte na poušti cestu Hospodinu! Panovník Hospodin přichází s mocí.\$Izaiáš 40,3.10" +
                "\$20.12.2020\$4. neděle adventní\$Radujte se v Pánu vždycky, znovu říkám, radujte se! Pán je blízko.\$Filipským 4,4.5b" +
                "\$27.12.2020\$1. neděle po Vánocích\$Spatřili jsme jeho slávu, slávu, jakou má od Otce jednorozený Syn, plný milosti a pravdy.\$Jan 1,14b"

        val st = StringTokenizer(inCzech, "$")
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
