package com.example.skillswap.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class MatchSkillId implements Serializable {
    private UUID matchId;
    private Long skillId;

    // Getters, setters, equals, and hashCode
    public UUID getMatchId() { return matchId; }
    public void setMatchId(UUID matchId) { this.matchId = matchId; }

    public Long getSkillId() { return skillId; }
    public void setSkillId(Long skillId) { this.skillId = skillId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MatchSkillId)) return false;
        MatchSkillId that = (MatchSkillId) o;
        return Objects.equals(matchId, that.matchId) && Objects.equals(skillId, that.skillId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchId, skillId);
    }
}