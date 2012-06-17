<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/solutions/solution">
        <html>
            <head>
                <style type="text/css">
                    body {
                    font-family: sans-serif;
                    }
                    table {
                    border-collapse: collapse;
                    }
                    td, th {
                    border: 1px solid black;
                    padding: 2px 5px;
                    background-color: #eee;
                    }
                    th {
                    background-color: #ddd;
                    }
                </style>
            </head>
            <body>
                <table>
                    <tr>
                        <th>house</th>
                        <xsl:for-each select="house[1]/@*[name()!='position']">
                            <th>
                                <xsl:value-of select="name()"/>
                            </th>
                        </xsl:for-each>
                    </tr>
                    <xsl:for-each select="house">
                        <tr>
                            <th>
                                <xsl:value-of select="@position"/>
                            </th>
                            <xsl:for-each select="@*[name()!='position']">
                                <td>
                                    <xsl:value-of select="."/>
                                </td>
                            </xsl:for-each>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>