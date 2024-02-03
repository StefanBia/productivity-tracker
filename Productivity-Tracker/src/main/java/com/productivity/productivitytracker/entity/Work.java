package com.productivity.productivitytracker.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;


import java.time.LocalDateTime;


public class Work {

    private Integer workId;


    private Integer lines;

    private LocalDateTime start;

    private LocalDateTime stop;

    private User user;

    Work(Integer lines,LocalDateTime start,LocalDateTime stop){

        this.lines = lines;
        this.start = start;
        this.stop = stop;
    }

    public Work() {

    }

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public Integer getLines() {
        return lines;
    }

    public void setLines(Integer lines) {
        this.lines = lines;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getStop() {
        return stop;
    }

    public void setStop(LocalDateTime stop) {
        this.stop = stop;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}