<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
   <p><b>Illness:</b></p>
   <table border="1">
      <th>Condition</th>

      <xsl:for-each select="Illness">
      <xsl:sort select="//condition" />
      <tr>
		  <td><i><xsl:value-of select="//condition" /></i></td>		  
	  </tr>
      </xsl:for-each>
   </table>
   </html>
</xsl:template>

</xsl:stylesheet>