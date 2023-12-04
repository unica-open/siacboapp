/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.entity;

import java.util.List;
import java.util.SortedSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

@Entity
@Table(name = "bko_t_system_table")
public class BkoTSystemTable extends BkoBase {
	private static final long serialVersionUID = -3978358667752612574L;

	@Id
	@Column(name = "st_id")
	private Integer id;

	@Column(name = "st_table_oid")
	private Integer tableOid;

	@Column(name = "st_table_name")
	private String name;

	@Column(name = "st_table_label")
	private String label;

	@OneToMany(mappedBy = "table", fetch = FetchType.LAZY)
	@OrderBy("ordinalPosition ASC")
	@Sort(type=SortType.NATURAL)
	private SortedSet<BkoTSystemColumn> columns;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinTable(name = "bko_r_system_table_type", 
		joinColumns = @JoinColumn(name = "st_id", referencedColumnName="st_id"), 
		inverseJoinColumns = @JoinColumn(name = "stt_id", referencedColumnName="stt_id")
	)
	private BkoDSystemTableType type;

	@OneToMany
	@JoinTable(name = "bko_r_system_table_action", joinColumns = @JoinColumn(name = "st_id"), inverseJoinColumns = @JoinColumn(name = "sa_id"))
	private List<BkoDSystemTableAction> actions;

	public BkoTSystemTable(String schema, String name) {
		this();
		this.name = name;
	}

	public BkoTSystemTable() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public SortedSet<BkoTSystemColumn> getColumns() {
		return columns;
	}

	public void setColumns(SortedSet<BkoTSystemColumn> columns) {
		this.columns = columns;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTableOid() {
		return tableOid;
	}

	public void setTableOid(Integer tableOid) {
		this.tableOid = tableOid;
	}

	public BkoDSystemTableType getType() {
		return type;
	}

	public void setType(BkoDSystemTableType type) {
		this.type = type;
	}

	public List<BkoDSystemTableAction> getActions() {
		return actions;
	}

	public void setActions(List<BkoDSystemTableAction> actions) {
		this.actions = actions;
	}

}