CREATE TABLE booking
(
    booking_id    SERIAL PRIMARY KEY,
    customer_id   INT REFERENCES customers (customer_id),
    room_id       INT REFERENCES rooms (room_id),
    checkin_date  date,
    checkout_date date
)