/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;

@Entity
public class SiacDecodifica extends SiacTEnteBase {
	private static final long serialVersionUID = 5561216109364478049L;

	@Id
	@Column
	private Integer uid;

	@Transient
	private BkoTSystemTable tabella;

	@Transient
	private Map<String, List<SiacDecodifica>> foreignKeyValues = new HashMap<String, List<SiacDecodifica>>();

	@Transient
	private Map<String, Object> values = new HashMap<String, Object>();

	@Override
	public Integer getUid() {
		return this.uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Map<String, List<SiacDecodifica>> getForeignKeyValues() {
		return foreignKeyValues;
	}

	public void setForeignKeyValues(Map<String, List<SiacDecodifica>> foreignKeyValues) {
		this.foreignKeyValues = foreignKeyValues;
	}

	public BkoTSystemTable getTabella() {
		return tabella;
	}

	public void setTabella(BkoTSystemTable tabella) {
		this.tabella = tabella;
	}

	public Map<String, Object> getValues() {
		return values;
	}

	public void setValues(Map<String, Object> values) {
		this.values = values;
	}

	public Object getValue(String fieldName) {
		return values.get(fieldName);
	}

	public void setValue(String fieldName, Object value) {
		values.put(fieldName, value);
	}

	public String getDescrizione() {
		return String.format("[%s] %s", String.valueOf(this.uid), StringUtils.join(values.values(), " | "));
	}
}