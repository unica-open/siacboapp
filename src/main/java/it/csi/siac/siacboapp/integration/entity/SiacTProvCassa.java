/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
@Table(name="siac_t_prov_cassa")
public class SiacTProvCassa extends SiacTEnteBase {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SIAC_T_PROV_CASSA_PROVCID_GENERATOR", allocationSize=1, sequenceName="SIAC_T_PROV_CASSA_PROVC_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIAC_T_PROV_CASSA_PROVCID_GENERATOR")
	@Column(name="provc_id")
	private Integer uid;

	@Column(name="provc_anno")
	private Integer anno;

	@Column(name="provc_numero")
	private Integer numero;

	@Column(name="provc_conto_evidenza")
	private String codiceContoEvidenza;
	
	@Column(name="provc_conto_evidenza_desc")
	private String descrizioneContoEvidenza;
	
	@Column(name="provc_causale")
	private String causale;

	@Column(name="provc_subcausale")
	private String subcausale;

	@Column(name="provc_denom_soggetto")
	private String descrizioneSoggetto;

	@Column(name="provc_importo")
	private BigDecimal importo;

	@Column(name="provc_data_emissione")
	private Date dataEmissione;

	@Column(name="provc_data_annullamento")
	private Date dataAnnullamento;

	@Column(name="provc_data_regolarizzazione")
	private Date dataRegolarizzazione;

	@OneToMany(mappedBy="provvisorioCassa")
	private List<SiacROrdinativoProvCassa> ordinativi;

	@ManyToOne
	@JoinColumn(name="provc_tipo_id")
	private SiacDProvCassaTipo tipo;


	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getAnno() {
		return anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getCausale() {
		return causale;
	}

	public void setCausale(String causale) {
		this.causale = causale;
	}

	public String getDescrizioneSoggetto() {
		return descrizioneSoggetto;
	}

	public void setDescrizioneSoggetto(String descrizioneSoggetto) {
		this.descrizioneSoggetto = descrizioneSoggetto;
	}

	public BigDecimal getImporto() {
		return importo;
	}

	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}

	public List<SiacROrdinativoProvCassa> getOrdinativi() {
		return ordinativi;
	}

	public void setOrdinativi(List<SiacROrdinativoProvCassa> ordinativi) {
		this.ordinativi = ordinativi;
	}

	public SiacDProvCassaTipo getTipo() {
		return tipo;
	}

	public void setTipo(SiacDProvCassaTipo tipo) {
		this.tipo = tipo;
	}

	public Date getDataEmissione() {
		return dataEmissione;
	}

	public void setDataEmissione(Date dataEmissione) {
		this.dataEmissione = dataEmissione;
	}

	public Date getDataAnnullamento() {
		return dataAnnullamento;
	}

	public void setDataAnnullamento(Date dataAnnullamento) {
		this.dataAnnullamento = dataAnnullamento;
	}

	public Date getDataRegolarizzazione() {
		return dataRegolarizzazione;
	}

	public void setDataRegolarizzazione(Date dataRegolarizzazione) {
		this.dataRegolarizzazione = dataRegolarizzazione;
	}

	public String getSubcausale() {
		return subcausale;
	}

	public void setSubcausale(String subcausale) {
		this.subcausale = subcausale;
	}

	public String getCodiceContoEvidenza() {
		return codiceContoEvidenza;
	}

	public void setCodiceContoEvidenza(String codiceContoEvidenza) {
		this.codiceContoEvidenza = codiceContoEvidenza;
	}

	public String getDescrizioneContoEvidenza() {
		return descrizioneContoEvidenza;
	}

	public void setDescrizioneContoEvidenza(String descrizioneContoEvidenza) {
		this.descrizioneContoEvidenza = descrizioneContoEvidenza;
	}
}