package org.launchcode.models.data;

import org.launchcode.models.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Erin DeVries on 4/9/2017.
 */
@Repository
@Transactional
public interface CourseDao extends CrudRepository<Course, Integer> {
}
