-- Create table
CREATE TABLE IF NOT EXISTS sour (
    id SERIAL PRIMARY KEY,
    type VARCHAR(255) NOT NULL,
    rating REAL NOT NULL CHECK (rating >= 1 AND rating <= 5),
    store VARCHAR(255) NOT NULL,
    comment TEXT,

    drank_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

-- Create trigger function (reusable for other tables)
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Attach trigger to sour table
CREATE TRIGGER update_sour_updated_at
    BEFORE UPDATE ON sour
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();