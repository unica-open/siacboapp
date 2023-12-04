/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.util.entitywrapper;

import org.apache.commons.lang.StringUtils;

public class SiacTModpagWrapper extends BaseEntityWrapper {

	private static final long serialVersionUID = 8223557735897976498L;

	private String codice;
	private String bic;
	private String contoCorrente;
	private String iban;
	private String note;
	private SiacTSoggettoWrapper soggetto;
	private String codiceTipoAccredito;
	private String descrizioneTipoAccredito;

	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
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

	public SiacTSoggettoWrapper getSoggetto() {
		return soggetto;
	}

	public void setSoggetto(SiacTSoggettoWrapper soggetto) {
		this.soggetto = soggetto;
	}

	public String getCodiceTipoAccredito() {
		return codiceTipoAccredito;
	}

	public void setCodiceTipoAccredito(String codiceTipoAccredito) {
		this.codiceTipoAccredito = codiceTipoAccredito;
	}

	public String getDescrizioneTipoAccredito() {
		return descrizioneTipoAccredito;
	}

	public void setDescrizioneTipoAccredito(String descrizioneTipoAccredito) {
		this.descrizioneTipoAccredito = descrizioneTipoAccredito;
	}

	public String getContoCorrente() {
		return contoCorrente;
	}

	public void setContoCorrente(String contoCorrente) {
		this.contoCorrente = contoCorrente;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		StringBuilder sb = new StringBuilder(String.format("%s - %s", codiceTipoAccredito, descrizioneTipoAccredito));
		
		if (StringUtils.isNotBlank(iban)) {
			sb.append(", iban: " + iban);
			return sb.toString();
		}
		
		if (StringUtils.isNotBlank(contoCorrente)) {
			sb.append(", conto: " + contoCorrente);
			return sb.toString();
		}
		
//		if (StringUtils.isNotBlank(note)) {
//			sb.append(", note: " + note);
//		}
//		
		return sb.toString();
	}
	
	public void setDescrizione(String descrizione) {
	}
}
