package uz.pdp.todo;

import org.springframework.security.core.context.SecurityContextHolder;
import uz.pdp.todo.config.CustomUserDetails;

public class Utils {
    public static CustomUserDetails sessionUser(){
        return (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
