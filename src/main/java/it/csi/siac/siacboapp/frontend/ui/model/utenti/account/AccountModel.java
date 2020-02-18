/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.utenti.account;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringEscapeUtils;

import it.csi.siac.siacboapp.integration.entity.SiacDRuoloOp;
import it.csi.siac.siacboapp.integration.entity.SiacRSoggettoRuolo;
import it.csi.siac.siacboapp.integration.entity.SiacTAccount;
import it.csi.siac.siacboapp.integration.entity.SiacTCassaEcon;
import it.csi.siac.siacboapp.integration.entity.SiacTClass;
import it.csi.siac.siacboapp.integration.entity.SiacTGruppo;
import it.csi.siac.siaccommonapp.model.GenericModel;

public class AccountModel extends GenericModel {

	private List<SiacRSoggettoRuolo> elencoSoggettiRuoli;
	private List<SiacTGruppo> elencoGruppi;
	private List<SiacDRuoloOp> elencoRuoliOp;
	//private List<SiacTClass> elencoStruttureAmministrativeContabili;
	private List<SiacTCassaEcon> elencoCasseEconomali;
	private String elencoStruttureAmministrativoContabiliJson;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7314305774761825521L;
	/**
	 * 
	 */

	private SiacTAccount accountEntity;

	public SiacTAccount getAccountEntity() {
		return accountEntity;
	}

	public void setAccountEntity(SiacTAccount accountEntity) {
		this.accountEntity = accountEntity;
	}

	public List<SiacRSoggettoRuolo> getElencoSoggettiRuoli() {
		return elencoSoggettiRuoli;
	}

	public void setElencoSoggettiRuoli(List<SiacRSoggettoRuolo> elencoSoggettiRuoli) {
		this.elencoSoggettiRuoli = elencoSoggettiRuoli;
	}

	public List<SiacTGruppo> getElencoGruppi() {
		return elencoGruppi;
	}

	public void setElencoGruppi(List<SiacTGruppo> elencoGruppi) {
		this.elencoGruppi = elencoGruppi;
	}

	public List<SiacDRuoloOp> getElencoRuoliOp() {
		return elencoRuoliOp;
	}

	public void setElencoRuoliOp(List<SiacDRuoloOp> elencoRuoliOp) {
		this.elencoRuoliOp = elencoRuoliOp;
	}

//	public List<SiacTClass> getElencoStruttureAmministrativeContabili() {
//		return elencoStruttureAmministrativeContabili;
//	}
//
//	public void setElencoStruttureAmministrativeContabili(
//			List<SiacTClass> elencoStruttureAmministrativeContabili) {
//		this.elencoStruttureAmministrativeContabili = elencoStruttureAmministrativeContabili;
//	}

	public List<SiacTCassaEcon> getElencoCasseEconomali() {
		return elencoCasseEconomali;
	}

	public void setElencoCasseEconomali(List<SiacTCassaEcon> elencoCasseEconomali) {
		this.elencoCasseEconomali = elencoCasseEconomali;
	}
	
	public String getElencoStruttureAmministrativoContabiliJson() {
		return elencoStruttureAmministrativoContabiliJson;
	}

	public void setElencoStruttureAmministrativoContabiliJson(String elencoStruttureAmministrativoContabiliJson) {
		this.elencoStruttureAmministrativoContabiliJson = elencoStruttureAmministrativoContabiliJson;
	}

	public void setElencoStruttureAmministrativoContabiliJson(
			List<SiacTClass> elencoStruttureAmministrativoContabili) throws Exception {

		Set<Integer> struttureAmministrativeContabiliId = new HashSet<Integer>();
		
		if (accountEntity != null)
			for (Integer sacId : accountEntity.getStruttureAmministrativeContabiliId())
				struttureAmministrativeContabiliId.add(sacId);
			
		StringBuilder json = new StringBuilder();
		
		json.append("[ \n");
		
		for (SiacTClass sac : elencoStruttureAmministrativoContabili)
			json.append(String.format("{ uid: %d, puid: %d, descr: '%s - %s', checked: %s },\n", sac.getUid(), 
					sac.getParent() != null ? sac.getParent().getUid() : -1, 
					sac.getCodice(), StringEscapeUtils.escapeJavaScript(sac.getDescrizione()),
					struttureAmministrativeContabiliId.contains(sac.getUid())));

		json.append(" ]\n");
		
		setElencoStruttureAmministrativoContabiliJson(json.toString());
	}
}
