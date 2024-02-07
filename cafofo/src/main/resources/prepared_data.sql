-- Insert data into _user table
INSERT INTO _user ( id, firstname, lastname, email, password, role) VALUES
                                                                   (1,'John', 'Doe', 'john.doe@example.com', 'hashed_password_1@gmail.com', 'CUSTOMER'),
                                                                   (2,'Jane', 'Smith', 'jane.smith@example.com', 'hashed_password_2', 'CUSTOMER'),
                                                                   (3, 'Bob', 'Johnson', 'bob.johnson@example.com', 'hashed_password_3', 'CUSTOMER');

INSERT INTO _user ( id, firstname, lastname, email, password, role) VALUES
                                                                    (4, 'Owner John', 'Doe', 'john.doe@example.com', 'hashed_password_1@gmail.com', 'CUSTOMER'),
                                                                    (5,'Owner Jane', 'Smith', 'jane.smith@example.com', 'hashed_password_2', 'CUSTOMER'),
                                                                    (6,'Owner Bob', 'Johnson', 'bob.johnson@example.com', 'hashed_password_3', 'CUSTOMER');
-- Insert data into customer table
INSERT INTO customer (user_id) VALUES
                                   (1), -- Replace with a valid user ID
                                   (2), -- Replace with a valid user ID
                                   (3); -- Replace with a valid user ID

-- Insert data into owner table
INSERT INTO owner (user_id) VALUES
                                (4),  -- Replace with a valid user ID
                                (5),  -- Replace with a valid user ID
                                (6);  -- Replace with a valid user ID

-- Insert data into address table
INSERT INTO address (country, state, city, street, number, zip) VALUES
                                                                    ('USA', 'California', 'Los Angeles', 'Main St', '123', '90001'),
                                                                    ('Canada', 'Ontario', 'Toronto', 'Oak St', '456', 'M5J 2R8'),
                                                                    ('France', 'Ile-de-France', 'Paris', 'Rue de la Paix', '789', '75001');

-- Insert data into property table
INSERT INTO property (property_name, owner_id, address_id, price, number_of_bed, number_of_bathroom, feature, home_type, deal_type, area)
VALUES
    ('Cozy House', 1, 1, 250000.00, 3, 2, 'Fireplace, Garden', 'HOUSE', 'FOR_SALE', 120.5),
    ('Modern Apartment', 2, 2, 1500.00, 1, 1, 'Balcony', 'APARTMENT', 'FOR_RENT', 75.8),
    ('Spacious Villa', 3, 3, 750000.00, 5, 4, 'Swimming Pool, Garage', 'CONDO', 'FOR_RENT', 300.0);
-- INSERT INTO property (property_name, owner_id, address_id, price, number_of_bed, number_of_bathroom, home_type, deal_type, area)
-- VALUES ('Luxury Villa', 1, 1, 1000000.0, 5, 4, 'HOUSE', 'FOR_SALE', 350.0),
-- ('Modern Apartment', 2, 2, 1500.00, 1, 1,  'APARTMENT', 'FOR_RENT', 75.8),
-- ('Spacious Villa', 3, 3, 750000.00, 5, 4,  'CONDO', 'FOR_RENT', 300.0);

-- Insert data into PropImage table
INSERT INTO prop_image (path)
VALUES ('http://example.com/image1.jpg'),
       ('http://example.com/image2.jpg'),
       ('http://example.com/image3.jpg');

-- -- Insert data into FactAndFeatures table
-- INSERT INTO fact_and_features (property_id, feature)
-- VALUES (1, 'Swimming Pool'),
--        (1, 'Smart Home System'),
--        (1, 'Garden');
-- Testing

-- Insert data into offer table
INSERT INTO offer (customer_id, offer_status, offer_price, offer_date, property_id,cancel_flag)
VALUES
    (1, 'PENDING', 230000.00, '2023-01-15 10:30:00', 1,false),
    (2, 'PENDING', 1550.00, '2023-01-20 12:45:00', 2,false),
    (3, 'PENDING', 720000.00, '2023-01-22 14:15:00', 3,false);


