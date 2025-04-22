package Container;

import EntityClasses.EntityProvider;

public class CurrentProviderContainer {
    private static EntityProvider currentProvider;

    public static void setCurrentProvider(EntityProvider provider) {
        currentProvider = provider;
    }

    public static EntityProvider getCurrentProvider() {
        if (currentProvider == null) return null;
        return currentProvider;
    }

    public static void removeCurrentUser() {
        currentProvider = null;
    }
}
