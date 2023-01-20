# Game Store E-Commerce

## Customers

customer_id
first_name
middle_name
last_name
email_address
phone_number
address_main
city
country
zip_code

## Products

product_id
product_type
product_name
product_price
product_ref
product_description
product_details

## Products_Types

product_type_id
product_type_description

## Orders

order_id
customer_id
order_status
date_order
order_details

## Payment_Methods

payment_method_id
payment_method_code
payment_method_description

## Customers_Payment_Methods

customer_id
payment_method_code
credit_card_number
payment_method_details

## Orders

order_id
customer_id
order_status_code
order_request_date
order_details

## Order_Items

order_item_id
product_id
order_id
order_item_status_code

## Invoices

invoice_number
order_id
invoice_status_code
invoce_date
invoce_details

## Shipments

shipment_id
order_id
invoice_number
shipment_tracking_number
shipment_date
other_shipment_details

## shipment_items

shipment_id
order_item_id
 
