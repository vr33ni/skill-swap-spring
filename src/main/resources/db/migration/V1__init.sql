-- V1__init.sql
-- Enable PostGIS (spatial extension)
CREATE EXTENSION IF NOT EXISTS postgis;

 
CREATE TABLE users (
  id UUID PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(255) UNIQUE NOT NULL,
  password_hash VARCHAR(255) NOT NULL,
  photo_url VARCHAR(255),
  location GEOGRAPHY(Point, 4326),
  radius_km DOUBLE PRECISION DEFAULT 5,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE skills (
  id SERIAL PRIMARY KEY,
  name VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE user_skills (
  user_id UUID REFERENCES users(id),
  skill_id INTEGER REFERENCES skills(id),
  type VARCHAR(10) CHECK (type IN ('OFFER', 'NEED')),
  PRIMARY KEY (user_id, skill_id)
);

CREATE TABLE matches (
  id UUID PRIMARY KEY,
  user1_id UUID REFERENCES users(id),
  user2_id UUID REFERENCES users(id),
  skill_id INTEGER REFERENCES skills(id),
  status VARCHAR(20) DEFAULT 'pending',
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE messages (
  id UUID PRIMARY KEY,
  match_id UUID REFERENCES matches(id),
  sender_id UUID REFERENCES users(id),
  content TEXT NOT NULL,
  sent_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_users_location ON users USING GIST (location);
