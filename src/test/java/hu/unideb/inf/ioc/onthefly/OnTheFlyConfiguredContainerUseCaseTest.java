package hu.unideb.inf.ioc.onthefly;

import hu.unideb.inf.ioc.cache.CacheStrategy;
import hu.unideb.inf.ioc.container.BaseContainer;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * These test are examples on how in out case the ioc could work.
 * The main point being the non-intrusiveness.
 * Aka you don't have to annotate the classes or modify them,
 * so you can hook them up without changing them.
 */
public class OnTheFlyConfiguredContainerUseCaseTest {

    public interface TestIOService{

        String getName();
    }

    public static class TestIOServiceImpl0 implements TestIOService{

        @Override
        public String getName(){
            // just for demonstration purposes
            // you can do here calls to System.in or
            // any other calls
            return "a";
        }
    }

    public static class TestIOServiceImpl1 implements TestIOService{

        @Override
        public String getName(){
            // just for demonstration purposes
            // you can do here calls to System.in or
            // any other calls
            return "b";
        }
    }

    @Data
    @SuperBuilder
    public static class TestEntity{

        private Integer hp;
    }

    @Data
    @SuperBuilder
    public static class TestPlayer extends TestEntity{

        private String name;
    }


    @RequiredArgsConstructor
    public static class TestPlayerService {

        private final TestIOService testIOService;

        public TestPlayer getTestPlayer(){
            return TestPlayer
                    .builder()
                    .hp(100)
                    .name(testIOService.getName())
                    .build();
        }
    }

    @Test
    void useCaseExample0(){
        OnTheFlyConfiguredContainer container = new OnTheFlyConfiguredContainer(new BaseContainer());
        container.configureSupplier(TestIOService.class,()->new TestIOServiceImpl0(), CacheStrategy.SINGLETON);
        TestPlayerService testPlayerService = container.getProvided(TestPlayerService.class);
        var testPlayer = testPlayerService.getTestPlayer();
        assertEquals("a",testPlayer.getName());
    }

    @Test
    void useCaseExample1(){
        OnTheFlyConfiguredContainer container = new OnTheFlyConfiguredContainer(new BaseContainer());
        container.configureSupplier(TestIOService.class,()->new TestIOServiceImpl1(), CacheStrategy.SINGLETON);
        TestPlayerService testPlayerService = container.getProvided(TestPlayerService.class);
        var testPlayer = testPlayerService.getTestPlayer();
        assertEquals("b",testPlayer.getName());
    }
}
