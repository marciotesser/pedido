CREATE TABLE pedido
(
    id_pedido bigserial NOT NULL,
    situacao smallint NOT NULL DEFAULT 0,
    data timestamp with time zone,
    razao_social character varying(255),
    cnpj character varying(14),
    telefone character varying(11),
    email character varying(255),
    PRIMARY KEY (id_pedido)
);

COMMENT ON COLUMN pedido.situacao
    IS '0 - Aberto
        1 - Finalizado';

CREATE TABLE pedido_produto
(
    id_pedido_produto bigserial NOT NULL,
    id_pedido bigint,
    produto character varying(255),
    quantidade numeric(14, 10),
    preco numeric(14, 10),
    PRIMARY KEY (id_pedido_produto),
    CONSTRAINT fk_pedido_produto_pedido FOREIGN KEY (id_pedido)
        REFERENCES pedido (id_pedido) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);
