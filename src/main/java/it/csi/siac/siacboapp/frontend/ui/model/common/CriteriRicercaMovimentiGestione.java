/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.common;

import java.io.Serializable;
import java.math.BigDecimal;

public abstract class CriteriRicercaMovimentiGestione implements Serializable {
	
	private static final long serialVersionUID = -454405955436536938L;
	
	
	private Integer anno;
	private BigDecimal numero;

	public Integer getAnno() {
		return anno;
	}
	public void setAnno(Integer anno) {
		this.anno = anno;
	}
	public BigDecimal getNumero() {
		return numero;
	}
	public void setNumero(BigDecimal numero) {
		this.numero = numero;
	}


}
