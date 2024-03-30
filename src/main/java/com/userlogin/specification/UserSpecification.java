package com.userlogin.specification;

import com.userlogin.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserSpecification {

    public static Specification<User> byUserNameOrEmail(String userName, String email) {
        return ((root, query, criteriaBuilder) ->
            criteriaBuilder.or(criteriaBuilder.equal(root.get("userName"), userName),
                    criteriaBuilder.equal(root.get("email"), email))
        );
    }
    public static Specification<User> byUserName(String userName) {
        return ((root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("userName"), userName)

        );
    }
}
