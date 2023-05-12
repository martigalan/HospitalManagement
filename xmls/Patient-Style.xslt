<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
   <p><b><xsl:value-of select="Patient/@name" /></b></p>
   <p><b><xsl:value-of select="Patient/@surname" /></b></p>
   <p><b>C<xsl:value-of select="//date of birth" /></b></p>
   <p><b>C<xsl:value-of select="//hospital" /></b></p>
   <p><b>Hpospitals available:</b></p>
   <table border="1">
      <th>Name</th>
      <xsl:for-each select="Patient/Hospitals/Hospital">
      <xsl:sort select="@name" />
       <xsl:sort select="//location" />
      </xsl:for-each>
   </table>
   </html>
</xsl:template>

</xsl:stylesheet>