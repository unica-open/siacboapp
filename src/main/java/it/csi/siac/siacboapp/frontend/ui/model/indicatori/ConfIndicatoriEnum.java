/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.indicatori;

public enum ConfIndicatoriEnum
{
	ENTRATA("SiacTConfIndicatoriEntrata", 
			"importoAccertamentoAnnoPrec2", 
			"importoAccertamentoAnnoPrec1", 
			"importoAccertamentoAnnoPrec",
			"importoRiscossioneAnnoPrec2",
			"importoRiscossioneAnnoPrec1",
			"importoRiscossioneAnnoPrec"),
	
	SPESA("SiacTConfIndicatoriSpesa",
			"importoFpvAnnoPrec2",
			"importoFpvAnnoPrec1",
			"importoFpvAnnoPrec",
			"importoImpegniAnnoPrec2",
			"importoImpegniAnnoPrec1",
			"importoImpegniAnnoPrec",
			"importoPagCompAnnoPrec2",
			"importoPagCompAnnoPrec1",
			"importoPagCompAnnoPrec",
			"importoPagResAnnoPrec2",
			"importoPagResAnnoPrec1",
			"importoPagResAnnoPrec",
			"importoResDefAnnoPrec2",
			"importoResDefAnnoPrec1",
			"importoResDefAnnoPrec"),
	
	SINT("SiacTConfIndicatoriSint", 
			"valoreAnno", 
			"valoreAnno1", 
			"valoreAnno2",
			"valoreTotMiss13Anno",
			"valoreTotMiss13Anno1",
			"valoreTotMiss13Anno2",
			"valoreTutteSpeseAnno",
			"valoreTutteSpeseAnno1",
			"valoreTutteSpeseAnno2")
	;
	
	
	private String entityName;
	private String[] valueFields;

	private ConfIndicatoriEnum(String entityName, String... valueFields) {
		this.setEntityName(entityName);
		this.setValueFields(valueFields);
	}

	public String getEntityName()
	{
		return entityName;
	}

	public void setEntityName(String entityName)
	{
		this.entityName = entityName;
	}

	public String[] getValueFields()
	{
		return valueFields;
	}

	public void setValueFields(String[] valueFields)
	{
		this.valueFields = valueFields;
	}

}
