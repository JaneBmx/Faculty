package com.vlasova.repository;

import com.vlasova.entity.faculity.Faculity;
import com.vlasova.specification.FaculitySpecification;

import java.util.Set;

public interface FaculityRepository extends Repository {
    void add(Faculity user);

    void remove(int id);

    void update(Faculity user);

    Set<Faculity> query(FaculitySpecification specification);
}
