/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.gestioneordinativi;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.business.service.common.ProvvedimentoService;
import it.csi.siac.siacboapp.business.service.common.SoggettoService;
import it.csi.siac.siacboapp.business.service.gestioneordinativi.OrdinativoService;
import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.handler.session.BoSessionParameter;
import it.csi.siac.siacboapp.frontend.ui.model.gestioneordinativi.CriteriRicercaOrdinativi;
import it.csi.siac.siacboapp.frontend.ui.model.gestioneordinativi.GestioneOrdinativiModel;
import it.csi.siac.siacboapp.frontend.ui.util.AzioneEnum;
import it.csi.siac.siaccorser.model.errore.ErroreCore;
import it.csi.siac.siacfin2ser.model.TipoOrdinativo;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class GestioneOrdinativiAction extends GenericBoAction<GestioneOrdinativiModel> {
	private static final long serialVersionUID = -1523514127549195614L;

	@Autowired
	private OrdinativoService ordinativoService;

	@Autowired
	private ProvvedimentoService provvedimentoService;

	@Autowired
	private SoggettoService soggettoService;
	
	
	enum StatoOrdinativoEnum {
		INSERITO("I"),
		TRASMESSO("T"),
		FIRMATO("F"),
		QUIETANZATO("Q"),
		ANNULLATO("A")
		;
		
		private String codice;

		StatoOrdinativoEnum(String codice) {
			this.codice = codice;
		}

		public String getCodice() {
			return codice;
		}
	}

	@Override
	public void prepare() throws Exception {
		final String methodName = "prepare";

		log.debugStart(methodName, "Preparazione della action");

		super.prepare();

		int idEnte = sessionHandler.getEnte().getUid();

		model.setElencoTipiOrdinativo(ordinativoService.readElencoTipiOrdinativo(idEnte));
		setElencoCodiciFlussoOrdinativi(idEnte, sessionHandler.getBilancio().getUid());
		setElencoCodiciDistintaOrdinativi(idEnte);
		model.setElencoStatiOrdinativo(ordinativoService.readElencoStatiOrdinativo(idEnte));
		model.setElencoTipiAttoAmministrativo(provvedimentoService.readElencoTipiAttoAmministrativo(idEnte));
		model.setElencoClassiSoggetto(soggettoService.readElencoClassiSoggetto(idEnte));
		model.setSiopePlus(ordinativoService.readSiopePlus(idEnte));
		
		log.debugEnd(methodName, "");
	}

	@Override
	protected void initModel() {
		super.initModel();

		model.setTitolo(sessionHandler.getAzione().getTitolo());
	}

	@Override
	public String execute() throws Exception {
		model.setElencoStruttureAmministrativoContabiliJson(readElencoStruttureAmministrativoContabiliUtenteJson());

		return SUCCESS;
	}
	
	private void validateGestioneOrdinativi() {
		if (StringUtils.isBlank(model.getCriteri().getCodiceTipo()))
			addErrore(ErroreCore.DATO_OBBLIGATORIO_OMESSO.getErrore("tipo"));
	}

	public void validatePreparaVB() {
		validateGestioneOrdinativi();
	}

	public void validateSblocca() {
		validateGestioneOrdinativi();
	}

	public void validateSbloccaSiopePlus() {
		validateGestioneOrdinativi();
	}

	public void validateTrasmetti() {
		validateGestioneOrdinativi();
	}

	public void validateAttivaTrasmissione() {
		validateGestioneOrdinativi();
	}

	public void validateDisattivaTrasmissione() {
		validateGestioneOrdinativi();
	}

	public void validateTrasmettiSiopePlus() {
		validateGestioneOrdinativi();
	}

	public void validateAssociaProvvisoriCassa() {
		if (!isAzioneConsentita(AzioneEnum.ASSOCIA_PROVVISORI_CASSA)) {
			addErrore(ErroreCore.OPERAZIONE_NON_CONSENTITA.getErrore("utente senza privilegi per 'Associa provvisori di cassa'"));
			
			return;
		}

		if (!validateCodiciStatoAssociaProvvisoriCassa()) {
			addErrore(ErroreCore.INCONGRUENZA_NEI_PARAMETRI.getErrore("per 'Associa provvisori di cassa' sono selezionabili solo ordinativi in stato INSERITO"));
		}
		
		validateGestioneOrdinativi();
	}

	private boolean validateCodiciStatoAssociaProvvisoriCassa() {
		
		if (model.getCriteri().getCodiciStato() == null) {
			return true;
		}
		
		for (String cs : model.getCriteri().getCodiciStato()) {
			if (StatoOrdinativoEnum.INSERITO.getCodice().equals(cs)) {
				return true;
			}
		}

		return false;
	}

	public String sblocca() throws Exception {
		return sblocca("sbloccaOrdinativi");
	}

	public String sbloccaSiopePlus() throws Exception {
		return sblocca("sbloccaOrdinativiSiopePlus");
	}

	private String sblocca(String result) {
		CriteriRicercaOrdinativi criteri = model.getCriteri();

		criteri.setDataTrasmissioneOilPresente(1);
		criteri.setCodiciStatoEsclusi(new String[] { StatoOrdinativoEnum.QUIETANZATO.getCodice(), StatoOrdinativoEnum.INSERITO.getCodice() });
		criteri.setIncludiVBDaTrasmettere(null);
		criteri.setIncludiAnnulliDaTrasmettere(null);

		storeCriteriIntoSession();

		return result;
	}

	public String trasmetti() throws Exception {
		CriteriRicercaOrdinativi criteri = model.getCriteri();
		
		criteri.setDataTrasmissioneOilPresente(null);
		criteri.setCodiciStatoEsclusi(null);

		storeCriteriIntoSession();

		return "trasmettiOrdinativi";
	}
	
	public String trasmettiSiopePlus() throws Exception {
		CriteriRicercaOrdinativi criteri = model.getCriteri();
		
		criteri.setDataTrasmissioneOilPresente(null);
		criteri.setCodiciStatoEsclusi(null);

		storeCriteriIntoSession();

		return "trasmettiOrdinativiSiopePlus";
	}
	
	public String preparaVB() throws Exception {
		CriteriRicercaOrdinativi criteri = model.getCriteri();

		criteri.setDataTrasmissioneOilPresente(1);
		criteri.setCodiciStatoEsclusi(new String[] { StatoOrdinativoEnum.ANNULLATO.getCodice() });
		criteri.setIncludiVBDaTrasmettere(null);
		criteri.setIncludiAnnulliDaTrasmettere(null);

		storeCriteriIntoSession();
		
		return "preparaVBOrdinativi";
	}


	public String attivaTrasmissione() throws Exception {
		CriteriRicercaOrdinativi criteri = model.getCriteri();

		criteri.setDaTrasmettere(0);
		
		storeCriteriIntoSession();
		
		return "attivaTrasmissioneOrdinativi";
	}

	public String disattivaTrasmissione() throws Exception {
		CriteriRicercaOrdinativi criteri = model.getCriteri();

		criteri.setCodiciStato(new String[] { StatoOrdinativoEnum.INSERITO.getCodice() });
		criteri.setDaTrasmettere(1);
		
		storeCriteriIntoSession();
		
		return "disattivaTrasmissioneOrdinativi";
	}

	
	public String associaProvvisoriCassa() throws Exception {
		CriteriRicercaOrdinativi criteri = model.getCriteri();

		criteri.setCodiciStato(new String[] { StatoOrdinativoEnum.INSERITO.getCodice() });
		criteri.setEscludiCollegatiProvvisoriCassa(true);
		
		storeCriteriIntoSession();
		
		return "associaProvvisoriCassa";
	}

	
	
	private void storeCriteriIntoSession() {
		sessionHandler.setParametro(BoSessionParameter.GESTIONE_ORDINATIVI_CRITERI_RICERCA_ORDINATIVI,
				model.getCriteri());
	}
	
	private void setElencoCodiciFlussoOrdinativi(int idEnte, int idBilancio) {
		model.setElencoCodiciFlussoOrdinativiEntrata(ordinativoService.readElencoCodiciFlussoOrdinativi(idEnte, idBilancio, TipoOrdinativo.INCASSO));
		model.setElencoCodiciFlussoOrdinativiSpesa(ordinativoService.readElencoCodiciFlussoOrdinativi(idEnte, idBilancio, TipoOrdinativo.PAGAMENTO));
		
	}
	
	private void setElencoCodiciDistintaOrdinativi(int idEnte) {
		model.setElencoCodiciDistintaOrdinativiEntrata(ordinativoService.readElencoCodiciDistintaOrdinativi(idEnte, "E"));
		model.setElencoCodiciDistintaOrdinativiSpesa(ordinativoService.readElencoCodiciDistintaOrdinativi(idEnte, "S"));
		
	}
}
