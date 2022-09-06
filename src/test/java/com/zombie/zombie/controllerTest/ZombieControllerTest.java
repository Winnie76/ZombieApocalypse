package com.zombie.zombie.controllerTest;

import com.alibaba.fastjson.JSON;

import com.zombie.zombie.dto.CreatureDto;
import com.zombie.zombie.dto.GameConfigDto;
import com.zombie.zombie.dto.ZombieDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.util.ArrayList;

import static com.alibaba.fastjson2.JSON.toJSONString;

@SpringBootTest
@AutoConfigureMockMvc
class ZombieControllerTest {
    @Resource
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;
    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void infection() throws Exception {
        Logger logger = LoggerFactory.getLogger(ZombieControllerTest.class);
        GameConfigDto gameConfig = new GameConfigDto();
        gameConfig.setGridSize(4);
        gameConfig.setCommands("RDRU");
        ZombieDto zombieDto = new ZombieDto(3, 1);
        gameConfig.setZombie(zombieDto);
        ArrayList<CreatureDto> creatureDtos = new ArrayList<>();
        creatureDtos.add(new CreatureDto(0, 1));
        creatureDtos.add(new CreatureDto(1, 1));
        creatureDtos.add(new CreatureDto(1, 2));
        gameConfig.setCreatures(creatureDtos);
        System.out.println(JSON.toJSONString(gameConfig));
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/getValue")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJSONString(gameConfig)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        logger.info(mvcResult.getResponse().getContentAsString());
    }

}
