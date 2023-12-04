/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.entity;

import java.util.Date;
import java.util.List;

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
@Table(name="siac_t_variazione")
public class SiacTVariazione extends SiacTEnteBase {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SIAC_T_VARIAZIONE_VARIAZIONEID_GENERATOR", allocationSize=1, sequenceName="SIAC_T_VARIAZIONE_VARIAZIONE_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIAC_T_VARIAZIONE_VARIAZIONEID_GENERATOR")
	@Column(name="variazione_id")
	private Integer uid;

	@Column(name="variazione_data")
	private Date data;

	@Column(name="variazione_desc")
	private String descrizione;
	
	@Column(name="variazione_num")
	private Integer numero;
	
	@OneToMany(mappedBy="variazione", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
	private List<SiacRVariazioneStato> stati;

	@ManyToOne
	@JoinColumn(name="bil_id")
	private SiacTBil bilancio;
	
	public SiacTVariazione() {
	}
	
	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
		
	}

	public SiacDVariazioneStato getStato() {
		return stati != null && ! stati.isEmpty() ? stati.iterator().next().getStato() : null;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public List<SiacRVariazioneStato> getStati() {
		return stati;
	}

	public void setStati(List<SiacRVariazioneStato> stati) {
		this.stati = stati;
	}

	public SiacTBil getBilancio() {
		return bilancio;
	}

	public void setBilancio(SiacTBil bilancio) {
		this.bilancio = bilancio;
	}

}