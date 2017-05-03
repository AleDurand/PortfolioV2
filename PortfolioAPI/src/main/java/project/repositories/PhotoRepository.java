package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import project.models.PhotoModel;

@Repository
public interface PhotoRepository extends JpaRepository<PhotoModel, Integer>, QueryDslPredicateExecutor<PhotoModel>{

}
