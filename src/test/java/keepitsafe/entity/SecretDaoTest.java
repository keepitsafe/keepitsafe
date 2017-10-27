package keepitsafe.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import keepitsafe.TestDbConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestDbConfig.class })
@Transactional
public class SecretDaoTest {
    @Autowired
    private SecretDao secretDao;
   
    @Autowired
    private KeepDao keepDao;

    private Keep keep;
    
    @Before
    public void setup() {
        keep = keepDao.save(new Keep("Passwords"));
    }
    
    @Test
    public void save() {
        Secret secret = new Secret(keep, "weak password");
        secretDao.save(secret);

        assertTrue(secret.getId() > 0);
    }
    
    @Test
    public void load() {
        Secret secret = new Secret(keep, "weak password");
        secretDao.save(secret);
        
        long id = secret.getId();
        Secret stSecret = secretDao.findOne(id);
        
        assertEquals(secret, stSecret);
    }
    
    @Test
    public void loadAll() {
        secretDao.save(new Secret(keep, "weak password"));
        secretDao.save(new Secret(keep, "another weak password"));
        secretDao.save(new Secret(keep, "also weak password"));

        List<Secret> secrets = new ArrayList<>();
        secretDao.findAll().forEach(secrets::add);
        
        assertEquals(3, secrets.size());
    }
    
    @Test
    public void addSecrets() {
        Keep keep = new Keep("Passwords");
        keep.addSecret("weak password");
        keep.addSecret("another weak password");
        keepDao.save(keep);
        
        List<Secret> secrets = new ArrayList<>();
        secretDao.findByKeep(keep).forEach(secrets::add);
        
        assertEquals(2, secrets.size());
    }
    
    @Test
    public void deleteKeep() {
        Keep keep = new Keep("Passwords");
        keep.addSecret("weak password");
        keep.addSecret("another weak password");
        
        keepDao.save(keep);
        keepDao.delete(keep);
        
        List<Secret> secrets = new ArrayList<>();
        secretDao.findAll().forEach(secrets::add);
        
        assertEquals(0, secrets.size());
    }
}
