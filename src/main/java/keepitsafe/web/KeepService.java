package keepitsafe.web;

import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import keepitsafe.entity.Keep;
import keepitsafe.entity.KeepDao;

@RestController
@Transactional
public class KeepService {

    private KeepDao keepDao;
    
    @RequestMapping("/keep")
    public List<Keep> getKeeps() {
        List<Keep> keeps = new LinkedList<>(); 
        keepDao.findAll().forEach(keeps::add);
        return keeps;
    }
    
}
