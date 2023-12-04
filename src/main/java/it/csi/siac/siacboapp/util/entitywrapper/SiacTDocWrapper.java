/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.util.entitywrapper;

import java.math.BigDecimal;
import java.util.Date;

import it.csi.siac.siacboapp.integration.entity.SiacDDocTipo;

public class SiacTDocWrapper extends BaseEntityWrapper {


	private static final long serialVersionUID = 8223557735897976498L;

	private Integer anno;
	private Date dataEmissione;
	private Date dataScadenza;
	private String descrizione;
	private BigDecimal importo;
	private String numero;
	private SiacDDocTipo tipo;
	private SiacTSoggettoWrapper soggetto;

	public Integer getAnno() {
		return anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public Date getDataEmissione() {
		return dataEmissione;
	}

	public void setDataEmissione(Date dataEmissione) {
		this.dataEmissione = dataEmissione;
	}

	public Date getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public BigDecimal getImporto() {
		return importo;
	}

	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public SiacDDocTipo getTipo() {
		return tipo;
	}

	public void setTipo(SiacDDocTipo tipo) {
		this.tipo = tipo;
	}

	public SiacTSoggettoWrapper getSoggetto() {
		return soggetto;
	}

	public void setSoggetto(SiacTSoggettoWrapper soggetto) {
		this.soggetto = soggetto;
	}

	@Override
	public String toString() {
		return String.format("%d/%s", anno, numero);
	}
}
