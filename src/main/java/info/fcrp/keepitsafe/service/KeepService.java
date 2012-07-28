package info.fcrp.keepitsafe.service;

import java.util.List;

import info.fcrp.keepitsafe.dao.KeepDAO;
import info.fcrp.keepitsafe.dao.SecretDAO;
import info.fcrp.keepitsafe.model.Keep;
import info.fcrp.keepitsafe.model.Secret;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Transactional
@RequestMapping(value = "/keep")
public class KeepService {
	@Autowired
	private KeepDAO keepDAO;

	@Autowired
	private SecretDAO secretDAO;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Keep findById(@PathVariable long id) {
		Keep keep = keepDAO.find(id);
		return keep;
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	List<Keep> findAll() {
		List<Keep> keeps = keepDAO.findAll();
		return keeps;
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	Keep create(@RequestBody Keep keep) {
		for (Secret sc : keep.getSecrets()) {
			sc.setKeep(keep);
		}
		keepDAO.save(keep);
		return keep;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody
	Keep create(@PathVariable long id, @RequestBody Keep keep) {
		keep.setId(id);
		keepDAO.update(keep);
		return keep;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void remove(@PathVariable long id) {
		Keep keep = keepDAO.find(id);
		keepDAO.delete(keep);
	}
	
}
