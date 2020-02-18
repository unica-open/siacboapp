/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.csi.siac.siacboapp.frontend.ui.util.comparator.StrutturaAmministrativoContabileComparator;
import it.csi.siac.siaccorser.model.StrutturaAmministrativoContabile;

public class StrutturaAmministrativoContabileWrapper {
	private Integer uid;
	private String descrizioneCompleta;
	private List<StrutturaAmministrativoContabileWrapper> sottoElementi;

	public StrutturaAmministrativoContabileWrapper(StrutturaAmministrativoContabile strutturaAmministrativoContabile) {
		this.uid = strutturaAmministrativoContabile.getUid();
		this.descrizioneCompleta = String.format("%s - %s", strutturaAmministrativoContabile.getCodice(), strutturaAmministrativoContabile.getDescrizione()) ;

		this.sottoElementi = wrapChildren(strutturaAmministrativoContabile.getSubStrutture());
	}

	private List<StrutturaAmministrativoContabileWrapper> wrapChildren(
			List<StrutturaAmministrativoContabile> subStrutture) {
		List<StrutturaAmministrativoContabileWrapper> list = new ArrayList<StrutturaAmministrativoContabileWrapper>();

		Collections.sort(subStrutture, StrutturaAmministrativoContabileComparator.INSTANCE);

		for (StrutturaAmministrativoContabile strutturaAmministrativoContabile : subStrutture)
			list.add(new StrutturaAmministrativoContabileWrapper(strutturaAmministrativoContabile));

		return list;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
	public List<StrutturaAmministrativoContabileWrapper> getSottoElementi() {
		return sottoElementi;
	}

	public void setSottoElementi(List<StrutturaAmministrativoContabileWrapper> sottoElementi) {
		this.sottoElementi = sottoElementi;
	}

	public String getDescrizioneCompleta() {
		return descrizioneCompleta;
	}

	public void setDescrizioneCompleta(String descrizioneCompleta) {
		this.descrizioneCompleta = descrizioneCompleta;
	}
}
