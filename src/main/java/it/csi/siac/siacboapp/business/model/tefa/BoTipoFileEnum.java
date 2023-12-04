/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.business.model.tefa;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.csi.siac.siacboapp.business.util.handler.tefa.TipoFileTefaHandler;
import it.csi.siac.siaccommon.util.CoreUtil;
import it.csi.siac.siaccorser.model.file.TipoFileHandler;
import it.csi.siac.siaccorser.model.file.TipoFileIntf;

public enum BoTipoFileEnum implements TipoFileIntf {
	
	FLUSSO_TEFA(TipoFileTefaHandler.class);
	
	@Autowired
	private ApplicationContext appCtx;
	
	private Class<? extends TipoFileHandler> tipoFileHandlerClass;

	private static final Map<String, ? extends TipoFileIntf> revMap = CoreUtil.getEnumMap(BoTipoFileEnum.class);

	BoTipoFileEnum(Class<? extends TipoFileTefaHandler> tipoFileHandlerClass) {
		this.tipoFileHandlerClass = tipoFileHandlerClass;
	}

	public static TipoFileIntf fromCodice(String codice) {
		return revMap.get(codice);
	}
	
	@Override
	public TipoFileHandler getHandler() {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		return appCtx.getBean(tipoFileHandlerClass);
	}
}
