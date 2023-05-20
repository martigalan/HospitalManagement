<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
   <p><b>Machine:</b></p>
   <table border="1">
      <th>Name</th>

      <xsl:for-each select="Machine">
      <xsl:sort select="//name" />
      <tr>
		  <td><i><xsl:value-of select="//name" /></i></td>		  
	  </tr>
      </xsl:for-each>
   </table>
   </html>
</xsl:template>

</xsl:stylesheet>