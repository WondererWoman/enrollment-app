package org.launchcode.models.data;

import org.launchcode.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


/**
 * Created by Erin DeVries on 4/11/2017.
 */
@Repository
@Transactional
public interface UserDao extends CrudRepository<User, Integer> {

}
