package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import project.models.AlbumModel;

@Repository
public interface AlbumRepository extends JpaRepository<AlbumModel, Integer>, QueryDslPredicateExecutor<AlbumModel> {

}
