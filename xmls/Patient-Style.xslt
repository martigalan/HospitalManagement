<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
   <p><b><xsl:value-of select="Patient/@name" /></b></p>
   <p><b><xsl:value-of select="Patient/@surname" /></b></p>
   <table border="1">
      <th>Name</th>
      <th>Surname</th>
      <th>dob</th>
      
      <xsl:for-each select="Patient">
      <xsl:sort select="@name" />        
            <tr>
            <td><i><xsl:value-of select="@name" /></i></td>
            <td><i><xsl:value-of select="@surname" /></i></td>
		    <td><i><xsl:value-of select="//dob" /></i></td>           
            </tr>
      </xsl:for-each>
   </table>
   </html>
</xsl:template>

</xsl:stylesheet>