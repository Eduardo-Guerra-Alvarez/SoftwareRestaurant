-- Users (employees)
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100),
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20),
    role VARCHAR(50) NOT NULL, -- admin, waiter, cook, etc.
    password_hash TEXT NOT NULL,
    is_active BOOLEAN DEFAULT TRUE
);

-- Tables (restaurant tables)
CREATE TABLE tables (
    table_id SERIAL PRIMARY KEY,
    table_number INT NOT NULL UNIQUE,
    capacity INT NOT NULL,
    status VARCHAR(20) DEFAULT 'available' -- available, occupied, reserved
);

-- Menu items
CREATE TABLE menu (
    menu_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price NUMERIC(10, 2) NOT NULL,
    category VARCHAR(50) NOT NULL, -- food, drink, dessert, etc.
    is_active BOOLEAN DEFAULT TRUE
);

-- Clients (optional, for frequent clients or delivery)
CREATE TABLE clients (
    client_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    email VARCHAR(100)
);

-- Orders
CREATE TABLE orders (
    order_id SERIAL PRIMARY KEY,
    table_id INT REFERENCES tables(table_id),
    user_id INT REFERENCES users(user_id),
    client_id INT REFERENCES clients(client_id),
    status VARCHAR(30) DEFAULT 'pending', -- pending, in_progress, delivered, canceled
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total NUMERIC(10, 2) DEFAULT 0.0
);

-- Order details (what was ordered)
CREATE TABLE order_details (
    detail_id SERIAL PRIMARY KEY,
    order_id INT REFERENCES orders(order_id) ON DELETE CASCADE,
    menu_id INT REFERENCES menu(menu_id),
    quantity INT NOT NULL,
    unit_price NUMERIC(10,2) NOT NULL,
    subtotal NUMERIC(10,2) GENERATED ALWAYS AS (quantity * unit_price) STORED
);
