/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
CREATE SEQUENCE siac.siac_d_proposta_preliminare_stato_prop_stato_id_seq
  INCREMENT 1 MINVALUE 1
  MAXVALUE 9223372036854775807 START 1
  CACHE 1;



CREATE TABLE siac.siac_d_proposta_preliminare_stato (
  prop_stato_id INTEGER DEFAULT nextval('siac_d_proposta_preliminare_stato_prop_stato_id_seq'::regclass) NOT NULL,
  prop_stato_code VARCHAR(200) NOT NULL,
  prop_stato_desc VARCHAR(500) NOT NULL,
  validita_inizio TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  validita_fine TIMESTAMP WITHOUT TIME ZONE,
  ente_proprietario_id INTEGER NOT NULL,
  data_creazione TIMESTAMP WITHOUT TIME ZONE DEFAULT now() NOT NULL,
  data_modifica TIMESTAMP WITHOUT TIME ZONE DEFAULT now() NOT NULL,
  data_cancellazione TIMESTAMP WITHOUT TIME ZONE,
  login_operazione VARCHAR(200) NOT NULL,
  CONSTRAINT pk_siac_d_prop_stato PRIMARY KEY(prop_stato_id),
  CONSTRAINT siac_t_ente_proprietario_siac_d_proposta_preliminare_stato FOREIGN KEY (ente_proprietario_id)
    REFERENCES siac.siac_t_ente_proprietario(ente_proprietario_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    NOT DEFERRABLE
)
WITH (oids = false);






CREATE SEQUENCE siac.siac_t_proposta_preliminare_prop_id_seq
  INCREMENT 1 MINVALUE 1
  MAXVALUE 9223372036854775807 START 1
  CACHE 1;

--  drop TABLE siac.siac_t_proposta_preliminare

CREATE TABLE siac.siac_t_proposta_preliminare (
  prop_id INTEGER DEFAULT nextval('siac_t_proposta_preliminare_prop_id_seq'::regclass) NOT NULL,
  prop_stato_id INTEGER NOT NULL,
  bil_elem_id INTEGER,
  prop_numero INTEGER,
  prop_desc VARCHAR,
  prop_desc2 VARCHAR,
  anno INTEGER NOT NULL,
  imp_competenza_anno NUMERIC,
  imp_competenza_anno_1 NUMERIC,
  imp_competenza_anno_2 NUMERIC,
  imp_cassa_anno NUMERIC,
  imp_cassa_anno_1 NUMERIC,
  imp_cassa_anno_2 NUMERIC,
  imp_residuo_anno NUMERIC,
  imp_residuo_anno_1 NUMERIC,
  imp_residuo_anno_2 NUMERIC,
  note VARCHAR(1000),
  validita_inizio TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  validita_fine TIMESTAMP WITHOUT TIME ZONE,
  ente_proprietario_id INTEGER NOT NULL,
  data_creazione TIMESTAMP WITHOUT TIME ZONE DEFAULT now() NOT NULL,
  data_modifica TIMESTAMP WITHOUT TIME ZONE DEFAULT now() NOT NULL,
  data_cancellazione TIMESTAMP WITHOUT TIME ZONE,
  login_operazione VARCHAR(200) NOT NULL,
  CONSTRAINT pk_siac_t_proposta_preliminare PRIMARY KEY(prop_id),
  CONSTRAINT siac_d_proposta_preliminare_stato_siac_t_proposta_preliminare FOREIGN KEY (prop_stato_id)
    REFERENCES siac.siac_d_proposta_preliminare_stato(prop_stato_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    NOT DEFERRABLE,
  CONSTRAINT siac_t_bil_elem_siac_t_proposta_preliminare FOREIGN KEY (bil_elem_id)
    REFERENCES siac.siac_t_bil_elem(elem_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    NOT DEFERRABLE,
  CONSTRAINT siac_t_ente_proprietario_siac_t_proposta_preliminare FOREIGN KEY (ente_proprietario_id)
    REFERENCES siac.siac_t_ente_proprietario(ente_proprietario_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    NOT DEFERRABLE
)
WITH (oids = false);