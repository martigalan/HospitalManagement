<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
   <p><b><xsl:value-of select="Hospital/@name" /></b></p>
   <p><b>C<xsl:value-of select="//location" /></b></p>
   <p><b>Machines/treatments inside the hospital:</b></p>
   <table border="1">
      <xsl:for-each select="Hospital/Machines/Machine">
      <xsl:sort select="@name" />        
            <tr>
				
      <th>Name</th>
            <td><i><xsl:value-of select="@name" /></i></td>           
            </tr>
      </xsl:for-each>
   </table>
   </html>
</xsl:template>

</xsl:stylesheet>