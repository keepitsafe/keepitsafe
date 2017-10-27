package keepitsafe.web;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import keepitsafe.TestConfig;
import keepitsafe.entity.Keep;
import keepitsafe.entity.KeepDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@WebAppConfiguration
@Transactional
public class KeepServiceTest {

    private MockMvc web;

    @Mock
    private KeepDao keepDao;

    @InjectMocks
    private KeepService keepService;

    private Keep stoKeep;
    private List<Keep> stoKeeps;

    private ObjectMapper oMapper;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        web = MockMvcBuilders.standaloneSetup(keepService).build();
        oMapper = new ObjectMapper();

        stoKeeps = new ArrayList<>();
        Keep keep = new Keep("ABC");
        keep.setId(1L);
        stoKeeps.add(keep);
        stoKeep = keep;

        keep = new Keep("DEF");
        keep.setId(2L);
        stoKeeps.add(keep);

        when(keepDao.findAll()).thenReturn(stoKeeps);
        when(keepDao.findOne(stoKeep.getId())).thenReturn(stoKeep);
        when(keepDao.exists(stoKeep.getId())).thenReturn(true);
        when(keepDao.save(stoKeeps.get(0))).thenReturn(stoKeep);
    }

    @Test
    public final void getKeeps() throws Exception {
        web.perform(get("/api/keep")).andExpect(status().isOk()).andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public final void getKeep() throws Exception {
        web.perform(get("/api/keep/1")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name", is(stoKeep.getName())));

        verify(keepDao).findOne(stoKeep.getId());
    }

    @Test
    public final void createKeep() throws Exception {
        web.perform(
                post("/api/keep").contentType(MediaType.APPLICATION_JSON).content(oMapper.writeValueAsString(stoKeep)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", Matchers.endsWith(String.valueOf(stoKeep.getId()))));
    }

    @Test
    public final void createKeepError() throws Exception {
        web.perform(post("/api/keep")).andExpect(status().isBadRequest());
    }

    @Test
    public final void updateKeep() throws Exception {
        Keep keep = new Keep(stoKeep.getName() + "-up");

        web.perform(put("/api/keep/1").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(oMapper.writeValueAsString(keep))).andExpect(status().isOk());

        verify(keepDao).save(keep);
    }

    @Test
    public final void deleteKeep() throws Exception {
        web.perform(delete("/api/keep/1")).andExpect(status().isOk());

        verify(keepDao).delete(stoKeep.getId());
    }

}
