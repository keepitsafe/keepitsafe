package keepitsafe.entity;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import keepitsafe.Config;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Config.class })
@Transactional
public class SecretDaoTest {

    @Autowired
    private EntityManager em;
    
    @Autowired
    private SecretDao dao;
    
    @Test
    public void save() {
        Secret secret = new Secret("weak password");
        
        dao.save(secret);
        
        assertTrue(secret.getId() > 0);
    }
    
    @Test
    public void load() {
        Secret secret = new Secret("weak password");
        
        dao.save(secret);
        
        long id = secret.getId();
        
        Secret stSecret = dao.findOne(id);
        
        assertEquals(secret, stSecret);
    }
    
    @Test
    public void loadAll() {
        dao.save(new Secret("weak password"));
        dao.save(new Secret("another weak password"));
        dao.save(new Secret("also weak password"));

        List<Secret> secrets = new ArrayList<>();
        dao.findAll().forEach(secrets::add);
        
        assertEquals(3, secrets.size());
    }
}
