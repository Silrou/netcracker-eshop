create table authorizedUser(
                               id  int NOT NULL,
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

create table orderCart(
                          id int NOT NULL PRIMARY KEY,
                          packageId integer NOT NULL,
                          courierId integer NOT NULL,
                          packageDescription varchar(100) NOT NULL,
                          orderStatus varchar(100) NOT NULL,
                          totalPrice NUMBER(7,2) NOT NULL,
                          userName varchar(100) NOT NULL,
                          deliveryTime DATE NOT NULL,
                          fullName VARCHAR(100) NOT NULL,
                          fullAddress VARCHAR(100) NOT NULL,
                          dontDisturb VARCHAR(1)
);

create table orderProduckt(
                              id int NOT NULL PRIMARY KEY,
                              productId int NOT NULL,
                              orderId int NOT NULL,
                              inCartProductAmount int NOT NULL
);

create table product (
                         id int PRIMARY KEY,
                         productCategory int NOT NULL ,
                         productName varchar(100) NOT NULL,
                         productAmount int NOT NULL ,
                         productPrice NUMBER(7,2) NOT NULL,
                         productDiscount NUMBER(7,2) NOT NULL,
                         productDate DATE NOT NULL,
                         productDescription VARCHAR(100) NOT NULL,
                         productStatus VARCHAR(100) NOT NULL
);

create table productCategory(
                                id integer  PRIMARY KEY,
                                productCategoryName VARCHAR(100) NOT NULL ,
                                productSuperCategoryId integer REFERENCES productCategory(id)
);

CREATE table statistic(
                          id integer PRIMARY KEY,
                          productCategory integer REFERENCES productCategory(id),
                          productid integer REFERENCES product(id),
                          productBought integer,
                          productSold integer,
                          productRating NUMBER(7,2)
);


INSERT INTO "AUTHORIZEDUSER" (ID, USERLOGIN, USERPASSWORD, USERROLE, USERNAME, USERREGISTRATIONDATE, USERSTATUS, USERADDRESS, USERNUMBER) VALUES ('0', 'ADMIN', 'ADMIN', 'ADMIN', 'ADMIN', TO_DATE('1981-05-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'ON', 'ADMINSTREET', 'ADMINNUMBER');
INSERT INTO PRODUCTCATEGORY (ID, PRODUCTCATEGORYNAME) VALUES ('1', 'BOOK');
INSERT INTO PRODUCTCATEGORY (ID, PRODUCTCATEGORYNAME, PRODUCTSUPERCATEGORYID) VALUES ('2', 'BIGBOOK', '1');
