package vn.edu.iuh.fit.lab01.entities;

public class GrantAccess {
    private Account account;
    private Role role;
    private boolean isGrant;
    private String note;

    public Account getAccount() {
        return account;
    }

    public Role getRole() {
        return role;
    }

    public boolean isGrant() {
        return isGrant;
    }

    public String getNote() {
        return note;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setGrant(boolean grant) {
        isGrant = grant;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public GrantAccess() {
    }

    public GrantAccess(Account account, Role role, boolean isGrant, String note) {
        this.account = account;
        this.role = role;
        this.isGrant = isGrant;
        this.note = note;
    }

    @Override
    public String toString() {
        return "GrantAccess{" +
                "account=" + account +
                ", role=" + role +
                ", isGrant=" + isGrant +
                ", note='" + note + '\'' +
                '}';
    }
}
