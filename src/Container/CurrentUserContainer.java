package Container;

import EntityClasses.EntityUser;
import controller.User;

public class CurrentUserContainer {
    private static EntityUser currentUser;

    public static void setCurrentUser(EntityUser user) {
        currentUser = user;
    }

    public static void removeCurrentuser() {
        currentUser = null;
    }

    public static EntityUser getCurrentUser() {
        if (currentUser == null) return null;
        return currentUser;
    }
}
