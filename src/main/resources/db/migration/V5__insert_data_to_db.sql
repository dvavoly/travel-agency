INSERT INTO hotels(hotel_name, hotel_address, country)
VALUES ('Comfort Hotel Cachan Paris Sud', '2 Rue Mirabeau, 94230 Cachan', 'FRANCE'),
       ('B&B HOTEL Paris 17 Batignolles', '4 Boulevard Berthier, 17th arr., 75017 Paris', 'FRANCE'),
       ('Château de la Bribourdière', 'La Bribourdière, 14430 Putot-en-Auge', 'FRANCE'),
       ('Hotel Panorama di Sicilia', 'Via Alcide De Gasperi 44, 98030 Castelmola', 'ITALY'),
       ('NYX Hotel Milan by Leonardo Hotels', 'Piazza Quattro Novembre 3, Stazione Centrale, 20124 Milan', 'ITALY'),
       ('Hotel Delle Nazioni', 'Via Luigi Alamanni 15, Santa Maria Novella, 50123 Florence', 'ITALY'),
       ('Expo Hotel Barcelona', 'Mallorca, 1-23, Sants-Montjuïc, 08014 Barcelona', 'SPAIN'),
       ('Riu Plaza España', 'Calle Gran Vía, 84, Centro, 28013 Madrid', 'SPAIN'),
       ('Royal Hideaway Corales Suites', 'venida Virgen de Guadalupe 21 Playa La Enramada, La Caleta 38679, Tenerife', 'SPAIN'),
       ('Hotel El Rullo', 'La Font, 2, 12192 Vilafames', 'SPAIN');
INSERT INTO rooms(hotel_id, room_number, room_type, room_cost)
VALUES (1, '1', 'ECONOMY', 100),
       (1, '2', 'STANDARD', 250),
       (1, '3', 'BUSINESS', 500),
       (2, '1', 'ECONOMY', 110),
       (2, '2', 'STANDARD', 280),
       (3, '1', 'ECONOMY', 90),
       (3, '2', 'STANDARD', 200);
INSERT INTO customers(first_name, last_name, email, customer_password, customer_role)
VALUES ('Michael', 'Wazowski', 'mike@monsters.inc', '$2a$10$N7pa23WgTdLfhK4tlZgYq.C0Hec/5qNs/BEl0JCFQ4.KmRUZocKe2',
        'ROLE_GUEST'),
       ('James', 'Sullivan', 'sulley@monsters.inc', '$2a$10$N7pa23WgTdLfhK4tlZgYq.C0Hec/5qNs/BEl0JCFQ4.KmRUZocKe2',
        'ROLE_GUEST'),
       ('Roz', 'Slug', 'roz@monsters.inc', '$2a$10$N7pa23WgTdLfhK4tlZgYq.C0Hec/5qNs/BEl0JCFQ4.KmRUZocKe2',
        'ROLE_MANAGER');
INSERT INTO booking(customer_id, room_id, checkin_date, checkout_date)
VALUES (1, 1, '2022-09-28', '2022-10-01'),
       (2, 4, '2022-05-28', '2022-06-01'),
       (3, 7, '2022-04-28', '2022-05-10');