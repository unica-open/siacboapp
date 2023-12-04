/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dad.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.siac.siacboapp.integration.dad.mapper.base.BaseEntityWrapperMapper;
import it.csi.siac.siacboapp.integration.entity.SiacRMovgestClass;
import it.csi.siac.siacboapp.integration.entity.SiacTClass;
import it.csi.siac.siacboapp.integration.entity.SiacTMovgest;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTClassWrapper;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTMovgestWrapper;
import it.csi.siac.siaccommon.util.collections.CollectionUtil;
import it.csi.siac.siaccommon.util.collections.Filter;
import it.csi.siac.siaccommon.util.collections.Function;
import it.csi.siac.siaccommonser.util.entity.EntityUtil;
import it.csi.siac.siaccorser.model.TipoClassificatoreEnum;


@Component
public class SiacTMovgestSacMapper extends BaseEntityWrapperMapper<SiacTMovgest, SiacTMovgestWrapper> {

	@Override
	public void map(SiacTMovgest o, SiacTMovgestWrapper ow) {
		ow.setStrutturaAmministrativoContabile(getSac(o.getTestata().getClassi()));
	}
	
	public SiacTClassWrapper getSac(List<SiacRMovgestClass> classi) {
		
		SiacTClass siacTClass = CollectionUtil.findFirst(
				EntityUtil.getAllValidMappedBy(classi, new Function<SiacRMovgestClass, SiacTClass>() {
					
					@Override
					public SiacTClass map(SiacRMovgestClass source) {
						return source.getClasse();
					}}
				),
				new Filter<SiacTClass>() {

					@Override
					public boolean isAcceptable(SiacTClass source) {
						return TipoClassificatoreEnum.CENTRO_DI_CONTROLLO.getCodice().equals(source.getTipo().getCodice());
					}}
		);
		
		if (siacTClass == null) {
			return null;
		}
		
		SiacTClassWrapper siacTClassWrapper = new SiacTClassWrapper();

		siacTClassWrapper.setCodice(siacTClass.getCodice());
		siacTClassWrapper.setUid(siacTClass.getUid());
	
		return siacTClassWrapper;
	}
}
