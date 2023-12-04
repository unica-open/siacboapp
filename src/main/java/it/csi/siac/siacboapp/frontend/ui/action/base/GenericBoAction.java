/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import it.csi.siac.siacboapp.business.model.tefa.BoTipoFileEnum;
import it.csi.siac.siacboapp.frontend.ui.handler.session.BoSessionParameter;
import it.csi.siac.siacboapp.frontend.ui.model.common.StrutturaAmministrativoContabileWrapper;
import it.csi.siac.siacboapp.frontend.ui.util.comparator.StrutturaAmministrativoContabileComparator;
import it.csi.siac.siacboapp.util.JsonConverter;
import it.csi.siac.siaccommonapp.action.GenericAction;
import it.csi.siac.siaccommonapp.model.GenericModel;
import it.csi.siac.siaccommonapp.util.exception.ParamValidationException;
import it.csi.siac.siaccorser.frontend.webservice.ClassificatoreService;
import it.csi.siac.siaccorser.frontend.webservice.msg.LeggiStrutturaAmminstrativoContabile;
import it.csi.siac.siaccorser.frontend.webservice.msg.LeggiStrutturaAmminstrativoContabileResponse;
import it.csi.siac.siaccorser.model.Errore;
import it.csi.siac.siaccorser.model.Informazione;
import it.csi.siac.siaccorser.model.Messaggio;
import it.csi.siac.siaccorser.model.StrutturaAmministrativoContabile;
import it.csi.siac.siaccorser.model.errore.ErroreCore;
import it.csi.siac.siaccorser.model.file.TipoFileIntf;

public abstract class GenericBoAction<M extends GenericModel> extends GenericAction<M> {
	private static final long serialVersionUID = -770930848505834033L;

	@Autowired
	@Qualifier("classificatoreCorService")
	private ClassificatoreService classificatoreService;

	@Override
	public void prepare() throws Exception {
		super.prepare();
		
		handleRedirectActionInfo();
		handleRedirectActionErrors();
		handleRedirectActionMessages();
	}

	private void handleRedirectActionInfo() {
		if (sessionHandler.getParametro(BoSessionParameter.REDIRECT_ACTION_INFO) != null) {
			Iterable<Informazione> i = sessionHandler.getParametro(BoSessionParameter.REDIRECT_ACTION_INFO);
			
			for (Informazione info : i)
				addInformazione(info);
			
			sessionHandler.setParametro(BoSessionParameter.REDIRECT_ACTION_INFO, null);
		}
	}

	private void handleRedirectActionMessages() {
		if (sessionHandler.getParametro(BoSessionParameter.REDIRECT_ACTION_MESSAGES) != null) {
			Iterable<Messaggio> i = sessionHandler.getParametro(BoSessionParameter.REDIRECT_ACTION_MESSAGES);

			for (Messaggio m : i)
				addMessaggio(m);

			sessionHandler.setParametro(BoSessionParameter.REDIRECT_ACTION_MESSAGES, null);
		}
	}

	private void handleRedirectActionErrors() {
		if (sessionHandler.getParametro(BoSessionParameter.REDIRECT_ACTION_ERRORS) != null) {
			Iterable<Errore> i = sessionHandler.getParametro(BoSessionParameter.REDIRECT_ACTION_ERRORS);

			for (Errore e : i)
				addErrore(e);

			sessionHandler.setParametro(BoSessionParameter.REDIRECT_ACTION_ERRORS, null);
		}
	}
	
	public String readElencoStruttureAmministrativoContabiliUtenteJson() throws IOException {
		if (sessionHandler
				.getParametro(BoSessionParameter.ELENCO_STRUTTURE_AMMINISTRATIVE_CONTABILI_UTENTE_JSON) == null) {

			List<StrutturaAmministrativoContabile> elencoStruttureAmministrativoContabiliUtente = _readElencoStruttureAmministrativoContabiliUtente();
			
			Collections.sort(elencoStruttureAmministrativoContabiliUtente, StrutturaAmministrativoContabileComparator.INSTANCE);
			
			List<StrutturaAmministrativoContabileWrapper> elencoStruttureAmministrativoContabili = new ArrayList<StrutturaAmministrativoContabileWrapper>();

			for (StrutturaAmministrativoContabile strutturaAmministrativoContabile : elencoStruttureAmministrativoContabiliUtente) {
				elencoStruttureAmministrativoContabili
						.add(new StrutturaAmministrativoContabileWrapper(strutturaAmministrativoContabile));
			}

			sessionHandler.setParametro(BoSessionParameter.ELENCO_STRUTTURE_AMMINISTRATIVE_CONTABILI_UTENTE_JSON,
					JsonConverter.objectToJson(elencoStruttureAmministrativoContabili));
		}

		return sessionHandler.getParametro(BoSessionParameter.ELENCO_STRUTTURE_AMMINISTRATIVE_CONTABILI_UTENTE_JSON);
	}
	
	public List<StrutturaAmministrativoContabile> readElencoStruttureAmministrativoContabiliUtente() {
		if (sessionHandler.getParametro(BoSessionParameter.ELENCO_STRUTTURE_AMMINISTRATIVE_CONTABILI_UTENTE) == null) {
			
			List<StrutturaAmministrativoContabile> elencoStruttureAmministrativoContabili = _readElencoStruttureAmministrativoContabiliUtente();

			deepSortElencoStruttureAmministrativoContabili(elencoStruttureAmministrativoContabili);
			
			sessionHandler.setParametro(BoSessionParameter.ELENCO_STRUTTURE_AMMINISTRATIVE_CONTABILI_UTENTE,
					elencoStruttureAmministrativoContabili);
		}

		return sessionHandler.getParametro(BoSessionParameter.ELENCO_STRUTTURE_AMMINISTRATIVE_CONTABILI_UTENTE);
	}

	private List<StrutturaAmministrativoContabile> _readElencoStruttureAmministrativoContabiliUtente() {
		List<StrutturaAmministrativoContabile> elencoStruttureAmministrativoContabili = readElencoStruttureAmministrativoContabiliUtenteFromAccount();

		return ! elencoStruttureAmministrativoContabili.isEmpty() ? 
				elencoStruttureAmministrativoContabili :
				readElencoStruttureAmministrativoContabiliUtenteFromService(); 
	}

	private List<StrutturaAmministrativoContabile> readElencoStruttureAmministrativoContabiliUtenteFromService() {
		List<StrutturaAmministrativoContabile> elencoStruttureAmministrativoContabili;
		LeggiStrutturaAmminstrativoContabile req = new LeggiStrutturaAmminstrativoContabile();

		req.setAnno(Integer.parseInt(sessionHandler.getAnnoEsercizio()));
		req.setIdEnteProprietario(sessionHandler.getEnte().getUid());
		req.setRichiedente(sessionHandler.getRichiedente());

		LeggiStrutturaAmminstrativoContabileResponse response = classificatoreService.leggiStrutturaAmminstrativoContabile(req);
		
		elencoStruttureAmministrativoContabili = response.getListaStrutturaAmmContabile();
		
		return elencoStruttureAmministrativoContabili;
	}

	private List<StrutturaAmministrativoContabile> readElencoStruttureAmministrativoContabiliUtenteFromAccount() {
		List<StrutturaAmministrativoContabile> res = sessionHandler.getAccount().getStruttureAmministrativeContabili();
		return res != null ? res : new ArrayList<StrutturaAmministrativoContabile>();
	}
	
	private void deepSortElencoStruttureAmministrativoContabili(List<StrutturaAmministrativoContabile> elencoStruttureAmministrativoContabili) {
		for (StrutturaAmministrativoContabile strutturaAmministrativoContabile : elencoStruttureAmministrativoContabili) {
			if (strutturaAmministrativoContabile.getSubStrutture() != null && !strutturaAmministrativoContabile.getSubStrutture().isEmpty()) {
				deepSortElencoStruttureAmministrativoContabili(strutturaAmministrativoContabile.getSubStrutture());
			}
		}

		Collections.sort(elencoStruttureAmministrativoContabili, StrutturaAmministrativoContabileComparator.INSTANCE);
	}
	
	protected void addRedirectActionMessage(Messaggio messaggio) {
		addMessaggio(messaggio);

		sessionHandler.setParametro(BoSessionParameter.REDIRECT_ACTION_MESSAGES, model.getMessaggi());
	}

	protected void addRedirectActionError(Errore errore) {
		addErrore(errore);

		sessionHandler.setParametro(BoSessionParameter.REDIRECT_ACTION_ERRORS, model.getErrori());
	}
	
	//SIAC-8485 da ridefinire a superclasse 
	public boolean hasZTreeMultipli() {
		return Boolean.TRUE;
	}
	
	/**
	 * Metodo di utilit&agrave; per il controllo di non-<code>null</code>it&agrave; per un dato campo.
	 * <br>
	 * Nel caso in cui il campo sia <code>null</code>, aggiunge un errore alla action.
	 * @param campo			il campo da controllare
	 * @param nomeCampo		il nome del campo
	 */
	protected void checkNotNull(Object campo, String nomeCampo) {
		// Non rilancia l'eccezione
		checkNotNull(campo, nomeCampo, false);
	}
	
	/**
	 * Metodo di utilit&agrave; per il controllo di non-<code>null</code>it&agrave; per un dato campo.
	 * <br>
	 * Nel caso in cui il campo sia <code>null</code>, aggiunge un errore alla action.
	 * @param campo          il campo da controllare
	 * @param nomeCampo      il nome del campo
	 * @param throwException se sia da rilanciare un'eccezione
	 * @throws ParamValidationException nel caso in cui la condizione non sia verificata e in cui si sia scelto di rilanciare l'eccezione
	 */
	protected void checkNotNull(Object campo, String nomeCampo, boolean throwException) {
		// Controlla che il campo non sia null
		checkCondition(campo != null, ErroreCore.DATO_OBBLIGATORIO_OMESSO.getErrore(nomeCampo), throwException);
	}
	
	/**
	 * Metodo di utilit&agrave; per il controllo di validit&agrave; di una condizione.
	 * <br>
	 * Nel caso in cui la condizione non sia rispettata, aggiunge l'errore all'interno della action.
	 * @param condition la condizione da verificare
	 * @param errore    l'errore da injettare
	 */
	protected void checkCondition(boolean condition, Errore errore) {
		// Non rilancia l'eccezione
		checkCondition(condition, errore, false);
	}
	
	/**
	 * Metodo di utilit&agrave; per il controllo di validit&agrave; di una condizione e per l'opzionale lancio di un'eccezione.
	 * <br>
	 * Nel caso in cui la condizione non sia rispettata, aggiunge l'errore all'interno della action.
	 * @param condition      la condizione da verificare
	 * @param errore         l'errore da injettare nel caso in cui la condizione sia violata
	 * @param throwException se l'eccezione sia da sollevare
	 * @throws ParamValidationException nel caso in cui la condizione non sia verificata e in cui si sia scelto di rilanciare l'eccezione 
	 */
	protected void checkCondition(boolean condition, Errore errore, boolean throwException) {
		// Controllo della condizione
		if(!condition) {
			// Aggiunge l'errore
			addErrore(errore);
			if(throwException) {
				// Lancia l'errore se richiesto
				throw new ParamValidationException("Error found: " + errore.getTesto());
			}
		}
	}
	
	protected <T> boolean checkResult(T res, String entityChecked) {
		if(res == null) {
			addErrore(ErroreCore.NESSUN_DATO_REPERITO.getErrore(entityChecked));
			return true;
		}
		return false;
	}
	
	protected <T> boolean checkResultList(List<T> list, String entityChecked) {
		if(list == null || list.isEmpty()) {
			addErrore(ErroreCore.NESSUN_DATO_REPERITO.getErrore(entityChecked));
			return true;
		}
		return false;
	}

	@Override
	protected TipoFileIntf getTipoFileIntf(String codiceTipoFile) {
		return BoTipoFileEnum.fromCodice(codiceTipoFile);
	}
}
