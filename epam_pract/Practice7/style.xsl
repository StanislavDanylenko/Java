<?xml version="1.0" encoding="UTF-8"?><xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"><xsl:template match="/">
    <html>
        <body>
            <h2>Planes</h2>
            <table border="1">

                <xsl:for-each select="Planes/Plane">
                    <tr>
                        <td colspan="2" align="center">Plane</td>
                    </tr>
                    <tr bgcolor="#9acd32">
                        <th align="left" width="100">Title</th>
                        <th align="left" width="100">Value</th>
                    </tr>
                    <tr>
                        <td>Model</td>
                        <td><xsl:value-of select="Model"/></td>
                    </tr>
                    <tr>
                        <td>Origin</td>
                        <td><xsl:value-of select="Origin"/></td>
                    </tr>
                    <tr>
                        <td>Type</td>
                        <td><xsl:value-of select="Chars/Type"/></td>
                    </tr>
                    <tr>
                        <td>Places</td>
                        <td><xsl:value-of select="Chars/Places"/></td>
                    </tr>
                    <tr>
                        <td>Radar</td>
                        <td><xsl:value-of select="Chars/Radar"/></td>
                    </tr>
                    <tr>
                        <td>Length</td>
                        <td><xsl:value-of select="Parameters/Length"/></td>
                    </tr>
                    <tr>
                        <td>Width</td>
                        <td><xsl:value-of select="Parameters/Width"/></td>
                    </tr>
                    <tr>
                        <td>Height</td>
                        <td><xsl:value-of select="Parameters/Height"/></td>
                    </tr>
                    <tr>
                        <td>Price</td>
                        <td><xsl:value-of select="Price"/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                    </tr>
                </xsl:for-each>
            </table>
        </body>
    </html>
</xsl:template></xsl:stylesheet>
