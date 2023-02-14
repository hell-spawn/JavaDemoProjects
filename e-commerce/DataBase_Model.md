# GAME STORE E-COMMERCE

## CUSTOMERS

|   |FIELD          | VARCHAR(510) | DESCRIPTION                  |
|---|---------------|--------------|------------------------------|
|PK |ID             | VARCHAR(35)  | UID Identifier customers     |
|PK |FIRST_NAME     | VARCHAR(35)  | Customer first name  not null|
|PK |LAST_NAME      | VARCHAR(35)  | Customer last name not null  |
|PK |EMAIL_ADDRESS  | VARCHAR(35)  | Customer email not null      |
|PK |PHONE_NUMBER   | VARCHAR(35)  | Customer phone_number        |
|PK |ADDRESS_MAIN   | VARCHAR(35)  | Customer Address not null    |
|PK |CITY           | VARCHAR(35)  | Customer city                |
|PK |COUNTRY        | VARCHAR(35)  | Customer country             |
|PK |ZIP_CODE       | VARCHAR(35)  | Customer zip code not null   |


## PRODUCTS

|   |FIELD          | VARCHAR(510) | DESCRIPTION                  |
|---|---------------|--------------|------------------------------|
|PK |ID             | VARCHAR(35)  | UID Identifier products      |
|FK |PRODUCT_TYPE   | INTEGER      | Foreing key PRODUCTS_TYPES   |
|   |NAME           | VARCHAR(255) | Product Name                 |
|   |PRICE          | DECIMAL      | Price Product                |
|   |REFERENCE      | VARCHAR(255) | Code reference               |
|   |DESCRIPTION    | VARCHAR(255) | General description product  |
|   |DETAILS        | VARCHAR(510) | Other details product        |

## PRODUCTS_TYPES

|   |FIELD          | VARCHAR(510) | DESCRIPTION                  |
|---|---------------|--------------|------------------------------|
|PK |ID             | INTEGER      | ID Identifier product type   |
|   |DESCRIPTION    | VARCHAR(35)  | Description product type     |


## PAYMENT_METHODS

PAYMENT_METHOD_ID
PAYMENT_METHOD_CODE
PAYMENT_METHOD_DESCRIPTION

## CUSTOMERS_PAYMENT_METHODS

CUSTOMER_ID
PAYMENT_METHOD_CODE
CREDIT_CARD_NUMBER
PAYMENT_METHOD_DETAILS

## ORDERS

ORDER_ID
CUSTOMER_ID
ORDER_STATUS_CODE
ORDER_REQUEST_DATE
ORDER_DETAILS

## ORDER_ITEMS

ORDER_ITEM_ID
PRODUCT_ID
ORDER_ID
ORDER_ITEM_STATUS_CODE
ORDER_ITEM_QUANTITY
ORDER_ITEM_PRICE
OTHER_ORDER_ITEM_DETAILS

## ORDER_STATUS_CODES

ORDER_STATUS_CODE_ID
ORDER_STATUS_CODE_DESCRIPTION

## ORDER_ITEM_STATUS_CODES

ORDER_ITEM_STATUS_CODE_ID
ORDER_ITEM_STATUS_CODE_DESCRIPTION

## INVOICES

INVOICE_NUMBER
ORDER_ID
INVOICE_STATUS_CODE
INVOICE_DATE
INVOICE_DETAILS

## SHIPMENTS

SHIPMENT_ID
ORDER_ID
INVOICE_NUMBER
SHIPMENT_TRACKING_NUMBER
SHIPMENT_DATE
OTHER_SHIPMENT_DETAILS

## SHIPMENT_ITEMS

SHIPMENT_ID
ORDER_ITEM_ID

## INVOICE_STATUS_CODE

INVOICE_STATUS_CODE_ID
INVOICE_STATUS_CODE_DESCRIPTION

# USERS

ID
USER_KEY VARCHAR(255)
LOWER_USER_NAME VARCHAR(255)
USER_NAME
DATE_REGISTERED
ROLE
EMAIL VARCHAR(254)
PASSWORD_HASH VARCHAR(45)
CREATION_DATE TIMESTAMP
STAUTS BOOLEAN
LAST_ACCESS_DATE TIMESTAMP
Updated_at TIMESTAMP

