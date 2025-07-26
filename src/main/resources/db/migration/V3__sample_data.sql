-- Insert sample users
INSERT INTO users (id, name, email, password_hash, photo_url, location, radius_km)
VALUES
  ('11111111-1111-1111-1111-111111111111', 'Alice', 'alice@example.com', 'password1', NULL, ST_SetSRID(ST_MakePoint(13.4050, 52.5200), 4326), 10),
  ('22222222-2222-2222-2222-222222222222', 'Bob', 'bob@example.com', 'password2', NULL, ST_SetSRID(ST_MakePoint(9.9937, 53.5511), 4326), 10),
  ('33333333-3333-3333-3333-333333333333', 'Charlie', 'charlie@example.com', 'password3', NULL, ST_SetSRID(ST_MakePoint(11.5810, 48.1351), 4326), 10);

-- Insert sample skills (neighborhood/tool/handyman oriented)
INSERT INTO skills (id, name) VALUES
  (1, 'Power Drill'),
  (2, 'Lawn Mower'),
  (3, 'Bicycle Repair'),
  (4, 'Gardening Tools'),
  (5, 'Painting Supplies'),
  (6, 'Sewing Machine'),
  (7, 'Car Jumper Cables'),
  (8, 'Ladder'),
  (9, 'Pressure Washer');

-- Insert user_skills (who offers/needs what)
INSERT INTO user_skills (user_id, skill_id, type) VALUES
  ('11111111-1111-1111-1111-111111111111', 1, 'OFFER'), -- Alice offers Power Drill
  ('11111111-1111-1111-1111-111111111111', 4, 'OFFER'), -- Alice offers Gardening Tools
  ('11111111-1111-1111-1111-111111111111', 8, 'NEED'),  -- Alice needs Ladder

  ('22222222-2222-2222-2222-222222222222', 2, 'OFFER'), -- Bob offers Lawn Mower
  ('22222222-2222-2222-2222-222222222222', 8, 'OFFER'), -- Bob offers Ladder
  ('22222222-2222-2222-2222-222222222222', 1, 'NEED'),  -- Bob needs Power Drill

  ('33333333-3333-3333-3333-333333333333', 3, 'OFFER'), -- Charlie offers Bicycle Repair
  ('33333333-3333-3333-3333-333333333333', 5, 'OFFER'), -- Charlie offers Painting Supplies
  ('33333333-3333-3333-3333-333333333333', 2, 'NEED');  -- Charlie needs Lawn Mower

-- Insert a match between Alice and Bob for Power Drill and Ladder
INSERT INTO matches (id, user1_id, user2_id, status, created_at)
VALUES ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '11111111-1111-1111-1111-111111111111', '22222222-2222-2222-2222-222222222222', 'ACCEPTED', NOW());

-- Link skills to the match
INSERT INTO match_skills (match_id, skill_id) VALUES
  ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 1), -- Power Drill
  ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 8); -- Ladder

-- Insert a message in the match
INSERT INTO messages (id, match_id, sender_id, content, sent_at)
VALUES ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '11111111-1111-1111-1111-111111111111', 'Hi Bob, could I borrow your ladder this weekend?', NOW());