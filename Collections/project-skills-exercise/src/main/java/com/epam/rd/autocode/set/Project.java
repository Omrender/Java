package com.epam.rd.autocode.set;

// import java.util.Set;

// public class Project {
	
// 	private static class Entry {
// 	}

// 	public Project(Role... roles) {
// 	}

// 	public int getConformity(Set<Member> team) {
// 		return 0;
// 	}
	
// }
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Project {
    private final List<Role> roles;

    public Project(Role... roles) {
        this.roles = new ArrayList<>();
        for (Role role : roles) {
            this.roles.add(role);
        }
    }

    public List<Role> getRoles() {
        return roles;
    }

    public int getConformity(Set<Member> team) {
        List<Entry> projectEntries = new ArrayList<>();
        List<Entry> teamEntries = new ArrayList<>();

        // Generate project entries
        for (Role role : roles) {
            Level roleLevel = role.getLevel();
            for (Skill skill : role.getSkills()) {
                projectEntries.add(new Entry(roleLevel, skill));
            }
        }

        int originalSize = projectEntries.size();

        // Generate team entries
        for (Member member : team) {
            Level memberLevel = member.getLevel();
            for (Skill skill : member.getSkills()) {
                teamEntries.add(new Entry(memberLevel, skill));
            }
        }

        // Remove common elements between projectEntries and teamEntries
        projectEntries.removeAll(teamEntries);

        // Calculate the compliance percentage
        int remainingSize = projectEntries.size();
        int matchedSize = originalSize - remainingSize;

        return (matchedSize * 100) / originalSize;
    }

    private static class Entry {
        private final Level level;
        private final Skill skill;

        public Entry(Level level, Skill skill) {
            this.level = level;
            this.skill = skill;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry entry = (Entry) o;
            return level == entry.level && skill == entry.skill;
        }

        @Override
        public int hashCode() {
            int result = level.hashCode();
            result = 31 * result + skill.hashCode();
            return result;
        }
    }
}

