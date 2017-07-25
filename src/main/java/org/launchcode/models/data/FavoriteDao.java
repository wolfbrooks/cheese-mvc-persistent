package org.launchcode.models.data;

import org.launchcode.models.Favorite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by brian on 6/19/2017.
 */

@Repository
@Transactional
public interface FavoriteDao extends CrudRepository<Favorite, Integer> {
}
