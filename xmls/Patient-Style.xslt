<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
   <p><b><xsl:value-of select="Patient/@name" /></b></p>
   <p><b><xsl:value-of select="Patient/@surname" /></b></p>
   <p><b>C<xsl:value-of select="//dob" /></b></p>
   <p><b>Hospital:</b></p>
   <table border="1">
      <th>Name</th>
      <xsl:for-each select="Patient/Hospital">
      <xsl:sort select="@name" />        
            <tr>
            <td><i><xsl:value-of select="@name" /></i></td>           
            </tr>
      </xsl:for-each>
   </table>
   </html>
</xsl:template>

</xsl:stylesheet>