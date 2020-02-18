package ru.esphere.school.test;

import org.junit.Assert;
import org.junit.Test;
import ru.esphere.school.service.Finder;
import ru.esphere.school.data.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class FinderTest {

    @Test
    public void testShouldFindOldMembers() {
        Finder finder = new Finder();

        int age = 32;

        List<MembersGroup> groups = Arrays.asList(
                new MembersGroup("group1", Arrays.asList(
                        new Member("Juliana", 27),
                        new Member("Trudy", 18),
                        new Member("Frank", 33),
                        new Member("Joe", 32)
                )),
                new MembersGroup("group2", Arrays.asList(
                        new Member("Thomas", 18),
                        new Member("John", 40),
                        new Member("Helen", 38)
                ))
        );

        Set<String> resultSet = finder.findOldMembers(groups, age);

        Object[] resultArr = resultSet.toArray();
        // Сортируем для того,чтобы последовательность элементов была постояннй
        Arrays.sort(resultArr);

        Assert.assertArrayEquals(resultArr, new String[]{"Frank", "Helen", "John"});
    }

    @Test
    public void testShouldNotFindOldMembers() {
        Finder finder = new Finder();

        int age = 40;

        List<MembersGroup> groups = Arrays.asList(
                new MembersGroup("group1", Arrays.asList(
                        new Member("Juliana", 27),
                        new Member("Trudy", 18),
                        new Member("Frank", 33),
                        new Member("Joe", 32)
                )),
                new MembersGroup("group2", Arrays.asList(
                        new Member("Thomas", 18),
                        new Member("John", 40),
                        new Member("Helen", 38)
                ))
        );

        Set<String> resultSet = finder.findOldMembers(groups, age);

        Assert.assertEquals(resultSet.size(), 0);
    }

    @Test
    public void testShouldIgnoreNullValue() {
        Finder finder = new Finder();

        int age = 20;

        Set<String> resultSet = finder.findOldMembers(null, age);

        Assert.assertEquals(resultSet.size(), 0);
    }
}
