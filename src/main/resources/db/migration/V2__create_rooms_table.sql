CREATE TABLE rooms
(
    room_id     SERIAL PRIMARY KEY,
    hotel_id    INT REFERENCES hotels (hotel_id),
    room_number VARCHAR(50),
    room_type   VARCHAR(50),
    room_cost   INT DEFAULT 0
);