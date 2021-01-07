<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <body>
                <div><a href="/RESTapi/workers/xml/">Все рабочие</a></div>
                <H1>Станки</H1>
                <table border="1">
                    <tr bgcolot="#9acd32">
                        <th>№</th>
                        <th>Тип</th>
                    </tr>
                    <xsl:for-each select="MachineList/machines/machines">
                        <tr>
                            <td><xsl:value-of select="id"/></td>
                            <td>
                                <xsl:variable name="id">
                                    <xsl:value-of select="id" />
                                </xsl:variable>
                                <a href="/RESTapi/machines/xml/{$id}/">
                                    <xsl:value-of select="type"/>
                                </a>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>