package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import javax.print.attribute.HashPrintJobAttributeSet;
import java.util.HashMap;

public class MessageSenderTest {


    @Test
        //проверка отправки сообщений на русском
    void ruTextSendTest() {
        HashMap<String, String> mock = new HashMap<String, String>();
        mock.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(Mockito.contains("172")))
                .thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        String expected = messageSender.send(mock);
        String actual = "Добро пожаловать";

        Assertions.assertEquals(expected, actual);
    }

    @Test
        //проверка отправки сообщений на английском
    void enTextSendTest() {
        HashMap<String, String> mock = new HashMap<String, String>();
        mock.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(Mockito.contains("96")))
                .thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        String expected = messageSender.send(mock);
        String actual = "Welcome";

        Assertions.assertEquals(expected, actual);
        Mockito.verify(localizationService,Mockito.times(2)).locale(Mockito.<Country>any());
        Mockito.verify(geoService,Mockito.times(1)).byIp(Mockito.<String>any());
    }

    @Test
    void textSendTest_Argument_Capture(){
        HashMap<String, String> mock = new HashMap<String, String>();
        mock.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(Mockito.contains("96")))
                .thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        String expected = messageSender.send(mock);
        String actual = "Welcome";

        ArgumentCaptor<String > argumentCaptor = ArgumentCaptor.forClass(String.class);
        Mockito.verify(geoService).byIp(argumentCaptor.capture());
        Assertions.assertEquals("96.44.183.149",argumentCaptor.getValue());

        ArgumentCaptor<Country> argumentCaptor1 = ArgumentCaptor.forClass(Country.class);
        Mockito.verify(localizationService,Mockito.times(2)).locale(argumentCaptor1.capture());
        Assertions.assertEquals(Country.USA,argumentCaptor1.getValue());
    }
}
