package nhom7.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nhom7.model.Category;
import nhom7.model.Restaurant;
import nhom7.repository.CategoryRepository;

@Service
public class CategoryServiceImp implements CategoryService {

	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category createCategory(String name, Long userId) throws Exception {
		Restaurant restaurant = restaurantService.getRestaurantByUserId(userId);
		Category category = new Category();
		category.setName(name);
		category.setRestaurant(restaurant);
		
		return categoryRepository.save(category);
	}

	@Override
	public List<Category> findCategoryByRestaurantId(Long id) throws Exception {
		Restaurant restaurant = restaurantService.findRestaurantById(id);
		return categoryRepository.findByRestaurantId(id);
	}

	@Override
	public Category findCategoryById(Long id) throws Exception {
		Optional<Category> optionalCategory = categoryRepository.findById(id);

		if (optionalCategory.isEmpty()) {
			throw new Exception("Không tìm thấy danh mục");
		}
		return optionalCategory.get();
	}
}
