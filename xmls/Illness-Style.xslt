<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
<<<<<<< HEAD
   <p><b><xsl:value-of select="Illness/@condition" /></b></p>
   <p><b>Doctors:</b></p>
=======
   <p><b>Illness:</b></p>
>>>>>>> branch 'master' of https://github.com/martigalan/HospitalManagement
   <table border="1">
<<<<<<< HEAD
      <th>Name</th>
      <xsl:for-each select="Illness/Doctors/Doctor">
      <xsl:sort select="@name" />
      <xsl:sort select="@surname" />
=======
      <th>Condition</th>

      <xsl:for-each select="Illness">
      <xsl:sort select="//condition" />
      <tr>
		  <td><i><xsl:value-of select="//condition" /></i></td>		  
	  </tr>
>>>>>>> branch 'master' of https://github.com/martigalan/HospitalManagement
      </xsl:for-each>
   </table>
   </html>
</xsl:template>

</xsl:stylesheet>