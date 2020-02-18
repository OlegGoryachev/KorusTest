package ru.esphere.school.service;

import ru.esphere.school.data.Member;
import ru.esphere.school.data.MembersGroup;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.*;

public class Finder {
    /**
     * Поиск групп людей старше определенного возраста.
     *
     * @param groups группы
     * @param targetAge возраст для поиска
     * @return список имен групп из списка групп старше возраста targetAge
     */
    public Set<String> findOldMembers(List<MembersGroup> groups, int targetAge) {
        if (groups == null) {
            return new HashSet<>();
        }

        return groups.stream()
                .flatMap(g -> g.getMembers().stream())
                .filter(u -> u.getAge() > targetAge)
                .map(Member::getName)
                .collect(Collectors.toSet());
    }
}
