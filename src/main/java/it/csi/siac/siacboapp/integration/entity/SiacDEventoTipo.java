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


/**
 * The persistent class for the siac_d_evento_tipo database table.
 * 
 */
@Entity
@Table(name="siac_d_evento_tipo")
@NamedQuery(name="SiacDEventoTipo.findAll", query="SELECT s FROM SiacDEventoTipo s")
public class SiacDEventoTipo extends SiacTEnteBase {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SIAC_D_EVENTO_TIPO_EVENTOTIPOID_GENERATOR", allocationSize=1, sequenceName="SIAC_D_EVENTO_TIPO_EVENTO_TIPO_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIAC_D_EVENTO_TIPO_EVENTOTIPOID_GENERATOR")
	@Column(name="evento_tipo_id")
	private Integer uid;

	@Column(name="evento_tipo_code")
	private String codice;

	@Column(name="evento_tipo_desc")
	private String descrizione;
	
	//bi-directional many-to-one association to SiacDEvento
	@OneToMany(mappedBy="siacDEventoTipo")
	private List<SiacDEvento> siacDEventos;

//	//bi-directional many-to-one association to SiacREventoTipoTabella
//	@OneToMany(mappedBy="siacDEventoTipo")
//	private List<SiacREventoTipoTabella> siacREventoTipoTabellas;
	
	//bi-directional many-to-one association to SiacRCausaleEpTipoEventoTipo
//	@OneToMany(mappedBy="siacDEventoTipo")
//	private List<SiacRCausaleEpTipoEventoTipo> siacRCausaleEpTipoEventoTipos;

	public SiacDEventoTipo() {}

	/**
	 * @return the codice
	 */
	public String getCodice() {
		return codice;
	}

	/**
	 * @param codice the codice to set
	 */
	public void setCodice(String codice) {
		this.codice = codice;
	}

	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * @param descrizione the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<SiacDEvento> getSiacDEventos() {
		return this.siacDEventos;
	}

	public void setSiacDEventos(List<SiacDEvento> siacDEventos) {
		this.siacDEventos = siacDEventos;
	}

	public SiacDEvento addSiacDEvento(SiacDEvento siacDEvento) {
		getSiacDEventos().add(siacDEvento);
		siacDEvento.setSiacDEventoTipo(this);

		return siacDEvento;
	}

	public SiacDEvento removeSiacDEvento(SiacDEvento siacDEvento) {
		getSiacDEventos().remove(siacDEvento);
		siacDEvento.setSiacDEventoTipo(null);

		return siacDEvento;
	}

//	public List<SiacREventoTipoTabella> getSiacREventoTipoTabellas() {
//		return this.siacREventoTipoTabellas;
//	}
//
//	public void setSiacREventoTipoTabellas(List<SiacREventoTipoTabella> siacREventoTipoTabellas) {
//		this.siacREventoTipoTabellas = siacREventoTipoTabellas;
//	}
//
//	public SiacREventoTipoTabella addSiacREventoTipoTabella(SiacREventoTipoTabella siacREventoTipoTabella) {
//		getSiacREventoTipoTabellas().add(siacREventoTipoTabella);
//		siacREventoTipoTabella.setSiacDEventoTipo(this);
//
//		return siacREventoTipoTabella;
//	}
//
//	public SiacREventoTipoTabella removeSiacREventoTipoTabella(SiacREventoTipoTabella siacREventoTipoTabella) {
//		getSiacREventoTipoTabellas().remove(siacREventoTipoTabella);
//		siacREventoTipoTabella.setSiacDEventoTipo(null);
//
//		return siacREventoTipoTabella;
//	}
	
//	public List<SiacRCausaleEpTipoEventoTipo> getSiacRCausaleEpTipoEventoTipos() {
//		return this.siacRCausaleEpTipoEventoTipos;
//	}
//
//	public void setSiacRCausaleEpTipoEventoTipos(List<SiacRCausaleEpTipoEventoTipo> siacRCausaleEpTipoEventoTipos) {
//		this.siacRCausaleEpTipoEventoTipos = siacRCausaleEpTipoEventoTipos;
//	}
//
//	public SiacRCausaleEpTipoEventoTipo addSiacRCausaleEpTipoEventoTipo(SiacRCausaleEpTipoEventoTipo siacRCausaleEpTipoEventoTipo) {
//		getSiacRCausaleEpTipoEventoTipos().add(siacRCausaleEpTipoEventoTipo);
//		siacRCausaleEpTipoEventoTipo.setSiacDEventoTipo(this);
//
//		return siacRCausaleEpTipoEventoTipo;
//	}
//
//	public SiacRCausaleEpTipoEventoTipo removeSiacRCausaleEpTipoEventoTipo(SiacRCausaleEpTipoEventoTipo siacRCausaleEpTipoEventoTipo) {
//		getSiacRCausaleEpTipoEventoTipos().remove(siacRCausaleEpTipoEventoTipo);
//		siacRCausaleEpTipoEventoTipo.setSiacDEventoTipo(null);
//
//		return siacRCausaleEpTipoEventoTipo;
//	}

	@Override
	public Integer getUid() {
		return this.uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

}