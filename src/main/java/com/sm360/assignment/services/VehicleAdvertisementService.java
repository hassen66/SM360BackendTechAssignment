package com.sm360.assignment.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sm360.assignment.exception.NotFoundException;
import com.sm360.assignment.exception.TierLimitException;
import com.sm360.assignment.model.Dealer;
import com.sm360.assignment.model.Listing;
import com.sm360.assignment.model.ListingState;
import com.sm360.assignment.repositories.DealerRepository;
import com.sm360.assignment.repositories.ListingRepository;
import com.sm360.assignment.requests.ListingCreateRequest;
import com.sm360.assignment.requests.ListingPublishRequest;
import com.sm360.assignment.requests.ListingUpdateRequest;
import com.sm360.assignment.responses.JsonResponse;
import com.sm360.assignment.responses.ListingCreateResponse;
import com.sm360.assignment.responses.ListingGetResponse;
import com.sm360.assignment.responses.ListingUpdateResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

@Service
public class VehicleAdvertisementService {

    private final ListingRepository listingRepository;

    private final DealerRepository dealerRepository;

	public ModelMapper modelMapper;

    @Autowired
    public VehicleAdvertisementService(ListingRepository listingRepository, DealerRepository dealerRepository, ModelMapper modelMapper) {
        this.listingRepository = listingRepository;
        this.dealerRepository = dealerRepository;
        this.modelMapper = modelMapper;
    }

    public List<ListingGetResponse> getListing(Long dealerId, ListingState state) {
        Dealer dealer = dealerRepository.findById(dealerId).orElse(null);
        var response = listingRepository.findByDealerAndState(dealer, state);
        return modelMapper.map(response, new TypeToken<List<ListingGetResponse>>() {}.getType());
    }

	public ListingCreateResponse createListing(ListingCreateRequest request) {
        Listing listing = modelMapper.map(request, Listing.class);
	    Dealer dealer = dealerRepository.findById(request.getDealerId()).orElse(null);
		if(dealer == null) {
			throw new NotFoundException(request.getDealerId());
		}
		listing.setCreatedAt(LocalDateTime.now());
		listing.setState(ListingState.draft);
		listing.setDealer(dealer);
		listingRepository.save(listing);
		
        ListingCreateResponse listingResponse = modelMapper.map(listing, ListingCreateResponse.class);
		return listingResponse;
	}

	public ListingUpdateResponse updateListing(Long listingId, ListingUpdateRequest request) {
		Listing listing = listingRepository.findById(listingId).orElse(null);
		if(listing == null) {
			throw new NotFoundException(listingId);
		}
		Dealer dealer = dealerRepository.findById(request.getDealerId()).orElse(null);
		if(dealer == null) {
			throw new NotFoundException(request.getDealerId());
		}
		listing.setUpdatedAt(LocalDateTime.now());
		listing.setState(ListingState.draft);
		listing.setDealer(dealer);
		listing.setVehicle(request.getVehicle());
		listing.setPrice(request.getPrice());
		listingRepository.save(listing);
        
		ListingUpdateResponse listingResponse = modelMapper.map(listing, ListingUpdateResponse.class);
		return listingResponse;
	}

	public Listing publishListing(Long id, ListingPublishRequest request) {
		Listing listing = listingRepository.findById(id).orElse(null);
		if(listing == null) {
			throw new NotFoundException(id);
		}
		if(listing.getDealer() == null) {
			throw new NotFoundException();
		}
		
		Dealer dealer = listing.getDealer();
		long total = listingRepository.countByDealerAndState(dealer,ListingState.published);
		
		if(total >= dealer.getTierLimit()) {
			if(request.isShowErrorLimitIsReached()) {
				throw new TierLimitException(dealer.getTierLimit());
			} else {
                Listing lastListing = listingRepository.findFirstByDealerAndStateAndPublishedAtIsNotNullOrderByPublishedAtDesc(listing.getDealer(),ListingState.published);
				lastListing.setState(ListingState.draft);
				listingRepository.save(lastListing);
			}
		}
		listing.setPublishedAt(LocalDateTime.now());
		listing.setState(ListingState.published);
		listingRepository.save(listing);

        return listing;

	}

	public Listing unpublishListing(Long id) {
		Listing listing = listingRepository.findById(id).orElse(null);;
		if(listing == null) {
			throw new NotFoundException(id);
		}
		listing.setPublishedAt(null);
		listing.setState(ListingState.draft);
		listingRepository.save(listing);

        return listing;
	}
}
