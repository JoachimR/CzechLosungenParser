package de.reiss.czechlosungenparser

import java.text.SimpleDateFormat
import java.util.*

/**
 * <WochenLosungen> <StartDatum>2013-12-15</StartDatum>
 * <EndDatum>2013-12-21</EndDatum> <Sonntag>Advent 3. vasárnapja</Sonntag>
 * <Losungstext>Építsetek utat a pusztában az Úrnak! Az Úr jön
 * hatalommal.</Losungstext> <Losungsvers>Ézs 40,3.10</Losungsvers>
</WochenLosungen> *
 */
class WochenLosungXMlObject : Comparable<WochenLosungXMlObject> {
    var startDatum: Date? = null
    var endDatum: Date? = null
    var sonntag: String? = null
    var losungstext: String? = null
    var losungsvers: String? = null
    override fun toString(): String {
        return ("<WochenLosungen> " + "\n" + "<StartDatum>"
                + convert(startDatum) + "</StartDatum>"
                + "\n"
                + "<EndDatum>"
                + convert(endDatum) // + "xxx"
                + "</EndDatum> " + "\n" + "<Sonntag>" + sonntag + "</Sonntag>"
                + "\n" + " <Losungstext>" + losungstext + "</Losungstext>"
                + "\n" + " <Losungsvers>" + losungsvers + "</Losungsvers>"
                + "\n" + " </WochenLosungen>")
    }

    private fun convert(d: Date?): String {
        return SimpleDateFormat("yyyy-MM-dd").format(d).toString()
    }

    override fun compareTo(o: WochenLosungXMlObject): Int {
        if (startDatum!!.before(o.startDatum)) {
            return -1
        }
        return if (startDatum!!.after(o.startDatum)) {
            1
        } else 0
    }
}
