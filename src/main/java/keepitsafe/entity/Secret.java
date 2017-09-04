package keepitsafe.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Secret {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private String login;
    private String password;
        
    @ManyToOne
    private Keep keep;
    
    Secret(Keep keep, String name) {
        this.keep = keep;
        this.name = name;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((keep == null) ? 0 : keep.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Secret other = (Secret) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (keep == null) {
            if (other.keep != null)
                return false;
        } else if (!keep.equals(other.keep))
            return false;
        return true;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Keep getKeep() {
        return keep;
    }

    void setKeep(Keep keep) {
        this.keep = keep;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }
}
