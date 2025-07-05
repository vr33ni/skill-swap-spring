package com.example.skillswap.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "match_skills")
public class MatchSkill implements Serializable {

    @EmbeddedId
    private MatchSkillId id = new MatchSkillId();

    @ManyToOne
    @MapsId("matchId")
    @JoinColumn(name = "match_id")
    private Match match;

    @ManyToOne
    @MapsId("skillId")
    @JoinColumn(name = "skill_id")
    private Skill skill;

    // Getters and setters
    public MatchSkillId getId() { return id; }
    public void setId(MatchSkillId id) { this.id = id; }

    public Match getMatch() { return match; }
    public void setMatch(Match match) { this.match = match; }

    public Skill getSkill() { return skill; }
    public void setSkill(Skill skill) { this.skill = skill; }
}