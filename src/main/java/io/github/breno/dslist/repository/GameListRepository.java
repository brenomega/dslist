package io.github.breno.dslist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.breno.dslist.model.GameList;

public interface GameListRepository extends JpaRepository<GameList, Long> {

}
