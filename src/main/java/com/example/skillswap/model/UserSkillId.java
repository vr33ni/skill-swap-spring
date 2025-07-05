package com.example.skillswap.model;

 
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class UserSkillId implements Serializable {

    private UUID user;
    private Long skill;

    public UserSkillId() {}

    public UserSkillId(UUID user, Long skill) {
        this.user = user;
        this.skill = skill;
    }

    public UUID getUser() { return user; }
    public void setUser(UUID user) { this.user = user; }

    public Long getSkill() { return skill; }
    public void setSkill(Long skill) { this.skill = skill; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserSkillId that)) return false;
        return Objects.equals(user, that.user) &&
               Objects.equals(skill, that.skill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, skill);
    }
}
