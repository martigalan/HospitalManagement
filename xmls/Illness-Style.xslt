<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
   <p><b><xsl:value-of select="Illness/@condition" /></b></p>
   <p><b>Doctors:</b></p>
   <table border="1">
      <th>Name</th>
      <xsl:for-each select="Illness/Doctors/Doctor">
      <xsl:sort select="@name" />
      <xsl:sort select="@surname" />
      </xsl:for-each>
   </table>
   </html>
</xsl:template>

</xsl:stylesheet>