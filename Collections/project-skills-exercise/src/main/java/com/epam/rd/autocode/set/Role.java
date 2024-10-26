package com.epam.rd.autocode.set;

// import java.util.Set;

// public class Role {

// 	private Level level;

// 	private Position position;

// 	private Set<Skill> skills;

// 	public Role(Position position, Level level, Skill... skills) {
// 	}

// 	public Set<Skill> getSkills() {
// 		return null;
// 	}
	
// }
import java.util.EnumSet;
import java.util.Set;

class Role {
    private final Position position;
    private final Level level;
    private final Set<Skill> skills;

    public Role(Position position, Level level, Skill... skills) {
        this.position = position;
        this.level = level;
        this.skills = EnumSet.noneOf(Skill.class);
        for (Skill skill : skills) {
            this.skills.add(skill);
        }
    }

    public Position getPosition() {
        return position;
    }

    public Level getLevel() {
        return level;
    }

    public Set<Skill> getSkills() {
        return skills;
    }
}

