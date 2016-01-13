package com.netradio.junit4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    GenreServiceJUnit4.class,
    UserServiceJUnit4.class,
    CodecServiceJUnit4.class,
    OptionServiceJUnit4.class,
    UserDetailsServiceJUnit4.class,
    StreamServiceJUnit4.class,
    RegUserControlerJUnit4.class,
    IndexControllerJUnit4.class,
    GenreControllerJUnit4.class,
    OptionsControllerJUnit4.class,
    UsersControllerJUnit4.class,
    GenreControllerJUnit4.class,
    CodecControllerJUnit4.class,
    ImageControllerJUnit4.class,
    StreamControlerJUnit4.class
})
public class TestsSuite {
}
