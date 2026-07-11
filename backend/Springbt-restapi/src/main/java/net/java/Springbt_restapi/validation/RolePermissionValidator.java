package net.java.Springbt_restapi.validation;

import net.java.Springbt_restapi.enums.Role;
import org.springframework.stereotype.Component;

import org.springframework.security.access.AccessDeniedException;
import java.util.Map;
import java.util.Set;


//This class provides hierarchy level access to the employees based on their role
@Component
public class RolePermissionValidator {
    private static final Map<Role, Set<Role>> ALLOWED_CREATIONS =
            Map.of(
                    Role.ROLE_SUPER_ADMIN,
                    Set.of(
                            Role.ROLE_SUPER_ADMIN,
                            Role.ROLE_ADMIN,
                            Role.ROLE_MANAGER,
                            Role.ROLE_HR,
                            Role.ROLE_EMPLOYEE
                    ),
                    Role.ROLE_ADMIN,
                    Set.of(
                            Role.ROLE_MANAGER,
                            Role.ROLE_HR,
                            Role.ROLE_EMPLOYEE
                    ),
                    Role.ROLE_HR,
                    Set.of(
                            Role.ROLE_EMPLOYEE
                    ),
                    Role.ROLE_MANAGER,
                    Set.of(
                            Role.ROLE_EMPLOYEE
                    ),
                    Role.ROLE_EMPLOYEE,
                    Set.of(
                            Role.ROLE_EMPLOYEE
                    )

            );
    public void validateUserCreation(Role creatorRole, Role targetRole) throws AccessDeniedException {
        if(!ALLOWED_CREATIONS
                .getOrDefault(creatorRole, Set.of())
                .contains(targetRole)) {
            throw new AccessDeniedException(
                    creatorRole + " cannot create " + targetRole
            );
        }
    }
    private static final Map<Role, Integer> ROLE_HIERARCHY = Map.of(
            Role.ROLE_SUPER_ADMIN, 5,
            Role.ROLE_ADMIN, 4,
            Role.ROLE_HR, 3,
            Role.ROLE_MANAGER, 2,
            Role.ROLE_EMPLOYEE, 1
    );

    public void validateHierarchy(Role requesterRole, Role targetRole) throws AccessDeniedException{
        int requesterLevel = ROLE_HIERARCHY.get(requesterRole);
        int targetLevel = ROLE_HIERARCHY.get(targetRole);

        if(requesterLevel < targetLevel){
            throw new AccessDeniedException(
                    requesterRole + " cannot modify " + targetRole
            );
        }
    }

    public boolean canAccess(Role loggedInRole, Role role) {
        return ROLE_HIERARCHY.get(loggedInRole) > ROLE_HIERARCHY.get(role);
    }

    public Set<Role> getAccessibleRoles(Role loggedInRole) {
        return ALLOWED_CREATIONS.getOrDefault(loggedInRole, Set.of());
    }
}
