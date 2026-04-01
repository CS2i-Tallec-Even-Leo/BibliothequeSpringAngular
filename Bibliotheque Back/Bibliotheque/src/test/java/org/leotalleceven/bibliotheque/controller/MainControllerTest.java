package org.leotalleceven.bibliotheque.controller;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MainControllerTest {

    @Test
    void mainControllerCanBeInstantiated() {
        MainController controller = new MainController();
        
        assertThat(controller).isNotNull();
    }
}
