ALTER TABLE matches DROP COLUMN skill_id;

CREATE TABLE match_skills (
  match_id UUID REFERENCES matches(id) ON DELETE CASCADE,
  skill_id INTEGER REFERENCES skills(id) ON DELETE CASCADE,
  PRIMARY KEY (match_id, skill_id)
);