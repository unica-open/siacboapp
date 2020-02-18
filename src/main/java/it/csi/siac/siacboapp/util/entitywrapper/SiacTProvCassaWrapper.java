/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.util.entitywrapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.time.DateFormatUtils;

public class SiacTProvCassaWrapper extends BaseEntityWrapper {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1824859263033379840L;
	private Integer anno;
	private Integer numero;
	private String codiceContoEvidenza;
	private String descrizioneContoEvidenza;
	private Date dataEmissione;
	private String causale;
	private String descrizioneSoggetto;
	private BigDecimal importo;
	private BigDecimal importoDaRegolarizzare;

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

	public Date getDataEmissione() {
		return dataEmissione;
	}

	public String getDataEmissioneStr() {
		if (dataEmissione == null) {
			return null;
		}
		
		return DateFormatUtils.format(dataEmissione, "dd/MM/yyyy", Locale.ITALY);
	}

	public void setDataEmissione(Date dataEmissione) {
		this.dataEmissione = dataEmissione;
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

	public BigDecimal getImportoDaRegolarizzare() {
		return importoDaRegolarizzare;
	}

	public void setImportoDaRegolarizzare(BigDecimal importoDaRegolarizzare) {
		this.importoDaRegolarizzare = importoDaRegolarizzare;
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
