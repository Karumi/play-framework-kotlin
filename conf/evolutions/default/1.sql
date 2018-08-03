# --- !Ups

create table developer (
  id                            uuid not null,
  username                      varchar(255) not null,
  email                         varchar(255),
  constraint pk_developer primary key (id)
);
