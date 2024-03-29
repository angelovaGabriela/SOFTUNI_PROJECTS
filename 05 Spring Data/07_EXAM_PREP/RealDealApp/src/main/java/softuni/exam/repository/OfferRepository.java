package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entities.Offer;

import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    Optional<Offer> findByDescriptionAndAddedOn(String description, String addedOn);
}
