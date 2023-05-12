<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
   <p><b><xsl:value-of select="//name" /></b></p>
   <p><b><xsl:value-of select="//surname" /></b></p>
   <p><b>Patients:</b></p>
   <table border="1">
      <th>Patient</th>
      <th>Date of Birth</th>
      <th>Hospital</th>
      <xsl:for-each select="Patient/Hospital">
      </xsl:for-each>
   </table>
   </html>
</xsl:template>

</xsl:stylesheet>