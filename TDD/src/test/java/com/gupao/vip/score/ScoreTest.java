package com.gupao.vip.score;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;
import org.junit.rules.ExpectedException;

import java.util.SortedSet;

public class ScoreTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Test
    public void score(){
        Score score = new Score();
        thrown.expect(NumberFormatException.class);
        thrown.expectMessage("For input string: \"as\"");
        score.score("as");
    }

    @Test
    public void isValid(){
        Score score = new Score();
        Assert.assertTrue(score.isValid("75"));
        Assert.assertFalse(score.isValid("as"));
    }
}
