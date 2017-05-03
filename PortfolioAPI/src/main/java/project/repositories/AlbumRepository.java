package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import project.models.AlbumModel;

public interface AlbumRepository extends JpaRepository<AlbumModel, Integer>, QueryDslPredicateExecutor<AlbumModel> {

}
