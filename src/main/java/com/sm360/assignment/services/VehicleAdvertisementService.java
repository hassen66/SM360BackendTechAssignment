package com.sm360.assignment.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sm360.assignment.exception.NotFoundException;
import com.sm360.assignment.model.Dealer;
import com.sm360.assignment.model.Listing;
import com.sm360.assignment.model.ListingState;
import com.sm360.assignment.repositories.DealerRepository;
import com.sm360.assignment.repositories.ListingRepository;
import com.sm360.assignment.requests.ListingCreateRequest;
import com.sm360.assignment.requests.ListingUpdateRequest;
import com.sm360.assignment.responses.ListingCreateResponse;
import com.sm360.assignment.responses.ListingGetResponse;
import com.sm360.assignment.responses.ListingUpdateResponse;

import jakarta.validation.Valid;

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
        var response = listingRepository.findAll().stream().filter(item-> dealerId.equals(item.getDealer().getId()) && state.equals(item.getState())).collect(Collectors.toList());
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
        System.out.println("listing");
        System.out.println(listing.getId());
        System.out.println(listingResponse.getId());
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

    /*public Optional<Category> getCategory(Long id) {
        return categoryRepository.findById(id);
    }

    public Category createCategory(Category category) {
        Slugify titleSeo = new Slugify();
        category.setTitleSeo(titleSeo.slugify(category.getTitle()));
        return categoryRepository.save(category);
    }

    public Optional<Category> updateCategory(Long id, UpdateCategoryRequest updateCategoryRequest) {
        return categoryRepository.findById(id).map(newCategory -> {
            if(updateCategoryRequest.getTitle() != null){
                newCategory.setTitle(updateCategoryRequest.getTitle());
                Slugify titleSeo = new Slugify();
                updateCategoryRequest.setTitleSeo(titleSeo.slugify(updateCategoryRequest.getTitle()));
            }
            if(updateCategoryRequest.getVisible() != null){
                newCategory.setVisible(updateCategoryRequest.getVisible());
            }
            if(updateCategoryRequest.getOrdre() != 0){
                newCategory.setOrdre(updateCategoryRequest.getOrdre());
            }
            if(updateCategoryRequest.getPosition() != null){
                newCategory.setPosition(updateCategoryRequest.getPosition());
            }
            System.out.println(newCategory.getPosition());
            return categoryRepository.save(newCategory);
        });
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }*/
}
