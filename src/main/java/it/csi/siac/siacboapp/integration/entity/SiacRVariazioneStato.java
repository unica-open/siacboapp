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
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="siac_r_variazione_stato")
@NamedQuery(name="SiacRVariazioneStato.findAll", query="SELECT s FROM SiacRVariazioneStato s")
public class SiacRVariazioneStato extends SiacTEnteBase {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SIAC_R_VARIAZIONE_STATO_VARIAZIONESTATOID_GENERATOR", allocationSize=1, sequenceName="SIAC_R_VARIAZIONE_STATO_VARIAZIONE_STATO_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIAC_R_VARIAZIONE_STATO_VARIAZIONESTATOID_GENERATOR")
	@Column(name="variazione_stato_id")
	private Integer uid;

	@ManyToOne
	@JoinColumn(name="variazione_stato_tipo_id")
	private SiacDVariazioneStato stato;

	@ManyToOne
	@JoinColumn(name="variazione_id")
	private SiacTVariazione variazione;

	public SiacRVariazioneStato() {
	}
	
	@Override
	public Integer getUid() {
		return uid;
	}

	/* (non-Javadoc)
	 * @see it.csi.siac.siaccommonser.integration.entity.SiacTBase#setUid(java.lang.Integer)
	 */
	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
		
	}

	public SiacTVariazione getVariazione() {
		return variazione;
	}

	public void setVariazione(SiacTVariazione variazione) {
		this.variazione = variazione;
	}

	public SiacDVariazioneStato getStato() {
		return stato;
	}

	public void setStato(SiacDVariazioneStato stato) {
		this.stato = stato;
	}

}