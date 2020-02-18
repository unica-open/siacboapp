/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.gestioneordinativi;

import java.util.List;

import it.csi.siac.siacboapp.integration.entity.SiacDAttoAmmTipo;
import it.csi.siac.siacboapp.integration.entity.SiacDDistinta;
import it.csi.siac.siacboapp.integration.entity.SiacDOrdinativoStato;
import it.csi.siac.siacboapp.integration.entity.SiacDOrdinativoTipo;
import it.csi.siac.siacboapp.integration.entity.SiacDSoggettoClasse;
import it.csi.siac.siaccommonapp.model.GenericModel;

public class GestioneOrdinativiModel extends GenericModel {
	private static final long serialVersionUID = -6155576148318081564L;

	private List<SiacDOrdinativoStato> elencoStatiOrdinativo;
	private List<SiacDOrdinativoTipo> elencoTipiOrdinativo;
	private List<String> elencoCodiciFlussoOrdinativiEntrata;
	private List<String> elencoCodiciFlussoOrdinativiSpesa;
	private List<SiacDDistinta> elencoCodiciDistintaOrdinativiEntrata;
	private List<SiacDDistinta> elencoCodiciDistintaOrdinativiSpesa;
	private List<SiacDAttoAmmTipo> elencoTipiAttoAmministrativo;
	private List<SiacDSoggettoClasse> elencoClassiSoggetto;
 	private String elencoStruttureAmministrativoContabiliJson;
	private CriteriRicercaOrdinativi criteri;
	private boolean siopePlus = false;

	public CriteriRicercaOrdinativi getCriteri() {
		return criteri;
	}

	public void setCriteri(CriteriRicercaOrdinativi criteri) {
		this.criteri = criteri;
	}

	public List<SiacDOrdinativoStato> getElencoStatiOrdinativo() {
		return elencoStatiOrdinativo;
	}

	public void setElencoStatiOrdinativo(List<SiacDOrdinativoStato> elencoStatiOrdinativo) {
		this.elencoStatiOrdinativo = elencoStatiOrdinativo;
	}

	public List<SiacDOrdinativoTipo> getElencoTipiOrdinativo() {
		return elencoTipiOrdinativo;
	}

	public void setElencoTipiOrdinativo(List<SiacDOrdinativoTipo> elencoTipiOrdinativo) {
		this.elencoTipiOrdinativo = elencoTipiOrdinativo;
	}

	public boolean isSiopePlus()
	{
		return siopePlus;
	}

	public void setSiopePlus(boolean siopePlus)
	{
		this.siopePlus = siopePlus;
	}

	public List<SiacDAttoAmmTipo> getElencoTipiAttoAmministrativo() {
		return elencoTipiAttoAmministrativo;
	}

	public void setElencoTipiAttoAmministrativo(List<SiacDAttoAmmTipo> elencoTipiAttoAmministrativo) {
		this.elencoTipiAttoAmministrativo = elencoTipiAttoAmministrativo;
	}

	public String getElencoStruttureAmministrativoContabiliJson() {
		return elencoStruttureAmministrativoContabiliJson;
	}

	public void setElencoStruttureAmministrativoContabiliJson(String elencoStruttureAmministrativoContabiliJson) {
		this.elencoStruttureAmministrativoContabiliJson = elencoStruttureAmministrativoContabiliJson;
	}

	public List<SiacDSoggettoClasse> getElencoClassiSoggetto() {
		return elencoClassiSoggetto;
	}

	public void setElencoClassiSoggetto(List<SiacDSoggettoClasse> elencoClassiSoggetto) {
		this.elencoClassiSoggetto = elencoClassiSoggetto;
	}

	public List<String> getElencoCodiciFlussoOrdinativiEntrata() {
		return elencoCodiciFlussoOrdinativiEntrata;
	}

	public void setElencoCodiciFlussoOrdinativiEntrata(List<String> elencoCodiciFlussoOrdinativiEntrata) {
		this.elencoCodiciFlussoOrdinativiEntrata = elencoCodiciFlussoOrdinativiEntrata;
	}

	public List<String> getElencoCodiciFlussoOrdinativiSpesa() {
		return elencoCodiciFlussoOrdinativiSpesa;
	}

	public void setElencoCodiciFlussoOrdinativiSpesa(List<String> elencoCodiciFlussoOrdinativiSpesa) {
		this.elencoCodiciFlussoOrdinativiSpesa = elencoCodiciFlussoOrdinativiSpesa;
	}

	public List<SiacDDistinta> getElencoCodiciDistintaOrdinativiEntrata() {
		return elencoCodiciDistintaOrdinativiEntrata;
	}

	public void setElencoCodiciDistintaOrdinativiEntrata(List<SiacDDistinta> elencoCodiciDistintaOrdinativiEntrata) {
		this.elencoCodiciDistintaOrdinativiEntrata = elencoCodiciDistintaOrdinativiEntrata;
	}

	public List<SiacDDistinta> getElencoCodiciDistintaOrdinativiSpesa() {
		return elencoCodiciDistintaOrdinativiSpesa;
	}

	public void setElencoCodiciDistintaOrdinativiSpesa(List<SiacDDistinta> elencoCodiciDistintaOrdinativiSpesa) {
		this.elencoCodiciDistintaOrdinativiSpesa = elencoCodiciDistintaOrdinativiSpesa;
	}
}
