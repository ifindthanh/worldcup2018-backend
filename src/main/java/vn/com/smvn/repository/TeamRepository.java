package vn.com.smvn.repository;

import org.springframework.data.repository.Repository;

import vn.com.smvn.entity.TeamEntity;

@org.springframework.stereotype.Repository
public interface TeamRepository extends Repository<TeamEntity, Long> {
    public void save(TeamEntity team);

    public TeamEntity findById(String id);
}
