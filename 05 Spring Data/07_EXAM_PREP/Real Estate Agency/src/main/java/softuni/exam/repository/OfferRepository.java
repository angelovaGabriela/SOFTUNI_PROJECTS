package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Offer;
import softuni.exam.models.entity.enums.ApartmentType;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {


    List<Offer> findOfferByApartmentApartmentTypeOrderByApartmentAreaDescPriceAsc(ApartmentType apartmentType);
}
