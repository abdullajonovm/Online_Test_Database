package service;

import config.DbConfig;
import config.DbConfigUsers;
import entity.model.User;

import java.util.List;

public class ChekService {
    public static boolean ChekEmail(String email){


        for (User user : DbConfigUsers.OpenUserDatabase()) {
            if (user.getEmail().equals(email)){
                return false;
            }
        }
        return true;
    }
}
