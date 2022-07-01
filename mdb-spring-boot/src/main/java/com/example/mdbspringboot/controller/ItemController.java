package com.example.mdbspringboot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.mdbspringboot.model.GroceryItem;
import com.example.mdbspringboot.repository.ItemRepository;

@RestController
public class ItemController {
    private Logger logger = LoggerFactory.getLogger(ItemRepository.class);

	@Autowired
	private ItemRepository itemRepository;

	@GetMapping(value = "/grocery")
	public List<GroceryItem> getAllMovies() {
		logger.info("Getting all groceries.");
		return itemRepository.findAll();
	}

    @GetMapping(value = "/id/{id}")
    public GroceryItem getGroceryByID(@PathVariable String id) {
        logger.info("Getting grocery with id: {}", id);
        return itemRepository.findItemByID(id);
    }

	@GetMapping(value = "/grocery/{itemName}")
	public GroceryItem getGroceryByName(@PathVariable String itemName) {
		logger.info("Getting grocery with name: {}", itemName);
		return itemRepository.findItemByName(itemName);
	}

	@PostMapping(value = "/grocery")
	public GroceryItem addMovie(@RequestBody GroceryItem groceryItem) {
		logger.info("Saving grocery item.");
		return itemRepository.save(groceryItem);
	}

	@PutMapping(value = "/grocery/{groceryID}")
	public GroceryItem updateGroceryItem(@PathVariable String groceryID, @RequestBody GroceryItem groceryItem) {
		logger.info("Updating grocery with ID: {}", groceryID);
		groceryItem.setId(groceryID);
		return itemRepository.save(groceryItem);
	}

	@DeleteMapping(value = "/grocery/{groceryID}")
	public void deleteGroceryItem(@PathVariable String groceryID) {
		logger.info("Deleting grocery with ID: {}", groceryID);
		itemRepository.deleteById(groceryID);
	}
}
