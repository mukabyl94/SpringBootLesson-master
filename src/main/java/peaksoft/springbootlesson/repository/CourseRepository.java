package peaksoft.springbootlesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.springbootlesson.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
