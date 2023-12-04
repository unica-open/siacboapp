/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.business.util.handler.tefa;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.siac.siacboapp.business.service.tefa.TefaService;
import it.csi.siac.siaccommon.util.MimeType;
import it.csi.siac.siaccommonapp.action.DownloadFileAction;
import it.csi.siac.siaccorser.model.file.File;
import it.csi.siac.siaccorser.model.file.TipoFileHandler;

@Component
public class TipoFileTefaHandler implements TipoFileHandler {

	@Autowired
	private TefaService tefaService;
	
	@Override
	public String[] getAzioni() {
		return new String[] { "estrai per versamenti", "estrai per upload"};
	}

	
	// FIXME 
	@Override
	public Object execute(Integer indexAzioneTipoFile, Object... parameters) throws Exception {
		Map<String, Object> request = (Map<String, Object>) parameters[0];
		Integer idEnte = (Integer) parameters[1];
		Integer idFile = (Integer) parameters[2];
		Integer anno = Integer.parseInt((String) parameters[3]);
		String nomeFile = (String) parameters[4];
		
		switch (indexAzioneTipoFile) {
		case 0:
			request.put(DownloadFileAction.FILE, 
					new File(String.format("%s-versamenti.xls", nomeFile), MimeType.CSV, tefaService.estraiVersamenti(idEnte, idFile, anno)));

			return "downloadFile";

		case 1:
			request.put(DownloadFileAction.FILE, 
					new File(String.format("%s-comune_anno_rif.xls", nomeFile), MimeType.CSV, tefaService.estraiComuneAnnoRif(idEnte, idFile, anno)));

			return "downloadFile";

		default:
			return null;
		}
	}

}
