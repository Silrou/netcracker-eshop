create table authorizedUser(
                               id BIGSERIAL NOT NULL,
                               userLogin varchar(100) NOT NULL,
                               userPassword varchar(100) NOT NULL,
                               userRole varchar(100) NOT NULL,
                               userName varchar(100) NOT NULL,
                               userSurname varchar(100) NOT NULL,
                               userRegistrationDate DATE NOT NULL,
                               userStatus VARCHAR(100) NOT NULL,
                               userAddress VARCHAR(100) NOT NULL,
                               userNumber VARCHAR(100) NOT NULL,
                               CONSTRAINT id_pk PRIMARY KEY(id)
);

create table productCategory(
                                id BIGSERIAL  PRIMARY KEY,
                                productCategoryName VARCHAR(100) NOT NULL,
                                productSuperCategoryId BIGSERIAL
);

create table product (
                         id BIGSERIAL PRIMARY KEY,
                         productCategory BIGSERIAL NOT NULL ,
                         productName varchar(100) NOT NULL,
                         productAmount BIGSERIAL NOT NULL ,
                         productPrice BIGSERIAL NOT NULL,
                         productDiscount BIGSERIAL NOT NULL,
                         productDate DATE NOT NULL,
                         productPict VARCHAR(100),
                         productDescription VARCHAR(100) NOT NULL,
                         productStatus VARCHAR(100) NOT NULL
);

create table orderProduckt(
                              id BIGSERIAL NOT NULL PRIMARY KEY,
                              productId BIGSERIAL NOT NULL,
                              orderId BIGSERIAL NOT NULL,
                              inCartProductAmount BIGSERIAL NOT NULL
);

create table orderCart(
                          id BIGSERIAL NOT NULL PRIMARY KEY,
                          userID BIGSERIAL NOT NULL,
                          courierId BIGSERIAL NOT NULL,
                          packageDescription varchar(100) NOT NULL,
                          orderStatus varchar(100) NOT NULL,
                          totalPrice BIGSERIAL NOT NULL,
                          userName varchar(100) NOT NULL,
                          deliveryTime DATE NOT NULL,
                          fullAddress VARCHAR(100) NOT NULL,
                          dontDisturb VARCHAR(1)
);


CREATE table statistic(
                          id BIGSERIAL PRIMARY KEY,
                          productCategory BIGSERIAL NOT NULL,
                          productId BIGSERIAL NOT NULL,
                          productBought BIGSERIAL NOT NULL,
                          productSold BIGSERIAL NOT NULL,
                          productRating BIGSERIAL
);

CREATE table courierCalendar(
                          courierId BIGSERIAL ,
                          F9T10 BIGSERIAL ,
                          F10T11 BIGSERIAL ,
                          F11T12 BIGSERIAL ,
                          F12T13 BIGSERIAL ,
                          F13T14 BIGSERIAL ,
              F14T15 BIGSERIAL ,
              F15T16 BIGSERIAL ,
              F16T17 BIGSERIAL ,
              F17T18 BIGSERIAL ,
              F18T19 BIGSERIAL ,
              F19T20 BIGSERIAL ,
              F20T21 BIGSERIAL ,
              userRegistrationDate DATE NOT NULL
);
