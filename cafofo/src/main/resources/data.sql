INSERT INTO public.address (city, country, number, state, street, zip)
VALUES ('New York', 'United States', '123', 'NY', 'Broadway St', '10001');
INSERT INTO public.address (city, country, number, state, street, zip)
VALUES ('Los Angeles', 'United States', '456', 'CA', 'Hollywood Blvd', '90001');
INSERT INTO public.address (city, country, number, state, street, zip)
VALUES ('Chicago', 'United States', '789', 'IL', 'Michigan Ave', '60601');
INSERT INTO public.address (city, country, number, state, street, zip)
VALUES ('Dallas', 'United States', '101', 'TX', 'Main St', '75201');

INSERT INTO public._user (id,email, firstname, lastname, password, role,active)
VALUES (1,'john.doe@example.com', 'John', 'Doe', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2', 'ADMIN',true);
INSERT INTO public._user (id,email, firstname, lastname, password, role,active)
VALUES (2,'jane.smith@example.com', 'Jane', 'Smith', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2', 'OWNER',true);
INSERT INTO public._user (id,email, firstname, lastname, password, role,active)
VALUES (3,'bob.jones@example.com', 'Bob', 'Jones', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2', 'CUSTOMER',true);
INSERT INTO public._user (id,email, firstname, lastname, password, role,active)
VALUES (4,'alice.wang@example.com', 'Alice', 'Wang', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2', 'CUSTOMER',true);
INSERT INTO public._user (id,email, firstname, lastname, password, role, active)
VALUES (5,'alice1.wang@example.com', 'Alice1', 'Wang1', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2', 'OWNER',true);

insert into public.owner (user_id)
values (2);
insert into public.owner (user_id)
values (5);

insert into customer (user_id)
values (3);
insert into customer (user_id)
values (4);

insert into property (area, number_of_bathroom, number_of_bed, price, address_id, owner_id, deal_type, home_type,property_name,approvalstatus) values (700.0,2,2,800.0,1,2,'FOR_RENT','APARTMENT','JoJoResident',false);
insert into property (area, number_of_bathroom, number_of_bed, price, address_id, owner_id, deal_type, home_type,property_name,approvalstatus) values (800.0,2,3,800000.0,2,2,'FOR_SALE','CONDO','Royal Oaks',false);
insert into property (area, number_of_bathroom, number_of_bed, price, address_id, owner_id, deal_type, home_type,property_name,approvalstatus) values (900.0,2,2,800.0,3,1,'SOLD','TOWNHOUSE','Emerald Paradise',false);
insert into property (area, number_of_bathroom, number_of_bed, price, address_id, owner_id, deal_type, home_type,property_name,approvalstatus) values (1000.0,2,3,800000.0,4,1,'FOR_SALE','LAND','Tranquil Haven',false);