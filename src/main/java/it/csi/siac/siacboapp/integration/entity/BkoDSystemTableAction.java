/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bko_d_system_table_action")
public class BkoDSystemTableAction extends BkoBase {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2568576277603495153L;

	@Id
	@Column(name = "sa_id")
	private Integer id;

	@Column(name = "sa_code")
	private String code;

	@Column(name = "sa_desc")
	private String description;

	public BkoDSystemTableAction() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}