package keepitsafe.web;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public List<Keep> getAll() {
        List<Keep> keeps = new ArrayList<>();
        keepDao.findAll().forEach(keeps::add);
        return keeps;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(HttpServletRequest request,
            @RequestBody Keep keep) throws URISyntaxException {
        if (keep != null) {
            keep = keepDao.save(keep);
            URI location = new URI(request.getRequestURL().append("/")
                                          .append(keep.getId()).toString());
            return ResponseEntity.created(location).build();
        }
        return ResponseEntity.badRequest().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody Keep keep,
            @PathVariable long id) {
        if (keep == null) {
            return ResponseEntity.badRequest().build();
        }

        // TODO Create when doesn´t exist

        Keep stoKeep = keepDao.findOne(id);
        stoKeep.setName(keep.getName());
        stoKeep = keepDao.save(keep);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable long id) {

        if (!keepDao.exists(id)) {
            return ResponseEntity.badRequest().build();
        }

        keepDao.delete(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Keep> get(@PathVariable long id) {

        if (!keepDao.exists(id)) {
            return ResponseEntity.badRequest().build();
        }

        Keep stoKeep = keepDao.findOne(id);
        return ResponseEntity.ok().body(stoKeep);
    }
}
