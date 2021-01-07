<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <body>
                <div><a href="/RESTapi/machines/xml/">Все станки</a></div>
                <H1>Рабочие</H1>
                <table border="1">
                    <tr bgcolot="#9acd32">
                        <th>Персональный №</th>
                        <th>Полное имя</th>
                        <th>Категория</th>
                        <th>Тип оборудования</th>

                    </tr>
                    <xsl:for-each select="WorkerList/workers/workers">
                        <tr>
                            <td><xsl:value-of select="personnelNumber"/></td>
                            <td>
                                <xsl:variable name="id">
                                    <xsl:value-of select="personnelNumber" />
                                </xsl:variable>
                                <a href="/RESTapi/workers/xml/{$id}/">
                                    <xsl:value-of select="fullName"/>
                                </a>
                            </td>
                            <td><xsl:value-of select="category"/></td>
                            <td>
                                <xsl:variable name="id">
                                    <xsl:value-of select="machine/id" />
                                </xsl:variable>
                                <a href="/RESTapi/machines/xml/{$id}/">
                                    <xsl:value-of select="machine/type"/>
                                </a>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>