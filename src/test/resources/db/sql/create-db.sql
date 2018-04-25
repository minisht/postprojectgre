-- Table: public."ProductPrice"

-- DROP TABLE public."ProductPrice";

CREATE TABLE "ProductPrice"
(
    "ID" integer,
    "ProductID" integer NOT NULL,
    "Current_Price" double precision NOT NULL,
    "Currency_Code" varchar(10) NOT NULL,
    CONSTRAINT "ProductPrice_pkey" PRIMARY KEY ("ID")
);
CREATE SEQUENCE ProductPrice_ID_seq START WITH 1 INCREMENT BY 1;
