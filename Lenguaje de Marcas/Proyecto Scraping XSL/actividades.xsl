<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:output method="xml" indent="yes" encoding="UTF-8"/>

<xsl:template match="/">
    <listado_salas>
        <xsl:for-each select="actividades/actividad">
            <sala>
                <nombre_sala>
                    <xsl:value-of select="sala"/>
                </nombre_sala>
                <nombre_actividad>
                    <xsl:value-of select="nombre"/>
                </nombre_actividad>
                <entrenador>
                    <xsl:value-of select="entrenador"/>
                </entrenador>
                <fecha_hora>
                    <xsl:value-of select="concat(fecha, ' ', hora)"/>
                </fecha_hora>
                <capacidad_maxima>
                    <xsl:value-of select="capacidad"/>
                </capacidad_maxima>
            </sala>
        </xsl:for-each>
    </listado_salas>
</xsl:template>

</xsl:stylesheet>
