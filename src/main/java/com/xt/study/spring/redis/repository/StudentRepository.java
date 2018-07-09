package com.xt.study.spring.redis.repository;

import com.xt.study.spring.redis.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, String> {

}
