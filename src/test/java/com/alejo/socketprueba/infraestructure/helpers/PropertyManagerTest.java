package com.alejo.socketprueba.infraestructure.helpers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropertyManagerTest {

    private PropertyManager propertyManager;

    @BeforeEach
    void setUp() {
        propertyManager = PropertyManager.getInstance();
    }

    @Test
    void getInstance() {

        PropertyManager propertyManager1 = PropertyManager.getInstance();
        PropertyManager propertyManager2 = PropertyManager.getInstance();

        assertEquals(propertyManager,propertyManager1);
        assertEquals(propertyManager,propertyManager2);
        assertEquals(propertyManager1,propertyManager2);

    }

    @Test
    void getPropertyShouldReturnPropertyWhenExistsPropertyName() {

        String nameSocketExpected = "Socket Prueba";

        String nameSocketResult = propertyManager.getProperty("Name");
        System.out.println(nameSocketResult);

        assertEquals(nameSocketExpected,nameSocketResult);
    }

    @Test
    void getPropertyShouldReturnPropertyEmptyWhenNoExistsPropertyName() {

        String nameSocketExpected = "";

        String nameSocketResult = propertyManager.getProperty("Campo");
        System.out.println(nameSocketResult);

        assertEquals(nameSocketExpected,nameSocketResult);
    }

    @Test
    void getPropertyShouldReturnPropertyEmptyWhenPropertyIsNull() {

        String nameSocketExpected = "";

        String nameSocketResult = propertyManager.getProperty(null);
        System.out.println(nameSocketResult);

        assertEquals(nameSocketExpected,nameSocketResult);
    }
}