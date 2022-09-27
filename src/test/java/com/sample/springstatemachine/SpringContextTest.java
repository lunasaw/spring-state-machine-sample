package com.sample.springstatemachine;

import com.sample.springstatemachine.application.config.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { SimpleStateMachineConfiguration.class, SimpleEnumStateMachineConfiguration.class,
		JunctionStateMachineConfiguration.class, HierarchicalStateMachineConfiguration.class,
		ForkJoinStateMachineConfiguration.class })
public class SpringContextTest {

    @Test
    public void whenSpringContextIsBootstrapped_thenNoExceptions() {
    }
}
