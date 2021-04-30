package ru.geek.controller;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geek.persist.*;
import ru.geek.service.UserServiceImpl;
import ru.geek.service.RoleService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class UserController implements Serializable {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @EJB
    private UserServiceImpl userService;

    @EJB
    private RoleService roleService;

    @Inject
    private HttpSession session;

    @Getter
    @Setter
    private UserDto user;

    private List<UserDto> userList;

    @Getter
    private List<RoleDto> roleList;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        this.userList = userService.findAllWithRoleFetch();
        this.roleList = roleService.getAllRoles();
    }

    public List<UserDto> findAll() {
        return userList;
    }

    public String editCustomer(UserDto userDto) {
        this.user = userDto;
        userService.save(user);
        return "/admin/user_form.xhtml?faces-redirect=true";
    }

    public void deleteCustomer(UserDto userDto) {
        userService.delete(userDto.getId());
    }

    public String saveCustomer() {
        userService.save(user);
        return "/admin/user.xhtml?faces-redirect=true";
    }

    public String addCustomer() {
        this.user = new UserDto();
        return "/admin/user_form.xhtml?faces-redirect=true";
    }

    public String logout() {
        session.invalidate();
        return "/product.xhtml?faces-redirect=true";
    }
}