package com.gupao.vip.rosterReporter;

import com.gupao.vip.student.Student;
import org.junit.Assert;
import org.junit.Test;

public class ReportCardTest {

    @Test
    public void message(){
        ReportCard reportCard = new ReportCard();
        Assert.assertEquals(ReportCard.A_MESSAGE,reportCard.getMessage(Student.Grade.A));
        Assert.assertEquals(ReportCard.B_MESSAGE,reportCard.getMessage(Student.Grade.B));
        Assert.assertEquals(ReportCard.C_MESSAGE,reportCard.getMessage(Student.Grade.C));
        Assert.assertEquals(ReportCard.D_MESSAGE,reportCard.getMessage(Student.Grade.D));
        Assert.assertEquals(ReportCard.F_MESSAGE,reportCard.getMessage(Student.Grade.F));
    }
}
