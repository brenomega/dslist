package io.github.breno.dslist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.github.breno.dslist.model.Game;
import io.github.breno.dslist.projection.GameMinProjection;

public interface GameRepository extends JpaRepository<Game, Long> {
	
	@Query(nativeQuery = true, value = """
			SELECT tg.id, tg.title, tg.game_year AS `year`, tg.img_url AS imgUrl,
			tg.short_description AS shortDescription, tb.position
			FROM TB_GAME tg
			JOIN TB_BELONGING tb
			ON tg.id = tb.game_id
			WHERE tb.game_list_id = :gameListId
			ORDER BY tb.position
			""")
	List<GameMinProjection> findByList(Long gameListId);
}
