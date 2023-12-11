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
    public Userx getNewUser() {
        if (newUser == null){
            newUser = new Userx();
            newUser.setRoles(Collections.singleton(UserxRole.USER));
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
     * {@link #doReloadUser()}, {@link #doSaveUser()}, {@link #doDeleteUser()}.
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
     * Action to register the user by the inout data of the Html file.
     */
    //setEnabled to true, we could use this to set the premium option
    public void doRegisterUser(){
        newUser.setCreateUser(newUser);
        newUser.setEnabled(true);
        user = this.userService.saveUser(newUser);

    }

}
