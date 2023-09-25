package vn.edu.iuh.fit.lab01.entities;

import java.time.LocalDateTime;

public class Log {
    private long id;
    private Account account;
    private LocalDateTime loginDateTime;
    private LocalDateTime logoutDateTime;
    private String note;

    public long getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public LocalDateTime getLoginDateTime() {
        return loginDateTime;
    }

    public LocalDateTime getLogoutDateTime() {
        return logoutDateTime;
    }

    public String getNote() {
        return note;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setLoginDateTime(LocalDateTime loginDateTime) {
        this.loginDateTime = loginDateTime;
    }

    public void setLogoutDateTime(LocalDateTime logoutDateTime) {
        this.logoutDateTime = logoutDateTime;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Log() {
    }

    public Log(long id, Account account, LocalDateTime loginDateTime, LocalDateTime logoutDateTime, String note) {
        this.id = id;
        this.account = account;
        this.loginDateTime = loginDateTime;
        this.logoutDateTime = logoutDateTime;
        this.note = note;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", account=" + account +
                ", loginDateTime=" + loginDateTime +
                ", logoutDateTime=" + logoutDateTime +
                ", note='" + note + '\'' +
                '}';
    }
}
