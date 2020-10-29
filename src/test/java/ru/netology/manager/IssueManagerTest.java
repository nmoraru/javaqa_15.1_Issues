package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IssueManagerTest {
    IssueRepository repository = new IssueRepository();
    IssueManager manager = new IssueManager(repository);

    private Issue issue1 = new Issue(
            1,
            new HashSet<String>(Arrays.asList("label1", "label2", "label3")),
            "Nikita",
            "Mary",
            new HashSet<String>(Arrays.asList("project1", "project2", "project3")),
            "release 1",
            true
    );

    private Issue issue2 = new Issue(
            2,
            new HashSet<String>(Arrays.asList("label1", "label2", "label3")),
            "Ivan",
            "Selina",
            new HashSet<String>(Arrays.asList("project1", "project2", "project3")),
            "release 1",
            false
    );

    private Issue issue3 = new Issue(
            3,
            new HashSet<String>(Arrays.asList("label4")),
            "Ivan",
            "Selina",
            new HashSet<String>(Arrays.asList("project1", "project3")),
            "release 1",
            false
    );

    private Issue issue4 = new Issue(
            4,
            new HashSet<String>(Arrays.asList("label4")),
            "Ivan",
            "Selina",
            new HashSet<String>(Arrays.asList("project1", "project3")),
            "release 1",
            true
    );

    @BeforeEach
    void setUp() {
        manager.addIssue(issue1);
        manager.addIssue(issue2);
        manager.addIssue(issue3);
        manager.addIssue(issue4);
    }

    @Test
    void shouldOpenIssue() {
        manager.openIssue(2);
        List<Issue> expected = new ArrayList<>(List.of(issue1, issue2, issue4));
        List<Issue> actual = manager.findOpenedIssues(true);
        assertEquals(expected, actual);
    }

    @Test
    void shouldCloseIssue() {
        manager.closedIssue(4);
        List<Issue> expected = new ArrayList<>(List.of(issue2, issue3, issue4));
        List<Issue> actual = manager.findOpenedIssues(false);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindByAuthor() {
        List<Issue> expected = new ArrayList<>(List.of(issue1));
        List<Issue> actual = manager.findByAuthor("Nikita");
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindByAssignee() {
        List<Issue> expected = new ArrayList<>(List.of(issue1));
        List<Issue> actual = manager.findByAssignee("Mary");
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindByLabel() {
        List<Issue> expected = new ArrayList<>(List.of(issue1, issue2));
        List<Issue> actual = manager.findByLabel(new HashSet<String>(Arrays.asList("label1")));
        assertEquals(expected, actual);
    }

    @Test
    void shouldSortByNewest() {
        List<Issue> expected = new ArrayList<>(List.of(issue4, issue3, issue2, issue1));
        List<Issue> actual = manager.sortByNewest();
        assertEquals(expected, actual);
    }

}