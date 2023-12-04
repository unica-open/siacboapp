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


/**
 * The persistent class for the siac_d_evento database table.
 * 
 */
@Entity
@Table(name="siac_d_evento")
@NamedQuery(name="SiacDEvento.findAll", query="SELECT s FROM SiacDEvento s")
public class SiacDEvento extends SiacTEnteBase {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SIAC_D_EVENTO_EVENTOID_GENERATOR", allocationSize=1, sequenceName="SIAC_D_EVENTO_EVENTO_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIAC_D_EVENTO_EVENTOID_GENERATOR")
	@Column(name="evento_id")
	private Integer uid;

	@Column(name="evento_code")
	private String codice;

	@Column(name="evento_desc")
	private String descrizione;

	//bi-directional many-to-one association to SiacDEventoTipo
	@ManyToOne
	@JoinColumn(name="evento_tipo_id")
	private SiacDEventoTipo siacDEventoTipo;
	
	//bi-directional many-to-one association to SiacDCollegamentoTipo
	@ManyToOne
	@JoinColumn(name="collegamento_tipo_id")
	private SiacDCollegamentoTipo siacDCollegamentoTipo;

	//bi-directional many-to-one association to SiacREventoCausale
//	@OneToMany(mappedBy="siacDEvento")
//	private List<SiacREventoCausale> siacREventoCausales;

//	//bi-directional many-to-one association to SiacREventoTipoTabella
//	@OneToMany(mappedBy="siacDEvento")
//	private List<SiacREventoTipoTabella> siacREventoTipoTabellas;

	public SiacDEvento() {}

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

	public SiacDEventoTipo getSiacDEventoTipo() {
		return this.siacDEventoTipo;
	}

	public void setSiacDEventoTipo(SiacDEventoTipo siacDEventoTipo) {
		this.siacDEventoTipo = siacDEventoTipo;
	}
	
	/**
	 * @return the siacDCollegamentoTipo
	 */
	public SiacDCollegamentoTipo getSiacDCollegamentoTipo() {
		return siacDCollegamentoTipo;
	}

	/**
	 * @param siacDCollegamentoTipo the siacDCollegamentoTipo to set
	 */
	public void setSiacDCollegamentoTipo(SiacDCollegamentoTipo siacDCollegamentoTipo) {
		this.siacDCollegamentoTipo = siacDCollegamentoTipo;
	}

//	public List<SiacREventoCausale> getSiacREventoCausales() {
//		return this.siacREventoCausales;
//	}
//
//	public void setSiacREventoCausales(List<SiacREventoCausale> siacREventoCausales) {
//		this.siacREventoCausales = siacREventoCausales;
//	}
//
//	public SiacREventoCausale addSiacREventoCausale(SiacREventoCausale siacREventoCausale) {
//		getSiacREventoCausales().add(siacREventoCausale);
//		siacREventoCausale.setSiacDEvento(this);
//
//		return siacREventoCausale;
//	}
//
//	public SiacREventoCausale removeSiacREventoCausale(SiacREventoCausale siacREventoCausale) {
//		getSiacREventoCausales().remove(siacREventoCausale);
//		siacREventoCausale.setSiacDEvento(null);
//
//		return siacREventoCausale;
//	}

//	public List<SiacREventoTipoTabella> getSiacREventoTipoTabellas() {
//		return this.siacREventoTipoTabellas;
//	}
//
//	public void setSiacREventoTipoTabellas(List<SiacREventoTipoTabella> siacREventoTipoTabellas) {
//		this.siacREventoTipoTabellas = siacREventoTipoTabellas;
//	}

//	public SiacREventoTipoTabella addSiacREventoTipoTabella(SiacREventoTipoTabella siacREventoTipoTabella) {
//		getSiacREventoTipoTabellas().add(siacREventoTipoTabella);
//		siacREventoTipoTabella.setSiacDEvento(this);
//
//		return siacREventoTipoTabella;
//	}
//
//	public SiacREventoTipoTabella removeSiacREventoTipoTabella(SiacREventoTipoTabella siacREventoTipoTabella) {
//		getSiacREventoTipoTabellas().remove(siacREventoTipoTabella);
//		siacREventoTipoTabella.setSiacDEvento(null);
//
//		return siacREventoTipoTabella;
//	}

	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

}