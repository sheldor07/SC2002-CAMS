package UI.Portal;
/**
 * The `RoleHandler` interface defines the contract for classes that handle specific roles within the CAMs application.
 * Role handlers are responsible for displaying and managing role-specific user interfaces and interactions.
 * Classes implementing this interface should provide implementations for the `displayPage()` method to handle role-specific actions.
 *
 * @author weiya
 */
public interface RoleHandler {
    /**
     * Displays and manages the user interface for the specific role.
     * This method should handle role-specific actions and interactions.
     *
     * @return `true` if the user chooses to log out or exit the role, `false` otherwise.
     */
    boolean displayPage();
}
