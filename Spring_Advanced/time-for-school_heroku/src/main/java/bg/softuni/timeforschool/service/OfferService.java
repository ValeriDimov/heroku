package bg.softuni.timeforschool.service;

import bg.softuni.timeforschool.model.dto.CreateOrUpdateOfferDTO;
import bg.softuni.timeforschool.model.dto.OfferDetailDTO;
import bg.softuni.timeforschool.model.dto.SearchOfferDTO;
import bg.softuni.timeforschool.model.entity.DeletedOfferEntity;
import bg.softuni.timeforschool.model.entity.OfferEntity;
import bg.softuni.timeforschool.model.entity.UserEntity;
import bg.softuni.timeforschool.model.enums.UserRoleEnum;
import bg.softuni.timeforschool.repository.DeletedOfferRepository;
import bg.softuni.timeforschool.repository.OfferRepository;
import bg.softuni.timeforschool.repository.OfferSpecification;
import bg.softuni.timeforschool.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final DeletedOfferRepository deletedOfferRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public OfferService(OfferRepository offerRepository,
                        DeletedOfferRepository deletedOfferRepository, UserRepository userRepository,
                        ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.deletedOfferRepository = deletedOfferRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    public boolean isOwner(String userName, Long offerId) {

        boolean isOwner = offerRepository.
                findById(offerId).
                filter(o -> o.getSeller().getEmail().equals(userName)).
                isPresent();

        if (isOwner) {
            return true;
        }
        return userRepository.
                findByEmail(userName).
                filter(this::isAdmin).
                isPresent();
    }

    private boolean isAdmin(UserEntity user) {
        return user.getUserRoles().
                stream().
                anyMatch(r -> r.getUserRole() == UserRoleEnum.ADMIN);
    }

    public void deleteOfferById(Long offerId) {
        offerRepository.deleteById(offerId);
    }

    public Page<OfferDetailDTO> getAllOffers(Pageable pageable) {
        return offerRepository.
                findAll(pageable).
                map(offer -> modelMapper.map(offer, OfferDetailDTO.class));

    }

    public Optional<CreateOrUpdateOfferDTO> getOfferEditDetails(Long offerID) {
        return offerRepository.
                findById(offerID).
                map(offer -> modelMapper.map(offer, CreateOrUpdateOfferDTO.class));
    }

    public Optional<OfferDetailDTO> findOfferByID(Long offerID) {
        return offerRepository.
                findById(offerID).
                map(offer -> modelMapper.map(offer, OfferDetailDTO.class));
    }

    public void addOffer(CreateOrUpdateOfferDTO addOfferDTO, UserDetails userDetails) {
        OfferEntity newOffer = modelMapper.map(addOfferDTO, OfferEntity.class);

        UserEntity seller = userRepository.findByEmail(userDetails.getUsername()).
                orElseThrow();

        newOffer.setSeller(seller);

        offerRepository.save(newOffer);
    }

    public void editOffer(CreateOrUpdateOfferDTO editOfferDTO, UserDetails userDetails) {
        OfferEntity newOffer = modelMapper.map(editOfferDTO, OfferEntity.class);

        UserEntity seller = userRepository.findByEmail(userDetails.getUsername()).
                orElseThrow();

        newOffer.setSeller(seller);

        offerRepository.save(newOffer);
    }


    public List<OfferDetailDTO> searchOffer(SearchOfferDTO searchOfferDTO) {
        List<OfferDetailDTO> offerDetailDTOS = this.offerRepository.findAll(new OfferSpecification(searchOfferDTO)).
                stream().map(offer -> modelMapper.map(offer, OfferDetailDTO.class)).
                toList();

        return offerDetailDTOS;
    }

    @Scheduled(cron = "0 */2 * * * ?")
    public void ScheduledOfferDeactivation() {
        List<OfferEntity> allOffers = offerRepository.findAll();

        for (OfferEntity offer : allOffers) {
            if (offer.getExpiryDate().isBefore(LocalDate.now())) {

                offer.setForDeletion(true);
                offerRepository.save(offer);

                DeletedOfferEntity deletedOfferEntity = new DeletedOfferEntity().
                        setContact(offer.getContact()).
                        setCourse(offer.getCourse()).
                        setDescription(offer.getDescription()).
                        setExpiryDate(offer.getExpiryDate());

                deletedOfferRepository.save(deletedOfferEntity);
            }
        }
    }


    @Scheduled(cron = "0 */3 * * * ?")
    public void ScheduledOfferDeletion() {
        List<OfferEntity> allOffers = offerRepository.findAll();

        for (OfferEntity offer : allOffers) {
            if (offer.isForDeletion()) {

               offerRepository.delete(offer);
            }
        }
    }

}
