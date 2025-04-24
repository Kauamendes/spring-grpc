package br.com.kauamendes.springgrpc.repository;

import br.com.kauamendes.springgrpc.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
