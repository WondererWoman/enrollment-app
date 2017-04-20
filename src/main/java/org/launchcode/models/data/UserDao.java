package org.launchcode.models.data;

import org.launchcode.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Erin DeVries on 4/11/2017.
 */
@Repository
@Transactional
public interface UserDao extends CrudRepository<User, Integer> {

}
