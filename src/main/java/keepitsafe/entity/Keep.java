package keepitsafe.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Keep {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    
    private String name;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Secret> secrets;
    
    Keep() {
    }
    
    public Keep(String name) {
        super();
        this.name = name;
        this.secrets = new ArrayList<>();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        Keep other = (Keep) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
    
    public long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public Secret addSecret(String name) {
        Secret secret = new Secret(this, name);
        secrets.add(secret);
        
        return secret;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
