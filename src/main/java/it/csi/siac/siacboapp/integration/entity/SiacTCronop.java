/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "siac_t_cronop")
public class SiacTCronop extends SiacTEnteBase {

	private static final long serialVersionUID = 8406172585563813692L;

	@Id
	@SequenceGenerator(name = "SIAC_T_CRONOP_CRONOPID_GENERATOR", allocationSize = 1, sequenceName = "SIAC_T_CRONOP_CRONOP_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIAC_T_CRONOP_CRONOPID_GENERATOR")
	@Column(name = "cronop_id")
	private Integer uid;

	@Column(name = "cronop_code")
	private String codice;

	@Column(name = "cronop_desc")
	private String descrizione;

	@ManyToOne
	@JoinColumn(name = "programma_id")
	private SiacTProgramma programma;

	@ManyToOne
	@JoinColumn(name = "bil_id")
	private SiacTBil bilancio;
	


	@Override
	public Integer getUid() {
		return uid;
	}

	public SiacTProgramma getProgramma() {
		return programma;
	}

	public void setProgramma(SiacTProgramma programma) {
		this.programma = programma;
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

	public SiacTBil getBilancio() {
		return bilancio;
	}

	public void setBilancio(SiacTBil bilancio) {
		this.bilancio = bilancio;
	}

}