create table if not exists Ingredient (
  id varchar(4) not null,
  name varchar(25) not null,
  type varchar(10) not null
);

create table if not exists Taco (
  id identity,
  name varchar(50) not null,
  createdAt timestamp not null
);

create table if not exists Taco_Ingredients (
  taco bigint not null,
  ingredient varchar(4) not null
);

alter table Taco_Ingredients add foreign key (taco) references Taco(id);
alter table Taco_Ingredients add foreign key (ingredient) references Ingredient(id);

create table if not exists Taco_Order (
  id identity,
    deliveryName varchar(50) not null,
    street varchar(50) not null,
    city varchar(50) not null,
    state varchar(2) not null,
    zip varchar(10) not null,
    ccNumber varchar(16) not null,
    ccExpiration varchar(5) not null,
    ccCVV varchar(3) not null,
    placedAt timestamp not null,
    userId number not null
);

create table if not exists Taco_Order_Tacos (
  tacoOrder bigint not null,
  taco bigint not null
);

alter table Taco_Order_Tacos add foreign key (tacoOrder) references Taco_Order(id);
alter table Taco_Order_Tacos add foreign key (taco) references Taco(id);

CREATE sequence  hibernate_sequence;

create table user(
      username varchar_ignorecase(50) not null,
      password varchar_ignorecase(50) not null,
      enabled boolean not null default true,
      city varchar_ignorecase(50) not null,
      fullname varchar_ignorecase(50) not null,
      phone_number varchar_ignorecase(50) not null,
      state varchar_ignorecase(50) not null,
      street varchar_ignorecase(50) not null,
      zip varchar_ignorecase(50) not null,
      id identity );

 create table authorities (
      username varchar_ignorecase(50) not null,
      authority varchar_ignorecase(50) not null,
      constraint fk_authorities_users foreign key(username) references user(username));
      create unique index ix_auth_username on authorities (username,authority);
