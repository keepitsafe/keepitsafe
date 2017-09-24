package keepitsafe.web;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import keepitsafe.config.Config;
import keepitsafe.config.WebConfig;
import keepitsafe.entity.KeepDao;
import keepitsafe.entity.Keep;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Config.class, WebConfig.class })
@WebAppConfiguration
@Transactional
public class KeepServiceTest {

    @Autowired
    private  WebApplicationContext wac;
    
    private MockMvc web;
    
    @Mock
    private KeepDao keepDao;
    
    @InjectMocks
    private KeepService keepService;
    
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
//        web = MockMvcBuilders.webAppContextSetup(wac).build();
        web = MockMvcBuilders.standaloneSetup(keepService).build();
        
        when(keepDao.findAll()).then(inv -> {
            List<Keep> keeps = new ArrayList<>();
            keeps.add(new Keep("ABC"));
            keeps.add(new Keep("DEF"));
            return keeps;
        });
    }
    
    @Test
    public final void test() throws Exception {
        web.perform(get("/api/keep"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$").isArray())
           .andDo(MockMvcResultHandlers.print());
    }

}
