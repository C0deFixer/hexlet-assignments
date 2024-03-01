package exercise.dto.users;

import exercise.model.User;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

import lombok.Getter;

// BEGIN
@Getter
public class UsersPage {
    public final List<User> usersList;
    public final String term;

    public UsersPage (List<User> uL, String term){
        this.term = term;
        if (!StringUtils.isEmpty(term)) {
            this.usersList = filterUsers(uL, term);
        } else {
            this.usersList = uL;
        }
    }
    public List<User> filterUsers(List<User> uL, String substring) {
        return uL.stream()
                .filter(x -> StringUtils.startsWithIgnoreCase(x.getFirstName(),substring))
                .toList();
    }

}
// END
