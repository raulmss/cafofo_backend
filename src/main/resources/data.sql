INSERT INTO public.address (city, country, number, state, street, zip)
VALUES ('New York', 'United States', '123', 'NY', 'Broadway St', '10001');
INSERT INTO public.address (city, country, number, state, street, zip)
VALUES ('Los Angeles', 'United States', '456', 'CA', 'Hollywood Blvd', '90001');
INSERT INTO public.address (city, country, number, state, street, zip)
VALUES ('Chicago', 'United States', '789', 'IL', 'Michigan Ave', '60601');
INSERT INTO public.address (city, country, number, state, street, zip)
VALUES ('Dallas', 'United States', '101', 'TX', 'Main St', '75201');
INSERT INTO public.address (city, country, number, state, street, zip)
VALUES ('New York', 'United States', '123', 'NY', 'Broadway St', '10001');
INSERT INTO public.address (city, country, number, state, street, zip)
VALUES ('Los Angeles', 'United States', '456', 'CA', 'Hollywood Blvd', '90001');
INSERT INTO public.address (city, country, number, state, street, zip)
VALUES ('Chicago', 'United States', '789', 'IL', 'Michigan Ave', '60601');
INSERT INTO public.address (city, country, number, state, street, zip)
VALUES ('Dallas', 'United States', '101', 'TX', 'Main St', '75201');

INSERT INTO public._user (id,email, firstname, lastname, password, role, secret_answer, active)
VALUES (2,'admin@gmail.com', 'John', 'Doe', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2', 'ADMIN','$2a$10$e6OcEBww6qA0bNwNTKNugOmZ6YaovqHh0azORhy0E8IIPFRqUdA2i',true);
INSERT INTO public._user (id,email, firstname, lastname, password, role,secret_answer, active)
VALUES (3,'owner1@gmail.com', 'Jane', 'Smith', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2', 'OWNER','$2a$10$e6OcEBww6qA0bNwNTKNugOmZ6YaovqHh0azORhy0E8IIPFRqUdA2i',true);
INSERT INTO public._user (id,email, firstname, lastname, password, role,secret_answer, active)
VALUES (4,'customer1@gmail.com', 'Bob', 'Jones', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2', 'CUSTOMER','$2a$10$e6OcEBww6qA0bNwNTKNugOmZ6YaovqHh0azORhy0E8IIPFRqUdA2i',true);
INSERT INTO public._user (id,email, firstname, lastname, password, role,secret_answer, active)
VALUES (5,'customer2@gmail.com', 'Alice', 'Wang', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2', 'CUSTOMER','$2a$10$e6OcEBww6qA0bNwNTKNugOmZ6YaovqHh0azORhy0E8IIPFRqUdA2i',true);
INSERT INTO public._user (id,email, firstname, lastname, password, role, secret_answer, active)
VALUES (6,'owner2@gmail.com', 'Alice1', 'Wang1', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2', 'OWNER','$2a$10$e6OcEBww6qA0bNwNTKNugOmZ6YaovqHh0azORhy0E8IIPFRqUdA2i',true);

SELECT setval('_user_id_seq', 6);

insert into public.owner (id)values (3);
insert into public.owner (id)values (6);

insert into public.customer (id)values (4);
insert into public.customer (id)values (5);


insert into property (area, number_of_bathroom, number_of_bed, price, address_id, owner_id, deal_type, home_type,property_name,approvalstatus) values (700.0,2,2,800.0,1,3,'FOR_RENT','APARTMENT','JoJoResident',true);
insert into property (area, number_of_bathroom, number_of_bed, price, address_id, owner_id, deal_type, home_type,property_name,approvalstatus) values (800.0,2,3,800000.0,2,3,'FOR_SALE','CONDO','Royal Oaks',true);
insert into property (area, number_of_bathroom, number_of_bed, price, address_id, owner_id, deal_type, home_type,property_name,approvalstatus) values (900.0,2,2,800.0,3,6,'SOLD','TOWNHOUSE','Emerald Paradise',true);
insert into property (area, number_of_bathroom, number_of_bed, price, address_id, owner_id, deal_type, home_type,property_name,approvalstatus) values (100.0,2,3,800000.0,4,6,'FOR_SALE','LAND','Tranquil Haven',true);
insert into property (area, number_of_bathroom, number_of_bed, price, address_id, owner_id, deal_type, home_type,property_name,approvalstatus) values (700.0,2,2,800.0,5,6,'FOR_RENT','APARTMENT','JoJoResident',true);
insert into property (area, number_of_bathroom, number_of_bed, price, address_id, owner_id, deal_type, home_type,property_name,approvalstatus) values (800.0,2,3,800000.0,6,3,'FOR_SALE','CONDO','Royal Oaks',true);
insert into property (area, number_of_bathroom, number_of_bed, price, address_id, owner_id, deal_type, home_type,property_name,approvalstatus) values (900.0,2,2,800.0,7,6,'SOLD','TOWNHOUSE','Emerald Paradise',true);
insert into property (area, number_of_bathroom, number_of_bed, price, address_id, owner_id, deal_type, home_type,property_name,approvalstatus) values (100.0,2,3,800000.0,8,6,'FOR_SALE','LAND','Tranquil Haven',true);


insert into prop_image (path)
values ('house.jpg');
insert into prop_image (path)
values ('house1.jpg');
insert into prop_image (path)
values ('house2.jpg');
insert into prop_image (path)
values ('house3.jpg');
insert into prop_image (path)
values ('house4.jpg');
insert into prop_image (path)
values ('house5.jpg');
insert into prop_image (path)
values ('house6.jpg');
insert into prop_image (path)
values ('house7.jpg');
insert into prop_image (path)
values ('house8.jpg');

insert into property_image (image_id, property_id)
values (1,1);
insert into property_image (image_id, property_id)
values (2,1);
insert into property_image (image_id, property_id)
values (3,3);
insert into property_image (image_id, property_id)
values (4,4);
insert into property_image (image_id, property_id)
values (5,5);
insert into property_image (image_id, property_id)
values (6,6);
insert into property_image (image_id, property_id)
values (7,7);
insert into property_image (image_id, property_id)
values (8,8);

insert into favorite_properties (customer_id, property_id)
values (4,1);
insert into favorite_properties (customer_id, property_id)
values (4,2);
insert into favorite_properties (customer_id, property_id)
values (5,3);
insert into favorite_properties (customer_id, property_id)
values (5,4);

insert into offer (cancel_flag, offer_price, customer_id, offer_date, property_id, offer_status)
values (false,1000.0,4,now(),1,'PENDING');
insert into offer (cancel_flag, offer_price, customer_id, offer_date, property_id, offer_status)
values (false,1020.0,4,now(),1,'PENDING');
insert into offer (cancel_flag, offer_price, customer_id, offer_date, property_id, offer_status)
values (false,10280.0,5,now(),2,'ACCEPTED');
insert into offer (cancel_flag, offer_price, customer_id, offer_date, property_id, offer_status)
values (false,10200.0,5,now(),2,'PENDING');
insert into offer (cancel_flag, offer_price, customer_id, offer_date, property_id, offer_status)
values (false,1025.0,5,now(),3,'PENDING');