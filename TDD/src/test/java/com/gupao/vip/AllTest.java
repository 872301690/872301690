package com.gupao.vip;

import com.gupao.vip.course.CourseSession;
import com.gupao.vip.course.CourseSessionTest;
import com.gupao.vip.rosterReporter.RosterReporterTest;
import com.gupao.vip.student.StudentTest;
import junit.framework.TestSuite;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({StudentTest.class, CourseSessionTest.class, RosterReporterTest.class})
public class AllTest {

}
