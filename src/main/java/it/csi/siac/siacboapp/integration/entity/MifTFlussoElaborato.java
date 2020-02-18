/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.entity;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "mif_t_flusso_elaborato")
public class MifTFlussoElaborato extends SiacTEnteBase {
	private static final long serialVersionUID = -5714315995852927562L;

	@Id
	@Column(name = "flusso_elab_mif_id")
	private Integer uid;

	@Column(name = "flusso_elab_mif_codice_flusso_oil")
	private String numero;

	@ManyToOne
	@JoinColumn(name = "flusso_elab_mif_tipo_id")
	private MifDFlussoElaboratoTipo tipo;

	@OneToMany(mappedBy = "flussoElaborato")
	private Set<MifTOrdinativoSpesa> ordinativiSpesaMif;

	@OneToMany(mappedBy = "flussoElaborato")
	private Set<MifTOrdinativoEntrata> ordinativiEntrataMif;

	@Column(name = "flusso_elab_mif_data")
	private Date data;

	@Override
	public Integer getUid() {
		return uid;
	}

	@Override
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public MifDFlussoElaboratoTipo getTipo() {
		return tipo;
	}

	public void setTipo(MifDFlussoElaboratoTipo tipo) {
		this.tipo = tipo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getAnno() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getDataCreazione());

		return cal.get(Calendar.YEAR);
	}

	public Set<MifTOrdinativoSpesa> getOrdinativiSpesaMif() {
		return ordinativiSpesaMif;
	}

	public void setOrdinativiSpesaMif(Set<MifTOrdinativoSpesa> ordinativiSpesaMif) {
		this.ordinativiSpesaMif = ordinativiSpesaMif;
	}

	public Set<MifTOrdinativoEntrata> getOrdinativiEntrataMif() {
		return ordinativiEntrataMif;
	}

	public void setOrdinativiEntrataMif(Set<MifTOrdinativoEntrata> ordinativiEntrataMif) {
		this.ordinativiEntrataMif = ordinativiEntrataMif;
	}
	
	
}