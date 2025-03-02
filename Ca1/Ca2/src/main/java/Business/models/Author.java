package Business.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Author {

    private String username;
    private String name;
    private String penName;
    private Date born;

    public Author() {
    }

    public Author(String username, String name, String penName, Date born) {
        this.username = username;
        this.name = name;
        this.penName = penName;
        this.born = born;
    }
}
