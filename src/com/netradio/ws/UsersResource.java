package com.netradio.ws;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.netradio.entity.User;
import com.netradio.service.UserService;

@Path("users")
public class UsersResource {

    private UserService srv;

    public void setSrv(UserService srv) {
        this.srv = srv;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User[] users() {
        List<User> list = srv.getUsers();
        return list.toArray(new User[list.size()]);
    }
}
