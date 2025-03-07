package Business.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Author {

    private String name;
    private String penName;
    private Date born;
    private Date died;

    public Author() {
    }

    public Author(String name, String penName, Date born,Date died) {
        this.name = name;
        this.penName = penName;
        this.born = born;
        this.died=died;
    }
}
