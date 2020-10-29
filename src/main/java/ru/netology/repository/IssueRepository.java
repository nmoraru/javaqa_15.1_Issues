package ru.netology.repository;

import ru.netology.domain.Issue;

import java.util.ArrayList;
import java.util.List;

public class IssueRepository {
    private List<Issue> issues = new ArrayList<>();

    public List<Issue> findAll() {
        return issues;
    }

    public boolean save(Issue issue) {
        return issues.add(issue);
    }

    public void openById(int id) {
        for (Issue issue : issues) {
            if (issue.getId() == id && !issue.isOpen()) {
                issue.setOpen(true);
            }
        }
    }

    public void closeById(int id) {
        for (Issue issue : issues) {
            if (issue.getId() == id && issue.isOpen()) {
                issue.setOpen(false);
            }
        }
    }
}
