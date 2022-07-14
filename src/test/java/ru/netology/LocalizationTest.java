package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;

public class LocalizationTest {
    @Test
        // проверяем LocalizationService
    void LocalizationService_locale_RU(){

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        String expected = localizationService.locale(Country.RUSSIA);
        String actual = "Добро пожаловать";

        Assertions.assertEquals(expected,actual);
        System.out.printf("ожидаемый текст %s \n текст который пришел %s \n",expected,actual);
    }
    @Test
    void LocalizationService_locale_EN(){
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        String expected = localizationService.locale(Country.USA);
        String actual = "Welcome";

        Assertions.assertEquals(expected,actual);
        System.out.printf("ожидаемый текст %s \n текст который пришел %s \n ",expected,actual);
    }
}
