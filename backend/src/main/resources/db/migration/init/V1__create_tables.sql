create table authorizedUser(
                               id int NOT NULL,
                               userLogin varchar(100) NOT NULL,
                               userPassword varchar(100) NOT NULL,
                               userRole varchar(100) NOT NULL,
                               userName varchar(100) NOT NULL,
                               userRegistrationDate DATE NOT NULL,
                               userStatus VARCHAR(100) NOT NULL,
                               userAddress VARCHAR(100) NOT NULL,
                               userNumber VARCHAR(100) NOT NULL,
                               CONSTRAINT id_pk PRIMARY KEY(id)
);

create table genre(
                id int PRIMARY KEY,
                genreName varchar(100) NOT NULL
);

create table coverType(
                id int PRIMARY KEY,
                coverTypeName varchar(100) NOT NULL
);

create table author(
                          id int PRIMARY KEY,
                          authorName varchar(100) NOT NULL
);

create table language(
                          id int PRIMARY KEY,
                          languageName varchar(100) NOT NULL
);

create table publisher(
                         id int PRIMARY KEY,
                         publisherName varchar(100) NOT NULL
);

create table product (
                         id bigserial PRIMARY KEY,
                         productName varchar(255) NOT NULL,
                         productAmount bigserial NOT NULL ,
                         productPrice int NOT NULL,
                         productDiscount int NOT NULL,
                         productDate DATE NOT NULL,
                         productPict VARCHAR(100),
                         productDescription VARCHAR(100) NOT NULL,
                         productStatus VARCHAR(100) NOT NULL,
                         genre bigserial NOT NULL,
                         coverType bigserial NOT NULL,
                         author bigserial NOT NULL,
                         language bigserial NOT NULL,
                         publisher bigserial NOT NULL
);

create table orderProduct(
                              id int NOT NULL PRIMARY KEY,
                              productId int NOT NULL,
                              orderId int NOT NULL,
                              inCartProductAmount int NOT NULL
);

create table orderCart(
                          id int NOT NULL PRIMARY KEY,
                          packageId integer NOT NULL,
                          courierId integer NOT NULL,
                          packageDescription varchar(100) NOT NULL,
                          orderStatus varchar(100) NOT NULL,
                          totalPrice float NOT NULL,
                          userName varchar(100) NOT NULL,
                          deliveryTime DATE NOT NULL,
                          fullName VARCHAR(100) NOT NULL,
                          fullAddress VARCHAR(100) NOT NULL,
                          dontDisturb VARCHAR(1)
);


CREATE table statistic(
                          id integer PRIMARY KEY,
                          productCategory integer NOT NULL,
                          productId integer NOT NULL,
                          productBought integer NOT NULL,
                          productSold integer NOT NULL,
                          productRating float
);

