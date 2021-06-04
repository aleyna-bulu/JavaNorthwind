package kodlamaio.northwind.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;

public interface ProductDao extends JpaRepository<Product, Integer> {
//verdiğim veri tipi için yani entity anotasyonyla süslenmiş nesne için(product)primary key alanını da ver ki ben sorguları intellisensi ona göre hazırliyim diyor (priamry key alanım integer olduğundan integer olarak veriyorym
	//şu an itibariyle dataAccesste CRUD operasyonalrı hazır

	Product getByProductName(String productName);
	//tabloya bakıp getBy görünce where koşulu yapıştırıyor
//findBy da olur
	Product getByProductNameAndCategory_CategoryId(String productName,int categoryId);
	//andli where şartı
	//benim category deme sebebim: ürünler category id üzerinden ilişkilendiriliyor yani defaultu category id (primary key üzrinden işlem yapıyor jpa r)
	List<Product> getByProductNameOrCategory_CategoryId(String productName,int categoryId);
	
	 List<Product> getByCategoryIn(List<Integer>categories);
	 //select*from products where category_id in(1,2,3,4)
	 
	 List<Product> getByProductNameContains(String productName);

	 List<Product> getByProductNameStartsWith(String productName);

	 //select*from products where category_id in(1,2,3,4)
	 
	 
	//JPQL
	 //bu sorguyu yazarken veritabanını unut entitye göre yazzzz
	 //farklı isim verseydim iki noktadan sonrakilere farklı isim verirdik
	 
	 @Query("From Product where productName=:productName and category.categoryId=:categoryId")
	 List<Product>getByNameAndCategory(String productName,int categoryId);
	 
	 
	 //select*from products where product_name=bişey and categoryId=bişey
	 
	 
	 //join atıyorum(categoriyle ilişkilendirilmiş productlarrdan çek
	  @Query("Select new kodlamaio.northwind.entities.dtos.ProductWithCategoryDto(p.id,p.productName,c.categoryName) From Category c Inner Join c.products p")
	 List <ProductWithCategoryDto>getProductWithCategoryDetails();
	 
	 
	 
	 
	 //select p.productId,p.productName,c.categoryName from Category c inner join Product p
	 //on c.categoryId=p.categoryId
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}

