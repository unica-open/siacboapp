/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dad.mapper;

import java.util.Map;

import org.springframework.stereotype.Component;

import it.csi.siac.siacboapp.integration.dad.mapper.base.BaseMapToEntityWrapperMapper;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTModpagWrapper;

@Component
public class ModalitaPagamentoSoggettoMapper extends BaseMapToEntityWrapperMapper<SiacTModpagWrapper> {

	@Override
	public void map(Map<String, Object> m, SiacTModpagWrapper ow) {
		// ow.setTipoAccredito(null);
	}
}
