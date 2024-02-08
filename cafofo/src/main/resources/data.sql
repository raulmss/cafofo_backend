
INSERT INTO public._user (id,email, firstname, lastname, password, role,active,secret_answer)
VALUES (2,'john.doe@example.com', 'John', 'Doe', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2', 'ADMIN',true, 'abc');
INSERT INTO public._user (id,email, firstname, lastname, password, role,active,secret_answer)
VALUES (3,'jane.smith@example.com', 'Jane', 'Smith', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2', 'OWNER',true,'abc');
INSERT INTO public._user (id,email, firstname, lastname, password, role,active,secret_answer)
VALUES (4,'bob.jones@example.com', 'Bob', 'Jones', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2', 'CUSTOMER',true,'abc');
INSERT INTO public._user (id,email, firstname, lastname, password, role,active,secret_answer)
VALUES (5,'alice.wang@example.com', 'Alice', 'Wang', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2', 'CUSTOMER',true,'abc');
INSERT INTO public._user (id,email, firstname, lastname, password, role,active,secret_answer)
VALUES (6,'alice1.wang@example.com', 'Alice1', 'Wang1', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2', 'OWNER',true,'abc');
insert into public.owner (id)
values (3);
insert into public.owner (id)
values (6);
insert into customer (id)
values (4);
insert into customer (id)
values (5);

INSERT INTO public.address (city, country, number, state, street, zip)
VALUES ('New York', 'United States', '123', 'NY', 'Broadway St', '10001');
INSERT INTO public.address (city, country, number, state, street, zip)
VALUES ('Los Angeles', 'United States', '456', 'CA', 'Hollywood Blvd', '90001');
INSERT INTO public.address (city, country, number, state, street, zip)
VALUES ('Chicago', 'United States', '789', 'IL', 'Michigan Ave', '60601');
INSERT INTO public.address (city, country, number, state, street, zip)
VALUES ('Dallas', 'United States', '101', 'TX', 'Main St', '75201');

insert into property (area, number_of_bathroom, number_of_bed, price, address_id, owner_id, deal_type, home_type,property_name,approvalStatus)
values (700.0,2,2,800.0,1,3,'FOR_RENT','APARTMENT','JoJoResident',true);
insert into property (area, number_of_bathroom, number_of_bed, price, address_id, owner_id, deal_type, home_type,property_name,approvalStatus)
values (800.0,2,3,800000.0,2,3,'FOR_SALE','CONDO','Royal Oaks',true);
insert into property (area, number_of_bathroom, number_of_bed, price, address_id, owner_id, deal_type, home_type,property_name,approvalStatus)
values (900.0,2,2,800.0,3,3,'SOLD','TOWNHOUSE','Emerald Paradise',true);
insert into property (area, number_of_bathroom, number_of_bed, price, address_id, owner_id, deal_type, home_type,property_name,approvalStatus)
values (1000.0,2,3,800000.0,4,3,'FOR_SALE','LAND','Tranquil Haven',true);
insert into offer (customer_id, id, offer_date, property_id, offer_status, cancel_flag,offer_price)
values (4,1,'2024-02-06 02:01:44.320947',1,'PENDING',true,1000.00);
insert into offer (customer_id, id, offer_date, property_id, offer_status,cancel_flag,offer_price)
values (4,2,'2024-02-06 02:01:44.320947',2,'PENDING',true,1200.00);
