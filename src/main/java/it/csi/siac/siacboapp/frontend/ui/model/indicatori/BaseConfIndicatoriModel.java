/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.indicatori;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import it.csi.siac.siaccommonapp.model.GenericModel;

public abstract class BaseConfIndicatoriModel extends GenericModel {
	
	private static final long serialVersionUID = -7914818355181707061L;

	private String serializedValues;
	private Integer anno;
	private Integer numeroAnniBilancioPrevisone;

	public Map<Integer, List<String>> deserializeValues() {
		Map<Integer, List<String>> tmpMap = new HashMap<Integer, List<String>>();
		
		for (String ind : StringUtils.split(serializedValues, "/")) {
			String[] tmp = StringUtils.split(ind, ":");
			
			Integer uid = Integer.parseInt(tmp[0]); 
			
			if (!tmpMap.containsKey(uid)) {
				tmpMap.put(uid, new ArrayList<String>());
			}
			
			tmpMap.get(uid).add(tmp[1]);
		}

		return tmpMap;
	}

	public Integer getAnno()
	{
		return anno;
	}

	public void setAnno(Integer anno)
	{
		this.anno = anno;
	}

	public String getSerializedValues()
	{
		return serializedValues;
	}

	public void setSerializedValues(String serializedValues)
	{
		this.serializedValues = serializedValues;
	}

	public Integer getNumeroAnniBilancioPrevisone()
	{
		return numeroAnniBilancioPrevisone;
	}

	public void setNumeroAnniBilancioPrevisone(Integer numeroAnniBilancioPrevisone)
	{
		this.numeroAnniBilancioPrevisone = numeroAnniBilancioPrevisone;
	}
}
