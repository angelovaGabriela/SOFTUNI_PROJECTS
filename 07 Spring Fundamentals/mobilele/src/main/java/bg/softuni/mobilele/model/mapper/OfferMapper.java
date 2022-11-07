package bg.softuni.mobilele.model.mapper;

import bg.softuni.mobilele.model.DTO.AddOfferDTO;
import bg.softuni.mobilele.model.OfferEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OfferMapper {

    OfferEntity addOfferDtoToOfferEntity(AddOfferDTO addOfferDTO);
}
