package com.fooddelivery.fooddelivery.controllers;
import com.fooddelivery.fooddelivery.entities.MenuItem;
import com.fooddelivery.fooddelivery.repositories.MenuItemRepository;
import com.fooddelivery.fooddelivery.services.MenuItemService;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MenuItemControllerTest {

    @Mock
    MenuItemRepository menuItemRepository;
    MenuItemService menuItemService;
    AutoCloseable closeable;
    MenuItem menuItemDto;

    @BeforeEach
    public void setUp() throws Exception {
        closeable = org.mockito.MockitoAnnotations.openMocks(this);
        menuItemService = new MenuItemService(menuItemRepository);
        menuItemDto = MenuItem.builder()
                .itemName("Test Menu Item")
                .itemImage("testimage")
                .description("Test Description")
                .price(10.00)
                .availability(true)
                .restaurantId(1L)
                .build();
    }

    @AfterEach
    public void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    public void getAllMenuItems() {
        mock(MenuItemController.class);
        mock(MenuItemRepository.class);
        List<MenuItem> menuItemList =new ArrayList<>();
        when(menuItemRepository.findAll()).thenReturn(menuItemList);
    }

    @Test
    public void getMenuItemById() {
        mock(MenuItemController.class);
        mock(MenuItemRepository.class);
        when(menuItemRepository.getById(1L)).thenReturn(menuItemDto);
    }

    @Test
    public void createMenuItem() {
        mock(MenuItemController.class);
        mock(MenuItemRepository.class);
        when(menuItemRepository.save(menuItemDto)).thenReturn(menuItemDto);
    }
}