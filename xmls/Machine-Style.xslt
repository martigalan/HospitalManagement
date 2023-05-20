<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
   <p><b><xsl:value-of select="Machine/@name" /></b></p>
   <p><b>Hospital:</b></p>
   <table border="1">
      <th>Name</th>
      <xsl:for-each select="Machine/Hospitals/Hospital">
      <xsl:sort select="@name" />
      </xsl:for-each>
   </table>
   </html>
</xsl:template>

</xsl:stylesheet>