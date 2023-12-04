/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.ordinativi;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

import it.csi.siac.siacboapp.util.DefaultDateConverter;

public class CriteriRicercaFlussi implements Serializable {
	private static final Date MIN_DATE = new GregorianCalendar(1900, 0, 1).getTime();
	private static final Date MAX_DATE = new GregorianCalendar(9999, 0, 1).getTime();

	private static final long serialVersionUID = -9070522334726079016L;

	private String anno;
	private Integer numeroDa;
	private Integer numeroA;
	private Date dataInserimentoDa;
	private Date dataInserimentoA;
	private String codiceTipo;

	public Integer getNumeroDa() {
		return numeroDa;
	}

	public void setNumeroDa(Integer numeroDa) {
		this.numeroDa = numeroDa;
	}

	public Integer getNumeroA() {
		return numeroA;
	}

	public void setNumeroA(Integer numeroA) {
		this.numeroA = numeroA;
	}

	public void setDataInserimentoDaStr(String dataInserimentoDaStr) {
		setDataInserimentoDa(DefaultDateConverter.convertToDefaultDate(dataInserimentoDaStr, MIN_DATE));
	}

	public void setDataInserimentoAStr(String dataInserimentoAStr) {
		setDataInserimentoA(DefaultDateConverter.convertToDefaultDate(dataInserimentoAStr, MAX_DATE));
	}

	public String getCodiceTipo() {
		return codiceTipo;
	}

	public void setCodiceTipo(String codiceTipo) {
		this.codiceTipo = codiceTipo;
	}

	public Date getDataInserimentoDa() {
		return dataInserimentoDa;
	}

	public void setDataInserimentoDa(Date dataInserimentoDa) {
		this.dataInserimentoDa = dataInserimentoDa;
	}

	public Date getDataInserimentoA() {
		return dataInserimentoA;
	}

	public void setDataInserimentoA(Date dataInserimentoA) {
		this.dataInserimentoA = dataInserimentoA;
	}

	public String getAnno() {
		return anno;
	}

	public void setAnno(String anno) {
		this.anno = anno;
	}

}
