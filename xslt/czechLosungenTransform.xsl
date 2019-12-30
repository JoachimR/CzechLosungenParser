<xsl:stylesheet version="2.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        >

    <xsl:output method="xml" indent="yes"/>

    <xsl:strip-space elements="*"/>

    <xsl:template match="LOSFILE">
        <xsl:element name="FreeXml" namespace="http://tempuri.org/FreeXml.xsd">
            <xsl:apply-templates select="LOSUNG"/>
        </xsl:element>
    </xsl:template>

    <xsl:template match="LOSUNG">


        <xsl:element name="Losungen">


            <xsl:element name="Datum">2020-<xsl:if test="string-length(@m) &lt; 2">0</xsl:if><xsl:value-of select="@m"/>-<xsl:if test="string-length(@d) &lt; 2">0</xsl:if><xsl:value-of select="@d"/></xsl:element>

            <xsl:element name="Sonntag">
                <xsl:value-of select="Sonntag"/>
            </xsl:element>

            <xsl:apply-templates select="TL"/>
            <xsl:apply-templates select="OT"/>
            <xsl:apply-templates select="NT"/>
        </xsl:element>
    </xsl:template>

    <xsl:template match="OT">
        <xsl:element name="Losungstext">
            <xsl:value-of select="IL"/>
            <xsl:for-each select="L">
                <xsl:value-of select="."/>
                <xsl:text> </xsl:text>
            </xsl:for-each>
        </xsl:element>
        <xsl:element name="Losungsvers">
            <xsl:value-of select="SL"/>
        </xsl:element>
    </xsl:template>

    <xsl:template match="NT">
        <xsl:element name="Lehrtext">
            <xsl:value-of select="IL"/>
            <xsl:text> </xsl:text>
            <xsl:for-each select="L">
                <xsl:value-of select="."/>
                <xsl:text> </xsl:text>
            </xsl:for-each>
        </xsl:element>
        <xsl:element name="Lehrtextvers">
            <xsl:value-of select="SL"/>
        </xsl:element>
    </xsl:template>

    <xsl:template match="TL">
        <xsl:element name="Wtag">
            <xsl:choose>
                <xsl:when test="contains(.,'Neděle')">Neděle</xsl:when>
                <xsl:when test="contains(.,'Pondělí')">Pondělí</xsl:when>
                <xsl:when test="contains(.,'Úterý')">Úterý</xsl:when>
                <xsl:when test="contains(.,'Středa')">Středa</xsl:when>
                <xsl:when test="contains(.,'Čtvrtek')">Čtvrtek</xsl:when>
                <xsl:when test="contains(.,'Pátek')">Pátek</xsl:when>
                <xsl:when test="contains(.,'Sobota')">Sobota</xsl:when>
                <xsl:otherwise/>
            </xsl:choose>
        </xsl:element>
    </xsl:template>

</xsl:stylesheet>
