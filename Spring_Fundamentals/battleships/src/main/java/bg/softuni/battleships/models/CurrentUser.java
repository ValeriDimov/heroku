package bg.softuni.battleships.models;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {
    private long id;

    private String fullName;

    private boolean isLoggedIn = false;

    public long getId() {
        return id;
    }

    public CurrentUser setId(long id) {
        this.id = id;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public CurrentUser setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public boolean isLoggedIn() {
        return isLoggedIn = true;
    }

    public CurrentUser setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
        return this;
    }

    public void clear() {
        this.isLoggedIn = false;
        this.fullName = null;
        this.id = 0;
    }
}
