<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
<<<<<<< HEAD
   <p><b><xsl:value-of select="Machine/@name" /></b></p>
   <p><b>Hospital:</b></p>
=======
   <p><b>Machine:</b></p>
>>>>>>> branch 'master' of https://github.com/martigalan/HospitalManagement
   <table border="1">
      <th>Name</th>
<<<<<<< HEAD
      <xsl:for-each select="Machine/Hospitals/Hospital">
      <xsl:sort select="@name" />
=======

      <xsl:for-each select="Machine">
      <xsl:sort select="//name" />
      <tr>
		  <td><i><xsl:value-of select="//name" /></i></td>		  
	  </tr>
>>>>>>> branch 'master' of https://github.com/martigalan/HospitalManagement
      </xsl:for-each>
   </table>
   </html>
</xsl:template>

</xsl:stylesheet>