package com.moeb.proposalmanager.user.dabatase.repository;
// package com.moeb.proposal_manager.user.dabatase.repository;

// import java.util.Optional;
// import com.moeb.proposal_manager.user.entity.User;
// import jakarta.persistence.EntityManager;
// import jakarta.persistence.PersistenceContext;

// public class UserRepositoryImpl implements UserRepositoryCustom {
// @PersistenceContext
// private EntityManager entityManager;

// @Override
// public Optional<User> findByEmail(String email) {
// try {
// User user = entityManager.createQuery("SELECT u FROM User u WHERE u.email =
// :email", User.class)
// .setParameter("email", email)
// .getSingleResult();
// return Optional.of(user);
// } catch (Exception e) {
// return Optional.empty();
// }
// }
// }
