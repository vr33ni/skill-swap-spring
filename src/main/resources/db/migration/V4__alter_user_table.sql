-- 1️⃣ Add the auth provider column, defaulting to 'local'
ALTER TABLE users
ADD COLUMN provider VARCHAR(50) NOT NULL DEFAULT 'local';

-- 2️⃣ Add the external provider_id for OAuth
ALTER TABLE users
ADD COLUMN provider_id VARCHAR(255);

-- 3️⃣ Make sure password_hash can be null for OAuth users
ALTER TABLE users
ALTER COLUMN password_hash DROP NOT NULL;

-- 4️⃣ (Optional) Add a unique constraint to avoid duplicate provider_id+provider combos
ALTER TABLE users
ADD CONSTRAINT unique_provider UNIQUE (provider, provider_id);

-- 5️⃣ Make email unique if it isn't already
ALTER TABLE users
ADD CONSTRAINT unique_email UNIQUE (email);
