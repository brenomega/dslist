package io.github.breno.dslist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.breno.dslist.model.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

}
