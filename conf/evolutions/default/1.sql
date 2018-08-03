# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table developer (
  id                            uuid not null,
  username                      varchar(255) not null,
  email                         varchar(255),
  constraint pk_developer primary key (id)
);


# --- !Downs

drop table if exists developer cascade;

