package keepitsafe.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import keepitsafe.Config;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Config.class })
@Transactional
public class KeepDaoTest {

    @Autowired
    private KeepDao dao;
    
    @Test
    public void save() {
        Keep keep = new Keep("Servers Passwords");
        
        dao.save(keep);
        
        assertTrue(keep.getId() > 0);
    }
    
    @Test
    public void load() {
        Keep keep = new Keep("Servers Passwords");
        
        dao.save(keep);
        long id = keep.getId();
        
        Keep stKeep = dao.findOne(id);
        
        assertEquals(keep, stKeep);
    }
    
    @Test
    public void loadAll() {
        dao.save(new Keep("Servers Passwords"));
        dao.save(new Keep("DBs Passwords"));
        dao.save(new Keep("Admin Passwords"));
        
        List<Keep> keeps = new ArrayList<>();
        dao.findAll().forEach(keeps::add);
        
        assertEquals(3, keeps.size());
    }
}
