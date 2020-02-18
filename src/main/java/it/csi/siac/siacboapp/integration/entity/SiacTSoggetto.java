/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "siac_t_soggetto")
public class SiacTSoggetto extends SiacTEnteBase {


	private static final long serialVersionUID = -2417382493901632463L;

	@Id
	@SequenceGenerator(name = "SIAC_T_SOGGETTO_SOGGETTO_ID_GENERATOR", allocationSize = 1, sequenceName = "SIAC_T_SOGGETTO_SOGGETTO_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIAC_T_SOGGETTO_SOGGETTO_ID_GENERATOR")
	@Column(name = "soggetto_id")
	private Integer uid;

	@Column(name = "codice_fiscale")
	private String codiceFiscale;

	@Column(name = "codice_fiscale_estero")
	private String codiceFiscaleEstero;

	@Column(name = "soggetto_code")
	private String codice;

	@Column(name = "soggetto_desc")
	private String descrizione;

	@Column(name = "login_creazione")
	private String loginCreazione;

	@ManyToOne
	@JoinColumn(name = "ambito_id")
	private SiacDAmbito ambito;

	@OneToMany(mappedBy = "soggetto", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	private List<SiacRSoggettoRuolo> ruoli;

	@OneToMany(mappedBy="soggetto")
	private List<SiacRSoggettoClasse> classi;
	
	@OneToMany(mappedBy="soggetto")
	private List<SiacRSoggettoStato> stati;
	
	public String getLoginCreazione() {
		return loginCreazione;
	}

	public void setLoginCreazione(String loginCreazione) {
		this.loginCreazione = loginCreazione;
	}

	public String getCodiceFiscale() {
		return this.codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getCodiceFiscaleEstero() {
		return this.codiceFiscaleEstero;
	}

	public void setCodiceFiscaleEstero(String codiceFiscaleEstero) {
		this.codiceFiscaleEstero = codiceFiscaleEstero;
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

	public SiacDAmbito getAmbito() {
		return ambito;
	}

	public void setAmbito(SiacDAmbito ambito) {
		this.ambito = ambito;
	}

	@Override
	public Integer getUid() {
		return this.uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getRuoloId() {
		try {
			return ruoli.iterator().next().getRuolo().getUid();
		} catch (NullPointerException e) {
			return null;
		}
	}

	public void setRuoloId(Integer ruoloId) {
		ruoli = new ArrayList<SiacRSoggettoRuolo>();
		SiacRSoggettoRuolo sr = new SiacRSoggettoRuolo(new SiacDRuolo(ruoloId));
		sr.setSoggetto(this);
		ruoli.add(sr);
	}

	public List<SiacRSoggettoRuolo> getRuoli() {
		return ruoli;
	}

	public void setRuoli(List<SiacRSoggettoRuolo> ruoli) {
		this.ruoli = ruoli;
	}

	public void setAmbitoId(Integer ambitoId) {
		ambito = new SiacDAmbito(ambitoId);
	}

	public Integer getAmbitoId() {
		return ambito == null ? null : ambito.getUid();
	}
	
	public SiacDSoggettoStato getStato() {
		return stati != null && ! stati.isEmpty() ? stati.iterator().next().getStato() : null;
	}
}