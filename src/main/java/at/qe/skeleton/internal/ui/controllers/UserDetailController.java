package at.qe.skeleton.internal.ui.controllers;

import at.qe.skeleton.internal.model.Userx;
import at.qe.skeleton.internal.model.UserxRole;
import at.qe.skeleton.internal.services.UserxService;
import java.io.Serializable;
import java.util.Collections;

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
     * Attribute to cache the newly registered user
     */
    private Userx newUser;

    /**
     * Returns the newUser which is created from the user class
     * @return
     */
    //giving every new user the default role of admin so we can access a ongoin page
    //with the new user
    public Userx getNewUser() {
        if (newUser == null){
            newUser = new Userx();
            newUser.setRoles(Collections.singleton(UserxRole.ADMIN));
        }
        return newUser;
    }

    /**
     * Sets the new user. This user is targeted by any further calls of
     * {@link #doRegisterUser()}.
     *
     * @param newUser
     */
    public void setNewUser(Userx newUser) {
        this.newUser = newUser;
    }

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

    /**
     * Action to register the user by the inout data of the Html file.
     */
    //setEnabled to true, we could use this to set the premium option
    public void doRegisterUser(){
        newUser.setCreateUser(newUser);
        newUser.setEnabled(true);
        user = this.userService.saveUser(newUser);


    }

}
