package com.marta.sandbox.spring.newsportal.dao;

import javax.persistence.*;

public abstract class AbstractDAO {

    @PersistenceContext(name="persistenceUnit")
    protected EntityManager em;

}
