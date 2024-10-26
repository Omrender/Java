package com.epam.rd.autocode.set;

// import java.util.Set;

// public class Member {
	
// 	private String name;
	
// 	private Level level;
	
// 	private Set<Skill> skills;

// 	public Member(String name, Level level, Skill... skills) {
// 	}
	
// 	public String getName() {
// 		return null;
// 	}
	
// 	public Level getLevel() {
// 		return null;
// 	}
	
// 	public Set<Skill> getSkills() {
// 		return skills;
// 	}

// }
import java.util.EnumSet;
import java.util.Set;

class Member {
    private final String name;
    private final Level level;
    private final Set<Skill> skills;

    public Member(String name, Level level, Skill... skills) {
        this.name = name;
        this.level = level;
        this.skills = EnumSet.noneOf(Skill.class);
        for (Skill skill : skills) {
            this.skills.add(skill);
        }
    }

    public String getName() {
        return name;
    }

    public Level getLevel() {
        return level;
    }

    public Set<Skill> getSkills() {
        return skills;
    }
}

