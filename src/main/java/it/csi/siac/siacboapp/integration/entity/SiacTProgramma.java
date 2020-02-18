/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "siac_t_programma")
public class SiacTProgramma extends SiacTEnteBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6852743421078435042L;

	@Id
	@SequenceGenerator(name = "SIAC_T_PROGRAMMA_PROGRAMMAID_GENERATOR", allocationSize = 1, sequenceName = "SIAC_T_PROGRAMMA_PROGRAMMA_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIAC_T_PROGRAMMA_PROGRAMMAID_GENERATOR")
	@Column(name = "programma_id")
	private Integer uid;

	@Column(name = "programma_code")
	private String codice;

	@Column(name = "programma_desc")
	private String descrizione;

	@OneToMany(mappedBy = "programma")
	private Set<SiacRProgrammaAttr> attributi;

	@OneToMany(mappedBy = "programma")
	@OrderBy("dataCreazione desc")
	private Set<SiacTCronop> cronoprogrammi;

	@OneToMany(mappedBy = "programma")
	private Set<SiacRMovgestTsProgramma> movimentiGestioneProgramma;

	private transient Boolean esisteCronoprogrammaGestione;

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

	public Set<SiacRProgrammaAttr> getAttributi() {
		return attributi;
	}

	public void setAttributi(Set<SiacRProgrammaAttr> attributi) {
		this.attributi = attributi;
	}

	public Set<SiacTCronop> getCronoprogrammi() {
		return cronoprogrammi;
	}

	public void setCronoprogrammi(Set<SiacTCronop> cronoprogrammi) {
		this.cronoprogrammi = cronoprogrammi;
	}

	public Set<SiacRMovgestTsProgramma> getMovimentiGestioneProgramma() {
		return movimentiGestioneProgramma;
	}

	public void setMovimentiGestioneProgramma(
			Set<SiacRMovgestTsProgramma> movimentiGestioneProgramma) {
		this.movimentiGestioneProgramma = movimentiGestioneProgramma;
	}

	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public void initEsisteCronoprogrammaGestione() {
		setEsisteCronoprogrammaGestione(!movimentiGestioneProgramma.isEmpty());
	}

	public Boolean getEsisteCronoprogrammaGestione() {
		if (esisteCronoprogrammaGestione == null)
			initEsisteCronoprogrammaGestione();
		
		return esisteCronoprogrammaGestione ;
	}

	public void setEsisteCronoprogrammaGestione(Boolean esisteCronoprogrammaGestione) {
		this.esisteCronoprogrammaGestione = esisteCronoprogrammaGestione;
	}

}