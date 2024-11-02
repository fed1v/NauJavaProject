package ru.fed1v.NauJava.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    private String status;

    @Column(length = 9192)
    private String content;

    public Report() {
    }

    public Report(String status) {
        this.status = status;
    }

    public Report(String status, String content) {
        this.status = status;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return Objects.equals(id, report.id) && Objects.equals(status, report.status) && Objects.equals(content, report.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, content);
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public enum Status {
        CREATED,
        COMPLETED,
        ERROR;
    }
}
