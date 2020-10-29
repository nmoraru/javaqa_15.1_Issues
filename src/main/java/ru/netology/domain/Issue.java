package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Issue implements Comparable<Issue> {
    private int id;
    private HashSet<String> label;
    private String author;
    private String assignee;
    private HashSet<String> project;
    private String milestones;
    private boolean isOpen;

    @Override
    public int compareTo(Issue issue) {
        return id - issue.id;
    }
}
