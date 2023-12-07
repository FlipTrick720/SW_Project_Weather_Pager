package at.qe.skeleton.internal.ui.controllers;

import at.qe.skeleton.internal.model.Userx;
import at.qe.skeleton.internal.services.UserxService;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Controller for the user detail view.
 *
 * This class is part of the skeleton project provided for students of the
 * course "Software Architecture" offered by Innsbruck University.
*/
@Component
@Scope("view")
public class UserDetailController implements Serializable {

    @Autowired
    private UserxService userService;

    /**
     * Attribute to cache the currently displayed user
     */
    private Userx user;

    /**
     * Sets the currently displayed user and reloads it form db. This user is
     * targeted by any further calls of
     * {@link #doReloadUser()}, {@link #doSaveUser()}, {@link #doDeleteUser()},
     * {@link #doEditFirstName(String)}, {@link #doEditLastName(String)}, {@link #doEditEmail(String)}, {@link #doEditPassword(String)}.
     *
     * @param user
     */
    public void setUser(Userx user) {
        this.user = user;
        doReloadUser();
    }

    /**
     * Returns the currently displayed user.
     *
     * @return
     */
    public Userx getUser() {
        return user;
    }

    /**
     * Action to force a reload of the currently displayed user.
     */
    public void doReloadUser() {
        user = userService.loadUser(user.getUsername());
    }

    /**
     * Action to save the currently displayed user.
     */
    public void doSaveUser() {
        user = this.userService.saveUser(user);
    }

    /**
     * Action to delete the currently displayed user.
     */
    public void doDeleteUser() {
        this.userService.deleteUser(user);
        user = null;
    }

    /**
     * Action to edit First Name of the displayed user.
     */
    public void doEditFirstName(String newName) {
        this.userService.changeFirstName(user, newName);
    }

    /**
     * Action to edit Last Name of the displayed user.
     */
    public void doEditLastName(String newName) {
        this.userService.changeEmail(user, newName);
    }

    /**
     * Action to edit email of the displayed user.
     */
    public void doEditEmail(String email) {
        this.userService.changeEmail(user, email);
    }

    /**
     * Action to edit Password of the displayed user.
     */
    public void doEditPassword(String newPassword) {
        this.userService.changePassword(user, newPassword);
    }

}
