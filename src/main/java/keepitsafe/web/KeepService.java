package keepitsafe.web;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import keepitsafe.entity.Keep;
import keepitsafe.entity.KeepDao;

@RestController
@Transactional
@RequestMapping("/api/keep")
public class KeepService {

    private KeepDao keepDao;

    @Autowired
    public KeepService(KeepDao keepDao) {
        super();
        this.keepDao = keepDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Keep> getKeeps() {
        List<Keep> keeps = new ArrayList<>();
        keepDao.findAll().forEach(keeps::add);
        return keeps;
    }
}
