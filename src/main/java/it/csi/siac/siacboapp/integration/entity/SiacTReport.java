/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the siac_t_account database table.
 * 
 */
@Entity
@Table(name = "siac_t_report")
public class SiacTReport extends SiacTEnteBase {

	private static final long serialVersionUID = 2493900246434455703L;

	@Id
	@SequenceGenerator(name = "siac_t_report_rep_id_seq_GENERATOR", allocationSize = 1, sequenceName = "siac_t_report_rep_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "siac_t_report_rep_id_seq_GENERATOR")
	@Column(name = "rep_id")
	private Integer uid;

	@Column(name = "rep_codice")
	private String codice;

	@Column(name = "rep_desc")
	private String descrizione;

	@Column(name = "rep_birt_codice")
	private String codiceBirt;

	@Column(name = "rep_ordina_elenco_variab")
	private String ordinaElenco; // SIAC-7192 ordinamento nuova colonna

	@OneToMany(mappedBy = "report", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	private Set<SiacRReportImporti> importi;

	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
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

	public String getCodiceBirt() {
		return codiceBirt;
	}

	public void setCodiceBirt(String codiceBirt) {
		this.codiceBirt = codiceBirt;
	}

	public Set<SiacRReportImporti> getImporti() {
		return importi;
	}

	public void setImporti(Set<SiacRReportImporti> importi) {
		this.importi = importi;
	}

	public String getOrdinaElenco() {
		return ordinaElenco;
	}

	public void setOrdinaElenco(String ordinaElenco) {
		this.ordinaElenco = ordinaElenco;
	}

}