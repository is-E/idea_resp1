package test;

import com.hxs.dao.CourseMapper;
import com.hxs.domain.Course;
import com.hxs.domain.Coursevo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-service.xml")
public class ServiceTest {
    @Autowired
    private CourseMapper courseMapper;
    @Test
    public void test1(){
        List<Course> list = courseMapper.findCourseByCondition(new Coursevo());
        for (Course course : list) {
            System.out.println(course);
        }
    }
}
