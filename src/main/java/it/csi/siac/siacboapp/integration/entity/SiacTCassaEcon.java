/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the siac_t_cassa_econ database table.
 * 
 */
@Entity
@Table(name = "siac_t_cassa_econ")
public class SiacTCassaEcon extends SiacTEnteBase {

	private static final long serialVersionUID = 8331574350116052033L;

	@Id
	@SequenceGenerator(name = "SEQ_T_CASSA_ECON", allocationSize = 1, sequenceName = "siac_t_cassa_econ_cassaecon_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_T_CASSA_ECON")
	@Column(name = "cassaecon_id")
	private Integer uid;

	@Column(name = "cassaecon_code")
	private String codice;

	@Column(name = "cassaecon_desc")
	private String descrizione;

	@OneToMany(mappedBy = "cassaEconomale")
	private List<SiacRAccountCassaEcon> siacRAccountCassaEcons;
	
	@OneToMany(mappedBy = "cassaEconomale")
	private List<SiacRCassaEconBil> siacRCassaEconBils;

	public SiacTCassaEcon(Integer uid) {
		this();
		setUid(uid);
	}

	public SiacTCassaEcon() {
		super();
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<SiacRAccountCassaEcon> getSiacRAccountCassaEcons() {
		return siacRAccountCassaEcons;
	}

	public void setSiacRAccountCassaEcons(List<SiacRAccountCassaEcon> siacRAccountCassaEcons) {
		this.siacRAccountCassaEcons = siacRAccountCassaEcons;
	}

	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public List<SiacRCassaEconBil> getSiacRCassaEconBils() {
		return siacRCassaEconBils;
	}

	public void setSiacRCassaEconBils(List<SiacRCassaEconBil> siacRCassaEconBils) {
		this.siacRCassaEconBils = siacRCassaEconBils;
	}
}