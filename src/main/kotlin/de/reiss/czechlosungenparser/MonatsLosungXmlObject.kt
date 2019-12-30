package de.reiss.czechlosungenparser

/**
 * <MonatsLosungen> <Datum>2011-01-01</Datum> <Losungstext>Und Gott schuf den
 * Menschen zu seinem Bilde, zum Bilde Gottes schuf er ihn; und schuf sie als
 * Mann und Frau. </Losungstext> <Losungsvers>Genesis 1,27</Losungsvers>
</MonatsLosungen> *
 */
class MonatsLosungXmlObject {
    var datum: String? = null
    var losungstext: String? = null
    var losungsvers: String? = null
    override fun toString(): String {
        return ("<MonatsLosungen> " + "\n" + "<Datum>" + datum + "</Datum>"
                + "\n" + " <Losungstext>" + losungstext + "</Losungstext>"
                + "\n" + " <Losungsvers>" + losungsvers + "</Losungsvers>"
                + "\n" + " </MonatsLosungen>")
    }
}
