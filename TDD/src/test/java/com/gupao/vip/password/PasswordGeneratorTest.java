package com.gupao.vip.password;

import org.junit.Assert;
import java.util.Random;
import org.junit.Test;

public class PasswordGeneratorTest {

    @Test
    public void generatorPassword(){
        PasswordGenerator generator = new PasswordGenerator();
        generator.setRandom(new PasswordGenerator.MockRandom('A'));
        Assert.assertEquals("ABCDEFGH",generator.generatorPassword());
        generator.setRandom(new PasswordGenerator.MockRandom('C'));
        Assert.assertEquals("CDEFGHIJ",generator.generatorPassword());
    }


}
