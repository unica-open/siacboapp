/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.propostepreliminari;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.siac.siacboapp.business.service.common.CapitoloService;
import it.csi.siac.siacboapp.business.service.propostepreliminari.PropostaPreliminareService;
import it.csi.siac.siacboapp.frontend.ui.handler.session.BoSessionParameter;
import it.csi.siac.siacboapp.frontend.ui.model.common.TipoImportoCapitolo;
import it.csi.siac.siacboapp.frontend.ui.model.propostepreliminari.PropostaPreliminareModel;
import it.csi.siac.siacboapp.integration.entity.SiacTBilElem;
import it.csi.siac.siacboapp.integration.entity.SiacTEnteProprietario;
import it.csi.siac.siacboapp.integration.entity.SiacTPropostaPreliminare;
import it.csi.siac.siacboapp.util.NumberFormatter;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

public abstract class PropostaPreliminareAction extends BasePropostePreliminariAction<PropostaPreliminareModel> {

	private static final long serialVersionUID = 4968480018698793654L;

	@Autowired
	private PropostaPreliminareService propostaPreliminareService;

	@Autowired
	private CapitoloService capitoloService;

	private Integer puid;
	private Integer cuid;

	@PostConstruct
	private void init() {
		tipoProposta = StringUtils.substringBetween(this.getClass().getName(), "PropostaPreliminare", "Action")
				.toLowerCase();
	}

	@Override
	public void prepare() throws Exception {
		final String methodName = "prepare";

		log.debugStart(methodName, "Preparazione della action");

		super.prepare();

		List<SiacTBilElem> elencoCapitoli = sessionHandler
				.getParametro(BoSessionParameter.GESTIONE_PROPOSTE_PRELIMINARI_ELENCO_CAPITOLI);
		model.setElencoCapitoli(elencoCapitoli);

		if (puid != null) {
			SiacTPropostaPreliminare proposta = propostaPreliminareService.readPropostaPreliminare(puid);

			model.setProposta(proposta);
			model.setImporti(proposta);

			if (proposta.getCapitolo() != null)
				model.getElencoCapitoli().add(0, proposta.getCapitolo());

		} else if (cuid != null) {
			model.setProposta(capitoloService.readCapitolo(cuid));
			
			SiacTPropostaPreliminare proposta = model.getProposta();
			
			proposta.setAnno(Integer.parseInt(sessionHandler.getAnnoEsercizio()));
			
			model.setImporti(capitoloService.readImportiCapitolo(cuid,
					Arrays.asList(TipoImportoCapitolo.COMPETENZA.getCodice(), TipoImportoCapitolo.RESIDUO.getCodice(),
							TipoImportoCapitolo.CASSA.getCodice()),
					Arrays.asList(String.format("anno%s", proposta.getAnno()),
							String.format("anno%s", proposta.getAnno() + 1),
							String.format("anno%s", proposta.getAnno() + 2))));
		} else {
			SiacTPropostaPreliminare proposta = new SiacTPropostaPreliminare();
			proposta.setAnno(Integer.parseInt(sessionHandler.getAnnoEsercizio()));

			model.setProposta(proposta);
		}

		log.debugEnd(methodName, "");
	}

	@Override
	protected void initModel() {
		super.initModel();

		model.setTitolo(sessionHandler.getAzione().getTitolo());
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public void validateUpdate() throws Exception {
		checkFormData();
	}

	public void validateCreate() throws Exception {
		checkFormData();
	}

	private void checkFormData()
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, ParseException {
		if (StringUtils.isBlank(model.getProposta().getDescrizione()))
			addErrore(ErroreCore.DATO_OBBLIGATORIO_OMESSO.getErrore("Descrizione"));

		for (String tipoImporto : PropostaPreliminareModel.ELENCO_TIPI_IMPORTO)
			for (String offsetAnno : PropostaPreliminareModel.ELENCO_OFFSET_ANNO) {
				String property = String.format("importo%sAnno%s", tipoImporto, offsetAnno);
				String value = BeanUtils.getSimpleProperty(model, property);

				if (StringUtils.isNotBlank(value)) {
					if (value.matches("^\\d{1,3}(\\.\\d{3})*\\,?\\d*$"))
						BeanUtils.setProperty(model.getProposta(), property,
								BigDecimal.valueOf(NumberFormatter.importoToDouble(value)));
					else
						addErrore(ErroreCore.FORMATO_NON_VALIDO.getErrore(
								String.format("importo %s anno %s", tipoImporto, model.offsetToAnno(offsetAnno)),
								value));
				}
			}
	}

	public String update() {
		SiacTPropostaPreliminare proposta = initProposta();
		SiacTPropostaPreliminare proposta0 = propostaPreliminareService.readPropostaPreliminare(proposta.getUid());

		proposta.setStato(proposta0.getStato());

		propostaPreliminareService.updatePropostaPreliminare(proposta);

		addSuccessMessage();

		return "elencoPropostePreliminari" + capitalize(tipoProposta);
	}

	public String create() {
		SiacTPropostaPreliminare proposta = initProposta();

		propostaPreliminareService.createPropostaPreliminare(proposta);

		addSuccessMessage();

		return "elencoPropostePreliminari" + capitalize(tipoProposta);
	}

	private SiacTPropostaPreliminare initProposta() {
		SiacTPropostaPreliminare proposta = model.getProposta();

		String codiceFiscale = sessionHandler.getOperatore().getCodiceFiscale();
		int enteId = sessionHandler.getEnte().getUid();

		proposta.setEnteProprietario(new SiacTEnteProprietario(enteId));
		proposta.setLoginOperazione(codiceFiscale);

		return proposta;
	}

	public Integer getPuid() {
		return puid;
	}

	public void setPuid(Integer puid) {
		this.puid = puid;
	}

	public Integer getCuid() {
		return cuid;
	}

	public void setCuid(Integer cuid) {
		this.cuid = cuid;
	}
}
