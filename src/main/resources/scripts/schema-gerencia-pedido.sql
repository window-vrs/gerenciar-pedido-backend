drop table if exists TB_ROLE;

/*==============================================================*/
/* Table: TB_ROLE                                               */
/*==============================================================*/
create table TB_ROLE
(
   ID                   INT not null auto_increment,
   STR_NOME             VARCHAR(10),
   STR_DESCRICAO        VARCHAR(25),
   B_STATUS             boolean,
   primary key (ID)
);


drop table if exists TB_USER;

/*==============================================================*/
/* Table: TB_USER                                               */
/*==============================================================*/
create table TB_USER
(
   ID                   INT not null auto_increment,
   USERNAME             VARCHAR(20),
   STR_OBSERVACAO       VARCHAR(200),
   PASSWORD             VARCHAR(255),
   STATUS               Boolean,
   DT_CADASTRO          timestamp default CURRENT_TIMESTAMP,
   DT_ULTIMA_ATUALIZACAO timestamp default CURRENT_TIMESTAMP,
   primary key (ID)
);

drop table if exists RL_ROLE_USUARIO;

/*==============================================================*/
/* Table: RL_ROLE_USUARIO                                       */
/*==============================================================*/
create table RL_ROLE_USUARIO
(
   ID_USUARIO           INT,
   ID_ROLE              INT,
     PRIMARY KEY (ID_USUARIO, ID_ROLE),
    FOREIGN KEY (ID_USUARIO) REFERENCES TB_USER(ID),
    FOREIGN KEY (ID_ROLE) REFERENCES TB_ROLE(ID)
);


/*==============================================================*/
/* Table: TB_CATEGORIA_PRODUTO                                  */
/*==============================================================*/
create table TB_CATEGORIA_PRODUTO
(
   ID                   INT not null auto_increment,
   STR_NOME             VARCHAR(40) not null,
   STR_DESCRICAO        VARCHAR(255),
   B_STATUS             boolean,
   primary key (ID)
);


drop table if exists TB_PRODUTO;

/*==============================================================*/
/* Table: TB_PRODUTO                                            */
/*==============================================================*/
create table TB_PRODUTO
(
   ID                   INT not null auto_increment,
   ID_CATEGORIA         INT not null,
   STR_NOME             VARCHAR(150),
   STR_DESCRICAO        TEXT,
   D_PRECO              DECIMAL(10,2) not null,
   IT_ESTOQUE           INT not null,
   STR_CODIGO_BARRAS    VARCHAR(50) not null,
   B_STATUS             BOOLEAN not null,
   DT_CADASTRO          timestamp not null,
   DT_ATUALIZADO        timestamp not null,
   primary key (ID)
);


alter table RL_ROLE_USUARIO add constraint FK_REF_ROLE_USUARIO foreign key (ID_ROLE)
      references TB_ROLE (ID) on delete cascade on update cascade;

alter table RL_ROLE_USUARIO add constraint FK_RF_USER_ROLE foreign key (ID_USUARIO)
      references TB_USER (ID) on delete cascade on update cascade;

alter table TB_PRODUTO add constraint FK_REF_CATEGORIA_PRODUTO foreign key (ID_CATEGORIA)
      references TB_CATEGORIA_PRODUTO (ID) on delete cascade on update cascade;

