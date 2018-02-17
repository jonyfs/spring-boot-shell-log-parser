package br.com.jonyfs.parser.log;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends CrudRepository<Log, Long>, QuerydslPredicateExecutor<Log>, QuerydslBinderCustomizer<QLog> {
    @Override
    default void customize(QuerydslBindings bindings, QLog root) {

    }
}
