<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <body>
                <div><a href="/RESTapi/workers/xml/">Все рабочие</a></div>
                <h1>Имя: <xsl:value-of select="Worker/fullName"/></h1>
                <div>Персоральный №: <xsl:value-of select="Worker/personnelNumber"/></div>
                <div>Категория: <xsl:value-of select="Worker/category"/></div>
                <xsl:variable name="id">
                    <xsl:value-of select="Worker/machine/id" />
                </xsl:variable>
                <div>Станок: <a href="/RESTapi/machines/xml/{$id}/"><xsl:value-of select="Worker/machine/type"/></a></div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>