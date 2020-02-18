/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.limiteimpegnabile;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.business.service.common.CapitoloService;
import it.csi.siac.siacboapp.business.service.common.PeriodoService;
import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.model.common.TipoImportoCapitolo;
import it.csi.siac.siacboapp.frontend.ui.model.limiteimpegnabile.ImportiLimiteImpegnabileModel;
import it.csi.siac.siacboapp.frontend.ui.model.messaggio.MessaggioBo;
import it.csi.siac.siacboapp.integration.entity.SiacTBilElem;
import it.csi.siac.siacboapp.integration.entity.SiacTBilElemDet;
import it.csi.siac.siacboapp.integration.entity.SiacTEnteProprietario;
import it.csi.siac.siacboapp.integration.repository.SiacTBilElemDetRepository;
import it.csi.siac.siacboapp.util.NumberFormatter;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class ImportiLimiteImpegnabileAction extends GenericBoAction<ImportiLimiteImpegnabileModel> {
	private static final long serialVersionUID = 4745502029978957849L;

	@Autowired
	private CapitoloService capitoloService;

	@Autowired
	private PeriodoService periodoService;

	private Integer cuid;

	@Override
	public void prepare() throws Exception {
		final String methodName = "prepare";

		log.debugStart(methodName, "Preparazione della action");

		super.prepare();

		log.debugEnd(methodName, "");
	}

	@Override
	protected void initModel() {
		super.initModel();

		model.setTitolo(sessionHandler.getAzione().getTitolo());
	}

	@Override
	public String execute() throws Exception {
		if (cuid != null) {
			SiacTBilElem capitolo = capitoloService.readCapitolo(cuid);

			model.setAnno(Integer.valueOf(capitolo.getBilancio().getPeriodo().getAnno()));

			model.setImportiCapitolo(capitoloService.readImportiCapitolo(cuid,
					Arrays.asList(TipoImportoCapitolo.MASSIMO_IMPEGNABILE.getCodice()),
					Arrays.asList(String.format("anno%s", model.getAnno()),
							String.format("anno%s", model.getAnno() + 1),
							String.format("anno%s", model.getAnno() + 2))));
		}

		return SUCCESS;
	}

	public void validateUpdate() {
		checkFormData();
		checkImportiCompetenza();
	}

	private void checkImportiCompetenza() {
		Map<String, SiacTBilElemDet> importiCompetenzaMap = capitoloService.readImportiCapitolo(cuid,
				Arrays.asList(TipoImportoCapitolo.COMPETENZA.getCodice()),
				Arrays.asList(String.format("anno%s", model.getAnno()),
						String.format("anno%s", model.getAnno() + 1),
						String.format("anno%s", model.getAnno() + 2)));
		Map<Integer, BigDecimal> importiImpegnatiMap = capitoloService.readImportiMovimentiGestione(cuid,
				Arrays.asList(model.getAnno(),
						Integer.valueOf(model.getAnno().intValue() + 1),
						Integer.valueOf(model.getAnno().intValue() + 2)));
		
		checkImportoCompetenza(importiCompetenzaMap, importiImpegnatiMap, model.getImportoAnno(), 0);
		checkImportoCompetenza(importiCompetenzaMap, importiImpegnatiMap, model.getImportoAnno1(), 1);
		checkImportoCompetenza(importiCompetenzaMap, importiImpegnatiMap, model.getImportoAnno2(), 2);
	}

	private void checkImportoCompetenza(Map<String, SiacTBilElemDet> importiCompetenzaMap, Map<Integer, BigDecimal> importiImpegnatiMap, String importoStr, int annoOffset) {
		if (StringUtils.isBlank(importoStr)) {
			return;
		}
		
		Integer newAnno = Integer.valueOf(model.getAnno().intValue() + annoOffset);
		try {
			SiacTBilElemDet siacTBilElemDet = importiCompetenzaMap
					.get(String.format(SiacTBilElemDetRepository.MAP_IMPORTI_KEY_PATTERN,
							TipoImportoCapitolo.COMPETENZA.getCodice(), newAnno));

			BigDecimal importoCompetenza = siacTBilElemDet != null ? siacTBilElemDet.getImporto() : BigDecimal.ZERO;

			BigDecimal importoNum = BigDecimal.valueOf(NumberFormatter.importoToDouble(importoStr));

			if (importoNum.compareTo(importoCompetenza) > 0) {
				addErrore(ErroreCore.OPERAZIONE_NON_CONSENTITA.getErrore(
						String.format("il limite impegnabile dell'anno %d supera lo stanziamento di competenza (%s Euro)",
								newAnno, NumberFormatter.numberToImporto(importoCompetenza))));
			}
			BigDecimal importoImpegnatoRegistrato = importiImpegnatiMap.get(newAnno);
			BigDecimal importoImpegnato = importoImpegnatoRegistrato != null ? importoImpegnatoRegistrato : BigDecimal.ZERO;
			
			if(importoNum.compareTo(importoImpegnato) < 0) {
				addErrore(ErroreCore.OPERAZIONE_NON_CONSENTITA.getErrore(
						String.format("il limite impegnabile dell'anno %d e' inferiore allo stanziamento impegnato (%s Euro)",
								newAnno, NumberFormatter.numberToImporto(importoImpegnato))));
			}
			
			
		} catch (Exception e) {
			addErrore(ErroreCore.ERRORE_DI_SISTEMA.getErrore(e.getMessage()));
		}
	}

	private void checkFormData() {
		checkImportoCapitolo(model.getImportoAnno(), "");
		checkImportoCapitolo(model.getImportoAnno1(), "1");
		checkImportoCapitolo(model.getImportoAnno2(), "2");
	}

	private void checkImportoCapitolo(String value, String anno) {
		if (StringUtils.isNotBlank(value) && !value.matches("^\\d{1,3}(\\.\\d{3})*\\,?\\d*$"))
			addErrore(
					ErroreCore.FORMATO_NON_VALIDO.getErrore(String.format("importo anno %s (%s)", anno, value), value));
	}
	
	public String update() {
		List<SiacTBilElemDet> elencoImportiCapitolo = new ArrayList<SiacTBilElemDet>();

		try {
			if (StringUtils.isNotBlank(model.getImportoAnno()))
				elencoImportiCapitolo.add(buildImportoCapitolo(model.getIdImportoAnno(), model.getImportoAnno(), 0));
			else if (model.getIdImportoAnno() != null)
				capitoloService.deleteImportoCapitolo(model.getIdImportoAnno());

			if (StringUtils.isNotBlank(model.getImportoAnno1()))
				elencoImportiCapitolo.add(buildImportoCapitolo(model.getIdImportoAnno1(), model.getImportoAnno1(), 1));
			else if (model.getIdImportoAnno1() != null)
				capitoloService.deleteImportoCapitolo(model.getIdImportoAnno1());

			if (StringUtils.isNotBlank(model.getImportoAnno2()))
				elencoImportiCapitolo.add(buildImportoCapitolo(model.getIdImportoAnno2(), model.getImportoAnno2(), 2));
			else if (model.getIdImportoAnno2() != null)
				capitoloService.deleteImportoCapitolo(model.getIdImportoAnno2());

			capitoloService.updateImportiCapitolo(elencoImportiCapitolo);

			addRedirectActionMessage(MessaggioBo.OPERAZIONE_EFFETTUATA_CORRETTAMENTE.getMessaggio());
		} catch (Exception e) {
			addErrore(ErroreCore.ERRORE_DI_SISTEMA.getErrore(e.getMessage()));
		}

		return "risultatiRicercaLimiteImpegnabile";
	}

	private SiacTBilElemDet buildImportoCapitolo(Integer uid, String value, int annoOffset) throws ParseException {
		SiacTBilElemDet siacTBilElemDet;

		int idEnte = sessionHandler.getEnte().getUid();

		if (uid == null) {
			siacTBilElemDet = new SiacTBilElemDet();
			siacTBilElemDet.setElementoBilancio(capitoloService.readCapitolo(cuid));
			siacTBilElemDet.setTipo(capitoloService.readTipoImportoCapitolo(idEnte,
					TipoImportoCapitolo.MASSIMO_IMPEGNABILE.getCodice()));
			siacTBilElemDet.setPeriodo(periodoService.readPeriodo(idEnte, Integer
					.toString(Integer.parseInt(siacTBilElemDet.getElementoBilancio().getBilancio().getPeriodo().getAnno()) + annoOffset)));
		} else
			siacTBilElemDet = capitoloService.readImportoCapitolo(uid);

		siacTBilElemDet.setImporto(BigDecimal.valueOf(NumberFormatter.importoToDouble(value)));

		siacTBilElemDet.setEnteProprietario(new SiacTEnteProprietario(idEnte));
		siacTBilElemDet.setLoginOperazione(sessionHandler.getOperatore().getCodiceFiscale());
		siacTBilElemDet.setDataModifica(new Date());

		return siacTBilElemDet;
	}

	public Integer getCuid() {
		return cuid;
	}

	public void setCuid(Integer cuid) {
		this.cuid = cuid;
	}

}
