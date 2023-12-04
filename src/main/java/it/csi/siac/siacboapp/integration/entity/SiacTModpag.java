/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

@Entity
@Table(name="siac_t_modpag")
public class SiacTModpag extends SiacTEnteBase {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SIAC_T_MODPAG_MODPAGID_GENERATOR", allocationSize=1, sequenceName="SIAC_T_MODPAG_MODPAG_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIAC_T_MODPAG_MODPAGID_GENERATOR")
	@Column(name="modpag_id")
	private Integer uid;
	
	private String bic;

	private String contocorrente;

	@Column(name="contocorrente_intestazione")
	private String contocorrenteIntestazione;

	@Column(name="data_scadenza")
	private Date dataScadenza;

	private String iban;

	private String note;

	@Column(name="quietanzante_nascita_data")
	private Date quietanzanteNascitaData;

	private String quietanziante;

	@Column(name="quietanziante_codice_fiscale")
	private String quietanzianteCodiceFiscale;

	@Column(name="quietanziante_nascita_luogo")
	private String quietanzianteNascitaLuogo;

	@Column(name="quietanziante_nascita_stato")
	private String quietanzianteNascitaStato;

	@Column(name="banca_denominazione")
	private String bancaDenominazione;
	
	@Column(name="per_stipendi")
	private Boolean perStipendi;


//	@ManyToOne
//	@JoinColumn(name="accredito_tipo_id")
//	private SiacDAccreditoTipo siacDAccreditoTipo;

	@ManyToOne
	@JoinColumn(name="soggetto_id")
	private SiacTSoggetto soggetto;




	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid =uid;
		
	}

	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	public String getContocorrente() {
		return contocorrente;
	}

	public void setContocorrente(String contocorrente) {
		this.contocorrente = contocorrente;
	}

	public String getContocorrenteIntestazione() {
		return contocorrenteIntestazione;
	}

	public void setContocorrenteIntestazione(String contocorrenteIntestazione) {
		this.contocorrenteIntestazione = contocorrenteIntestazione;
	}

	public Date getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getQuietanzanteNascitaData() {
		return quietanzanteNascitaData;
	}

	public void setQuietanzanteNascitaData(Date quietanzanteNascitaData) {
		this.quietanzanteNascitaData = quietanzanteNascitaData;
	}

	public String getQuietanziante() {
		return quietanziante;
	}

	public void setQuietanziante(String quietanziante) {
		this.quietanziante = quietanziante;
	}

	public String getQuietanzianteCodiceFiscale() {
		return quietanzianteCodiceFiscale;
	}

	public void setQuietanzianteCodiceFiscale(String quietanzianteCodiceFiscale) {
		this.quietanzianteCodiceFiscale = quietanzianteCodiceFiscale;
	}

	public String getQuietanzianteNascitaLuogo() {
		return quietanzianteNascitaLuogo;
	}

	public void setQuietanzianteNascitaLuogo(String quietanzianteNascitaLuogo) {
		this.quietanzianteNascitaLuogo = quietanzianteNascitaLuogo;
	}

	public String getQuietanzianteNascitaStato() {
		return quietanzianteNascitaStato;
	}

	public void setQuietanzianteNascitaStato(String quietanzianteNascitaStato) {
		this.quietanzianteNascitaStato = quietanzianteNascitaStato;
	}

	public String getBancaDenominazione() {
		return bancaDenominazione;
	}

	public void setBancaDenominazione(String bancaDenominazione) {
		this.bancaDenominazione = bancaDenominazione;
	}

	public Boolean getPerStipendi() {
		return perStipendi;
	}

	public void setPerStipendi(Boolean perStipendi) {
		this.perStipendi = perStipendi;
	}

	public SiacTSoggetto getSoggetto() {
		return soggetto;
	}

	public void setSoggetto(SiacTSoggetto soggetto) {
		this.soggetto = soggetto;
	}

	
	
	public class SiacTModpagExt extends SiacTModpag {
		

		private Integer ordine;

		public Integer getOrdine() {
			return ordine;
		}

		public void setOrdine(Integer ordine) {
			this.ordine = ordine;
		}

	}
	
}




