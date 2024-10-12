package model;

// Represents an Account having a name, email
abstract class Account {
    private String name;
    private final String email;

    // EFFECTS: constructs an account having a name and an email
    public Account(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // EFFECT: return non-sensitive account detail information
    public StringBuilder getUserAccountDetails() {
        StringBuilder accountInfo = new StringBuilder();
        accountInfo.append("\nAccount Details:");
        accountInfo.append("\n***********************************\n");
        accountInfo.append("\nName: ").append(name);
        accountInfo.append("\nEmail: ").append(email);
        accountInfo.append("\n***********************************\n");
        return accountInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

}