-- Create Enum Type
CREATE TYPE unit AS ENUM ('PCS', 'DOZEN', 'PACK', 'LITER', 'KILOGRAM');

-- Create Product Table
CREATE TABLE products (
    id VARCHAR(36) PRIMARY KEY,
    ean_code VARCHAR(25) NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    base_price DECIMAL(10, 2) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    stock INTEGER NOT NULL,
    unit unit NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Cast Enum Unit
CREATE CAST (character varying AS unit) WITH INOUT AS ASSIGNMENT;