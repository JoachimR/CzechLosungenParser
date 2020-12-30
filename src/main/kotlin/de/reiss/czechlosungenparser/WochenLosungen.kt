package de.reiss.czechlosungenparser

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object WochenLosungen {

    @JvmStatic
    fun main(args: Array<String>) {
        val inCzech = "" +
                "@03.01.2021@2. Neděle po Vánocích@Spatřili jsme jeho slávu, slávu, jakou má od Otce jednorozený Syn, plný milosti a pravdy.@Jan 1,14b" +
                "@10.01.2021@1. neděle po Zjevení Páně@Ti, kdo se dají vést Duchem Božím, jsou synové Boží.@Římanům 8,14" +
                "@17.01.2021@2. neděle po Zjevení Páně@Z plnosti jeho my všickni vzali jsme, a to milost za milost.@Jan 1,16 K" +
                "@24.01.2021@3. neděle po Zjevení Páně@Přijdou od východu i západu, od severu i jihu, a budou stolovat v Božím království.@Lukáš 13,29" +
                "@31.01.2021@Poslední neděle po Zjevení Páně@Hle, temnota přikrývá zemi, soumrak národy, ale nad tebou vzejde Hospodin a ukáže se nad tebou jeho sláva.@Izaiáš 60,2b" +
                "@07.02.2021@Sexagesimae@Jestliže dnes uslyšíte jeho hlas, nezatvrzujte svá srdce!@Židům 3,15" +
                "@14.02.2021@Estomihi@Hle, jdeme do Jeruzaléma a na Synu člověka se naplní všechno, co je psáno u proroků.@Lukáš 18,31" +
                "@21.02.2021@Invocavit@Proto se zjevil Syn Boží, aby zmařil činy ďáblovy.@1.Janova 3,8b" +
                "@28.02.2021@Reminiscere@Bůh prokazuje svou lásku k nám tím, že Kristus za nás zemřel, když jsme ještě byli hříšní.@Římanům 5,8" +
                "@07.03.2021@Oculi@Kdo položí ruku na pluh a ohlíží se zpět, není způsobilý pro království Boží.@Lukáš 9,62" +
                "@14.03.2021@Laetare@Jestliže pšeničné zrno nepadne do země a nezemře, zůstane samo. Zemře-li však, vydá mnohý užitek.@Jan 12,24" +
                "@21.03.2021@Judica@Syn člověka nepřišel, aby si dal sloužit, ale aby sloužil a dal svůj život jako výkupné za mnohé.@Matouš 20,28" +
                "@28.03.2021@Květná neděle@Jako Mojžíš vyvýšil hada na poušti, tak musí být vyvýšen Syn člověka, aby každý, kdo v něho věří, měl život věčný.@Jan 3,14.15" +
                "@04.04.2021@Velikonoční neděle@Byl jsem mrtev – a hle, živ jsem na věky věků. Mám klíče od smrti i hrobu.@Zjevení 1,18" +
                "@11.04.2021@Quasimodogeniti@Veleben buď Bůh a Otec Pána našeho Ježíše Krista, neboť nám ze svého velikého milosrdenství dal vzkříšením Ježíše Krista nově se narodit k živé naději.@1.Petrova 1,3" +
                "@18.04.2021@Misericordias Domini@Já jsem dobrý pastýř. Moje ovce uposlechnou mého hlasu; já je znám a ony jdou za mnou. Já jim dávám věčný život.@Jan 10,11.27-28 P" +
                "@25.04.2021@Jubilate@Kdo je v Kristu, je nové stvoření. Co je staré, pominulo, hle, je tu nové!@2.Korintským 5,17" +
                "@02.05.2021@Cantate@Zpívejte Hospodinu píseň novou, neboť učinil podivuhodné věci.@Žalm 98,1" +
                "@09.05.2021@Rogate@Požehnán buď Bůh, že mou modlitbu nezamítl a své milosrdenství mi neodepřel.@Žalm 66,20" +
                "@16.05.2021@Exaudi@Až budu vyvýšen ze země, přitáhnu všecky k sobě.@Jan 12,32" +
                "@23.05.2021@Neděle svatodušní@ Ne mocí ani silou, nýbrž mým duchem, praví Hospodin zástupů.@Zachariáš 4,6" +
                "@30.05.2021@Neděle svaté Trojice@Milost našeho Pána Ježíše Krista a láska Boží a přítomnost Ducha svatého se všemi vámi.@2.Korintským 13,13" +
                "@06.06.2021@1. neděle po sv. Trojici@Kdo slyší vás, slyší mne, a kdo odmítá vás, odmítá mne.@Lukáš 10,16a" +
                "@13.06.2021@2. neděle po sv. Trojici@Pojďte ke mně, všichni, kdo se namáháte a jste obtíženi břemeny, a já vám dám odpočinout.@Matouš 11,28" +
                "@20.06.2021@3. neděle po sv. Trojici@Syn člověka přišel, aby hledal a spasil, co zahynulo.@Lukáš 19,10" +
                "@27.06.2021@4. neděle po sv. Trojici@Berte na sebe břemena jedni druhých, tak naplníte zákon Kristův.@Galatským 6,2" +
                "@04.07.2021@5. neděle po sv. Trojici@Milostí spaseni jste skrze víru, a to ne sami z sebe: dar jest to Boží.@Efezským 2,8 K" +
                "@11.07.2021@6. neděle po sv. Trojici@Toto praví Hospodin, tvůj stvořitel, Jákobe, tvůrce tvůj, Izraeli: Neboj se, já jsem tě vykoupil, povolal jsem tě tvým jménem, jsi můj.@Izaiáš 43,1" +
                "@18.07.2021@7. neděle po sv. Trojici@Nejste již tedy cizinci a přistěhovalci, máte právo Božího lidu a patříte k Boží rodině.@Efezským 2,19" +
                "@25.07.2021@8. neděle po sv. Trojici@I vy jste kdysi byli tmou, ale nyní vás Pán učinil světlem.@Efezským 5,8b.9" +
                "@01.08.2021@9. neděle po sv. Trojici@Komu bylo mnoho dáno, od toho se mnoho očekává, a komu mnoho svěřili, od toho budou žádat tím více.@Lukáš 12,48b" +
                "@08.08.2021@10. neděle po sv. Trojici@Blahoslavený národ, kteréhož Hospodin jest Bohem jeho, lid ten, kterýž sobě on vyvolil za dědictví.@Žalm 33,12 K" +
                "@15.08.2021@11. neděle po sv. Trojici@Bůh se staví proti pyšným, ale pokorným dává milost.@1.Petrova 5,5b" +
                "@22.08.2021@12. neděle po sv. Trojici@Nalomenou třtinu nedolomí, nezhasí knot doutnající.@Izaiáš 42,3a" +
                "@29.08.2021@13. neděle po sv. Trojici@Cokoliv jste učinili jednomu z těchto mých nepatrných bratří, mně jste učinili.@Matouš 25,40b" +
                "@05.09.2021@14. neděle po sv. Trojici@Dobrořeč, má duše, Hospodinu, nezapomínej na žádné jeho dobrodiní!@Žalm 103,2" +
                "@12.09.2021@15. neděle po sv. Trojici@Všechnu svou starost vložte na něj, neboť mu na vás záleží.@1.Petrova 5,7" +
                "@19.09.2021@16. neděle po sv. Trojici@Ježíš Kristus zlomil moc smrti a zjevil nepomíjející život v evangeliu.@2.Timoteovi 1,10b" +
                "@26.09.2021@17. neděle po sv. Trojici@Vítězství, které přemohlo svět, je naše víra.@1.Janova 5,4c" +
                "@03.10.2021@Den díkůvzdání za úrodu@Oči všech s nadějí vzhlížejí k tobě a ty jim v pravý čas dáváš pokrm.@Žalm 145,15" +
                "@10.10.2021@19. neděle po sv. Trojici@Uzdrav mne, Hospodine, a zdráv budu; vysvoboď mne, a vysvobozen budu.@Jeremiáš 17,14 K" +
                "@17.10.2021@20. neděle po sv. Trojici@Člověče, bylo ti oznámeno, co je dobré a co od tebe Hospodin žádá: jen to, abys zachovával právo, miloval milosrdenství a pokorně chodil se svým Bohem.@Micheáš 6,8" +
                "@24.10.2021@21. neděle po sv. Trojici@Nedej se přemoci zlem, ale přemáhej zlo dobrem.@Římanům 12,21" +
                "@31.10.2021@22. neděle po sv. Trojici@Nikdo nemůže položit jiný základ než ten, který už je položen, a to je Ježíš Kristus.@1.Korintským 3,11" +
                "@07.11.2021@23. neděle po sv. Trojici@Blahoslavení pokojní, nebo oni synové Boží slouti budou.@Matouš 5,9 K" +
                "@14.11.2021@24. neděle po sv. Trojici@Všichni se musíme ukázat před soudným stolcem Kristovým.@2.Korintským 5,10a" +
                "@21.11.2021@Poslední neděle církevního roku@Buďte připraveni a vaše lampy ať hoří.@Lukáš 12,35" +
                "@28.11.2021@1. neděle adventní@Hle, přichází k tobě tvůj král, spravedlivý a zachránce.@Zachariáš 9,9b" +
                "@05.12.2021@2. neděle adventní@Napřimte se a zvedněte hlavy, neboť vaše vykoupení je blízko.@Lukáš 21,28" +
                "@12.12.2021@3. neděle adventní@Připravte cestu Hospodinu! Panovník Hospodin přichází s mocí.@Izaiáš 40,3.10" +
                "@19.12.2021@4. neděle adventní@Radujte se v Pánu vždycky, znovu říkám, radujte se! Pán je blízko.@Filipským 4,4.5b" +
                "@26.12.2021@2. svátek vánoční@Slovo se stalo tělem a přebývalo mezi námi. Spatřili jsme jeho slávu.@Jan 1,14a"
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
