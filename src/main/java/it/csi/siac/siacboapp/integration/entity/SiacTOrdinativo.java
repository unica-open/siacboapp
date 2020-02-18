/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

import it.csi.siac.siacfin2ser.model.TipoOrdinativo;

@Entity
@Table(name = "siac_t_ordinativo")
public class SiacTOrdinativo extends SiacTEnteBase {
	private static final long serialVersionUID = -446095291932276536L;

	@Id
	@SequenceGenerator(name = "SIAC_T_ORDINATIVO_ORDID_GENERATOR", allocationSize = 1, sequenceName = "SIAC_T_ORDINATIVO_ORD_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIAC_T_ORDINATIVO_ORDID_GENERATOR")
	@Column(name = "ord_id")
	private Integer uid;

	@Column(name = "ord_numero")
	private BigDecimal numero;

	@Column(name = "ord_desc")
	private String descrizione;

	@Column(name = "ord_anno")
	private Integer anno;

	@ManyToOne
	@JoinColumn(name = "ord_tipo_id")
	private SiacDOrdinativoTipo tipo;

	@OneToMany(mappedBy = "ordinativo")
	private Set<MifTOrdinativoSpesa> ordinativiSpesaMif;

	@OneToMany(mappedBy = "ordinativo")
	private Set<MifTOrdinativoEntrata> ordinativiEntrataMif;

	@Column(name = "ord_emissione_data")
	private Date dataEmissione;

	@Column(name = "ord_trasm_oil_data")
	private Date dataTrasmissioneOil;
	
	@Column(name = "ord_spostamento_data")
	private Date dataSpostamento;

	@OneToMany(mappedBy = "ordinativo")
	private Set<SiacROrdinativoStato> stati;

	@Column(name = "ord_da_trasmettere")
	private Boolean daTrasmettere;

	@OneToMany(mappedBy="ordinativo")
	private List<SiacROrdinativoAttoAmm> attiAmministrativi;

	@OneToMany(mappedBy="ordinativo")
	private List<SiacROrdinativoSoggetto> soggetti;

	@OneToMany(mappedBy="ordinativo")
	private List<SiacTOrdinativoTs> ordinativiTs;
	
	@OneToMany(mappedBy="ordinativo")
	private List<SiacROrdinativoProvCassa> provvisoriCassa;

	@ManyToOne
	@JoinColumn(name="dist_id")
	private SiacDDistinta distinta;
	
	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public SiacDOrdinativoStato getStato() {
		return stati.iterator().next().getStato();
	}

	public Date getDataEmissione() {
		return dataEmissione;
	}

	public void setDataEmissione(Date dataEmissione) {
		this.dataEmissione = dataEmissione;
	}

	public BigDecimal getNumero() {
		return numero;
	}

	public void setNumero(BigDecimal numero) {
		this.numero = numero;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public SiacDOrdinativoTipo getTipo() {
		return tipo;
	}

	public void setTipo(SiacDOrdinativoTipo tipo) {
		this.tipo = tipo;
	}

	
	public Date getDataTrasmissioneOil() {
		return dataTrasmissioneOil;
	}

	public void setDataTrasmissioneOil(Date dataTrasmissioneOil) {
		this.dataTrasmissioneOil = dataTrasmissioneOil;
	}

	public String getCodiceFlusso() {
		Set<? extends MifTOrdinativoBase> o;

		switch (TipoOrdinativo.fromCodice(tipo.getCodice())) {
		case INCASSO:
			o = ordinativiEntrataMif;
			break;
		case PAGAMENTO:
			o = ordinativiSpesaMif;
			break;
		default:
			throw new IllegalArgumentException(
					String.format("Tipo ordinativo non valido: uid %d, tipo %s", uid, tipo.getCodice()));
		}

		return o.iterator().hasNext() ? o.iterator().next().getCodiceFlusso() : null;
	}

	public Set<MifTOrdinativoSpesa> getOrdinativiSpesaMif() {
		return ordinativiSpesaMif;
	}

	public void setOrdinativiSpesaMif(Set<MifTOrdinativoSpesa> ordinativiSpesaMif) {
		this.ordinativiSpesaMif = ordinativiSpesaMif;
	}

	public Set<MifTOrdinativoEntrata> getOrdinativiEntrataMif() {
		return ordinativiEntrataMif;
	}

	public void setOrdinativiEntrataMif(Set<MifTOrdinativoEntrata> ordinativiEntrataMif) {
		this.ordinativiEntrataMif = ordinativiEntrataMif;
	}

	public Set<SiacROrdinativoStato> getStati() {
		return stati;
	}

	public void setStati(Set<SiacROrdinativoStato> stati) {
		this.stati = stati;
	}

	public Integer getAnno() {
		return anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public Date getDataSpostamento() {
		return dataSpostamento;
	}

	public void setDataSpostamento(Date dataSpostamento) {
		this.dataSpostamento = dataSpostamento;
	}

	public Boolean getDaTrasmettere() {
		return daTrasmettere;
	}

	public void setDaTrasmettere(Boolean daTrasmettere) {
		this.daTrasmettere = daTrasmettere;
	}

	public List<SiacROrdinativoAttoAmm> getAttiAmministrativi() {
		return attiAmministrativi;
	}

	public void setAttiAmministrativi(List<SiacROrdinativoAttoAmm> attiAmministrativi) {
		this.attiAmministrativi = attiAmministrativi;
	}

	public List<SiacROrdinativoSoggetto> getSoggetti() {
		return soggetti;
	}

	public void setSoggetti(List<SiacROrdinativoSoggetto> soggetti) {
		this.soggetti = soggetti;
	}

	public List<SiacTOrdinativoTs> getOrdinativiTs() {
		return ordinativiTs;
	}

	public void setOrdinativiTs(List<SiacTOrdinativoTs> ordinativiTs) {
		this.ordinativiTs = ordinativiTs;
	}

	public SiacDDistinta getDistinta() {
		return distinta;
	}

	public void setDistinta(SiacDDistinta distinta) {
		this.distinta = distinta;
	}
}