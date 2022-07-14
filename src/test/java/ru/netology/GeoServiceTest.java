package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;

public class GeoServiceTest {
    @Test
        //проверка русского IP
    void GeoService_byIpTestRu() {
        GeoService geoService = Mockito.mock(GeoService.class);
        Location location = Mockito.mock(Location.class);
        Mockito.when(geoService.byIp(Mockito.contains("172")))
                .thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));

        Location expected = geoService.byIp("172.0.32.11");
        Location actual = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        Assertions.assertEquals(expected.getClass(), actual.getClass());

        //проверка файлов
        System.out.println(expected.toString() + " expected");
        System.out.println(actual.toString() + " actual");

    }

    @Test // проверка английского Ip
    void GeoService_byIpTestEn() {
        GeoService geoService = Mockito.mock(GeoService.class);
        Location location = Mockito.mock(Location.class);
        Mockito.when(geoService.byIp(Mockito.contains("96")))
                .thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));

        Location expected = geoService.byIp("96.44.183.149");
        Location actual = new Location("New York", Country.USA, " 10th Avenue", 32);
        Assertions.assertEquals(expected.getClass(), actual.getClass());

        //проверка файлов
        System.out.println(expected.toString() + " expected");
        System.out.println(actual.toString() + " actual");

    }


}
