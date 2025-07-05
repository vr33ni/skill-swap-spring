package com.example.skillswap.service;

import com.example.skillswap.model.Match;
import com.example.skillswap.model.Skill;
import com.example.skillswap.model.MatchSkill;
import com.example.skillswap.model.MatchSkillId;
import com.example.skillswap.repository.MatchRepository;
import com.example.skillswap.repository.SkillRepository;
import com.example.skillswap.repository.MatchSkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MatchSkillServiceImpl implements MatchSkillService {

    private final MatchSkillRepository matchSkillRepository;
    private final MatchRepository matchRepository;
    private final SkillRepository skillRepository;

    public MatchSkillServiceImpl(MatchSkillRepository matchSkillRepository,
                                MatchRepository matchRepository,
                                SkillRepository skillRepository) {
        this.matchSkillRepository = matchSkillRepository;
        this.matchRepository = matchRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    public List<MatchSkill> getSkillsForMatch(UUID matchId) {
        return matchSkillRepository.findByMatchId(matchId);
    }

    @Override
    public MatchSkill addSkillToMatch(UUID matchId, Long skillId) {
        Match match = matchRepository.findById(matchId).orElseThrow();
        Skill skill = skillRepository.findById(skillId).orElseThrow();
        MatchSkill matchSkill = new MatchSkill();
        MatchSkillId id = new MatchSkillId();
        id.setMatchId(matchId);
        id.setSkillId(skillId);
        matchSkill.setId(id);
        matchSkill.setMatch(match);
        matchSkill.setSkill(skill);
        return matchSkillRepository.save(matchSkill);
    }

    @Override
    public void removeSkillFromMatch(UUID matchId, Long skillId) {
        MatchSkillId id = new MatchSkillId();
        id.setMatchId(matchId);
        id.setSkillId(skillId);
        matchSkillRepository.deleteById(id);
    }
}