/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "siac_t_fpv_set_cronop")
public class SiacTFpvSetCronop extends SiacTEnteBase {

	private static final long serialVersionUID = 3554382572272291699L;

	@Id
	@SequenceGenerator(name = "SIAC_T_FPV_SET_CRONOP_ID_GENERATOR", allocationSize = 1, sequenceName = "SIAC_T_FPV_SET_CRONOP_SET_CRONOP_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIAC_T_FPV_SET_CRONOP_ID_GENERATOR")
	@Column(name = "set_cronop_id")
	private Integer uid;

	@Column(name = "set_cronop_code")
	private String codice;

	@Column(name = "set_cronop_desc")
	private String descrizione;

	@ManyToOne
	@JoinColumn(name = "bil_id")
	private SiacTBil bilancio;
	
	@OneToMany(mappedBy="setProgetti", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
	private Set<SiacRFpvSetCronop> programmi;
	
	
	
	
	
	public Set<SiacRFpvSetCronop> getProgrammi() {
		return programmi;
	}

	public void setProgrammi(Set<SiacRFpvSetCronop> programmi) {
		this.programmi = programmi;
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

	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

}