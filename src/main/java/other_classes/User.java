package other_classes;

import database_classes.Person;

public class User extends Person{
    private PersonStatus status;

    public PersonStatus getStatus() {
        return status;
    }

    public User(Person person, PersonStatus status) {
        setId(person.getId());
        setLogin(person.getLogin());
        setName(person.getName());
        setPassword(person.getPassword());
        this.status = status;

    }
}
