package de.reiss.czechlosungenparser

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object WochenLosungen {

    @JvmStatic
    fun main(args: Array<String>) {
        val inCzech = ""+
                "@02.01.2022@1. neděle po Vánocích@Spatřili jsme jeho slávu, slávu, jakou má od Otce jednorozený Syn, plný milosti a pravdy.@Jan 1,14b"+
        "@09.01.2022@1. neděle po Zjevení Páně@Ti, kdo se dají vést Duchem Božím, jsou synové Boží.@Římanům 8,14"+
        "@16.01.2022@2. neděle po Zjevení Páně@Z jeho plnosti jsme byli obdarováni my všichni milostí za milostí.@Jan 1,16"+
        "@23.01.2022@3. neděle po Zjevení Páně@Přijdou od východu i západu, od severu i jihu, a budou stolovat v Božím království.@Lukáš 13,29"+
        "@30.01.2022@Poslední neděle po Zjevení Páně@Nad tebou vzejde Hospodin a ukáže se nad tebou jeho sláva.@Izaiáš 60,2b"+
        "@06.02.2022@4. neděle před postní dobou@Pojďte a vizte Boží skutky! Úžasné je, co lidem činí.@Žalm 66,5 R"+
        "@13.02.2022@3. neděle před postní dobou@Ne pro své spravedlivé činy ti předkládáme své prosby o smilování, ale pro tvé velké slitování.@Daniel 9,18b"+
        "@20.02.2022@2. neděle před postní dobou@Uslyšíte-li dnes jeho hlas, nezatvrzujte svoje srdce.@Židům 3,15 P"+
        "@27.02.2022@Estomihi@Hle, jdeme do Jeruzaléma a na Synu člověka se naplní všechno, co je psáno u proroků.@Lukáš 18,31b"+
        "@06.03.2022@Invocavit@Proto se zjevil Syn Boží, aby zmařil činy ďáblovy.@1.Janova 3,8b"+
        "@13.03.2022@Reminiscere@Bůh prokazuje svou lásku k nám tím, že Kristus za nás zemřel, když jsme ještě byli hříšní.@Římanům 5,8"+
        "@20.03.2022@Oculi@Kdo položí ruku na pluh a ohlíží se zpět, není způsobilý pro království Boží.@Lukáš 9,62"+
        "@27.03.2022@Laetare@Jestliže pšeničné zrno nepadne do země a nezemře, zůstane samo. Zemře-li však, vydá mnohý užitek.@Jan 12,24"+
        "@03.04.2022@Judica@Syn člověka nepřišel, aby si dal sloužit, ale aby sloužil a dal svůj život jako výkupné za mnohé.@Matouš 20,28"+
        "@10.04.2022@Květná neděle@Jako Mojžíš vyvýšil hada na poušti, tak musí být vyvýšen Syn člověka.@Jan 3,14b.15"+
        "@17.04.2022@Velikonoční neděle@Byl jsem mrtev – a hle, živ jsem na věky věků. Mám klíče od smrti i hrobu.@Zjevení 1,18"+
        "@24.04.2022@Quasimodogeniti@Veleben buď Bůh a Otec Pána našeho Ježíše Krista, neboť nám ze svého velikého milosrdenství dal vzkříšením Ježíše Krista nově se narodit k živé naději.@1.Petrova 1,3"+
        "@01.05.2022@Misericordias Domini@Já jsem ten pastýř dobrý. Dobrý pastýř duši svou pokládá za ovce.@Jan 10,11a.27-28a K"+
        "@08.05.2022@Jubilate@Kdo je v Kristu, je nové stvoření. Co je staré, pominulo, hle, je tu nové!@2.Korintským 5,17"+
        "@15.05.2022@Cantate@Zpívejte Hospodinu píseň novou, neboť učinil podivuhodné věci!@Žalm 98,1"+
        "@22.05.2022@Rogate@Požehnán buď Bůh, že mou modlitbu nezamítl a své milosrdenství mi neodepřel.@Žalm 66,20"+
        "@29.05.2022@Exaudi@Až budu vyvýšen ze země, přitáhnu všecky k sobě.@Jan 12,32"+
        "@05.06.2022@Neděle svatodušní@Ne mocí ani silou, nýbrž mým duchem, praví Hospodin zástupů.@Zachariáš 4,6b"+
        "@12.06.2022@Neděle svaté Trojice@Milost Pána Jezukrista a láska Boží a účastenství Ducha svatého budiž se všemi vámi.@2.Korintským 13,13 K"+
        "@19.06.2022@1. neděle po sv. Trojici@Kdo slyší vás, slyší mne, a kdo odmítá vás, odmítá mne.@Lukáš 10,16a"+
        "@26.06.2022@2. neděle po sv. Trojici@Pojďte ke mně všichni, kdo se namáháte a jste obtíženi břemeny, a já vám dám odpočinout.@Matouš 11,28"+
        "@03.07.2022@3. neděle po sv. Trojici@Syn člověka přišel hledat a zachránit, co je ztraceno.@Lukáš 19,10 P"+
        "@10.07.2022@4. neděle po sv. Trojici@Jedni druhých břemena neste a tak plňte zákon Kristův.@Galatským 6,2 K"+
        "@17.07.2022@5. neděle po sv. Trojici@Nebo milostí spaseni jste skrze víru, a to ne sami z sebe: dar je to Boží.@Efezským 2,8 K"+
        "@24.07.2022@6. neděle po sv. Trojici@Toto praví Hospodin, tvůj stvořitel, Jákobe, tvůrce tvůj, Izraeli: Neboj se, já jsem tě vykoupil, povolal jsem tě tvým jménem, jsi můj.@Izaiáš 43,1"+
        "@31.07.2022@7. neděle po sv. Trojici@Nejste již tedy cizinci a přistěhovalci, máte právo Božího lidu a patříte k Boží rodině.@Efezským 2,19"+
        "@07.08.2022@8. neděle po sv. Trojici@Žijte jako děti světla – ovocem světla je vždy dobrota, spravedlnost a pravda.@Efezským 5,9"+
        "@14.08.2022@9. neděle po sv. Trojici@Komu bylo mnoho dáno, od toho se mnoho očekává, a komu mnoho svěřili, od toho budou žádat tím více.@Lukáš 12,48b"+
        "@21.08.2022@10. neděle po sv. Trojici@Blahoslavený národ, kteréhož Hospodin jest Bohem jeho, lid ten, kterýž sobě on vyvolil za dědictví.@Žalm 33,12 K"+
        "@28.08.2022@11. neděle po sv. Trojici@Bůh se staví proti pyšným, ale pokorným dává milost.@1.Petrova 5,5b"+
        "@04.09.2022@12. neděle po sv. Trojici@Třtiny nalomené nedolomí, a lnu kouřícího se neuhasí.@Izaiáš 42,3a K"+
        "@11.09.2022@13. neděle po sv. Trojici@Cokoliv jste učinili jednomu z těchto mých nepatrných bratří, mně jste učinili.@Matouš 25,40"+
        "@18.09.2022@14. neděle po sv. Trojici@Žehnej má duše Hospodinu a nezapomeň na žádné jeho dobrodiní!@Žalm 103,2 Z"+
        "@25.09.2022@15. neděle po sv. Trojici@Všechnu svou starost vložte na něj, neboť mu na vás záleží.@1.Petrova 5,7"+
        "@02.10.2022@Den díkůvzdání za úrodu@Oči všech s nadějí vzhlížejí k tobě a ty jim v pravý čas dáváš pokrm.@Žalm 145,15"+
        "@09.10.2022@17. neděle po sv. Trojici@Vítězství, které přemohlo svět, je naše víra.@1.Janova 5,4c"+
        "@16.10.2022@18. neděle po sv. Trojici@A tak máme od něho toto přikázání: Kdo miluje Boha, ať miluje i svého bratra.@1.Janova 4,21"+
        "@23.10.2022@19. neděle po sv. Trojici@Uzdrav mne, Hospodine, a zdráv budu; vysvoboď mne, a vysvobozen budu, ty jsi zajisté chvála má.@Jeremiáš 17,14 K"+
        "@30.10.2022@20. neděle po sv. Trojici@Člověče, bylo ti oznámeno, co je dobré a co od tebe Hospodin žádá: jen to, abys zachovával právo, miloval milosrdenství a pokorně chodil se svým Bohem.@Micheáš 6,8"+
        "@06.11.2022@21. neděle po sv. Trojici@Blahoslavení pokojní, nebo oni synové Boží slouti budou.@Matouš 5,9 K"+
        "@13.11.2022@22. neděle po sv. Trojici@Vždyť se všichni musíme ukázat před soudným stolcem Kristovým.@2.Korintským 5,10a"+
        "@20.11.2022@Poslední neděle církevního roku@Buďte připraveni a vaše lampy ať hoří.@Lukáš 12,35"+
        "@27.11.2022@1. neděle adventní@Aj, král tvůj přijde tobě spravedlivý a spasení plný.@Zachariáš 9,9b K"+
        "@04.12.2022@2. neděle adventní@Pohleďtež a pozdvihnětež hlav vašich, protože se přibližuje vykoupení vaše.@Lukáš 21,28 K"+
        "@11.12.2022@3. neděle adventní@Připravte na poušti cestu Hospodinu! Panovník Hospodin přichází s mocí.@Izaiáš 40,3.10"+
        "@18.12.2022@4. neděle adventní@Radujte se v Pánu vždycky, znovu říkám, radujte se!  Pán je blízko.@Filipským 4,4.5b"+
        "@25.12.2022@1. svátek vánoční@Slovo se stalo tělem a přebývalo mezi námi. Spatřili jsme jeho slávu.@Jan 1,14a"

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
