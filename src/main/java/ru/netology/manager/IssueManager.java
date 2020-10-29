package ru.netology.manager;

import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.*;
import java.util.function.Predicate;

public class IssueManager {
    private IssueRepository repository;

    public IssueManager(IssueRepository repository) {
        this.repository = repository;
    }

    public void addIssue(Issue issue) {
        repository.save(issue);
    }

    public void openIssue(int id) {
        repository.openById(id);
    }

    public void closedIssue(int id) {
        repository.closeById(id);
    }

    public List<Issue> findOpenedIssues(boolean isOpen) {
        Predicate<Boolean> openedIssue = t -> t.equals(isOpen);
        List<Issue> issues = new ArrayList<>();
        for (Issue issue : repository.findAll())
            if (openedIssue.test(issue.isOpen())) {
                issues.add(issue);
            }
        return issues;
    }

    public List<Issue> findByAuthor(String author) {
        Predicate<String> byAuthor = t -> t.equals(author);
        List<Issue> issues = new ArrayList<>();
        for (Issue issue : repository.findAll())
            if (byAuthor.test(issue.getAuthor())) {
                issues.add(issue);
            }
        return issues;
    }

    public List<Issue> findByAssignee(String assignee) {
        Predicate<String> byAssignee = t -> t.equals(assignee);
        List<Issue> issues = new ArrayList<>();
        for (Issue issue : repository.findAll())
            if (byAssignee.test(issue.getAssignee())) {
                issues.add(issue);
            }
        return issues;
    }

    public List<Issue> findByLabel(Set<String> label) {
        Predicate<Set<String>> byLabel = t -> t.containsAll(label);
        List<Issue> issues = new ArrayList<>();
        for (Issue issue : repository.findAll())
            if (byLabel.test(issue.getLabel())) {
                issues.add(issue);
            }
        return issues;
    }


    public List<Issue> sortByNewest() {
        Comparator byNewest = Comparator.reverseOrder();
        List<Issue> issues = new ArrayList<>();
        issues.addAll(repository.findAll());
        ((ArrayList<Issue>) issues).sort(byNewest);
        return issues;
    }

}
