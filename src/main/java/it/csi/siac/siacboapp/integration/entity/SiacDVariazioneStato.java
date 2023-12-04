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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="siac_d_variazione_stato")
@NamedQuery(name="SiacDVariazioneStato.findAll", query="SELECT s FROM SiacDVariazioneStato s")
public class SiacDVariazioneStato extends SiacTEnteBase {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SIAC_D_VARIAZIONE_STATO_VARIAZIONESTATOTIPOID_GENERATOR", allocationSize=1, sequenceName="SIAC_D_VARIAZIONE_STATO_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIAC_D_VARIAZIONE_STATO_VARIAZIONESTATOTIPOID_GENERATOR")
	@Column(name="variazione_stato_tipo_id")
	private Integer uid;

	@Column(name="variazione_stato_tipo_code")
	private String codice;

	@Column(name="variazione_stato_tipo_desc")
	private String descrizione;

	@OneToMany(mappedBy="stato")
	private List<SiacRVariazioneStato> stati;


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

	public List<SiacRVariazioneStato> getStati() {
		return stati;
	}

	public void setStati(List<SiacRVariazioneStato> stati) {
		this.stati = stati;
	}

}