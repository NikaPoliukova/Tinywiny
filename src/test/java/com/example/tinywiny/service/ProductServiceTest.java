package com.example.tinywiny.service;

import com.example.tinywiny.dto.ProductDto;
import com.example.tinywiny.dto.TypeProduct;
import com.example.tinywiny.model.DeliveryInformation;
import com.example.tinywiny.model.DeliveryType;
import com.example.tinywiny.model.Order;
import com.example.tinywiny.model.Product;
import com.example.tinywiny.model.ProductInBucket;
import com.example.tinywiny.model.ProductInOrder;
import com.example.tinywiny.model.User;
import com.example.tinywiny.repository.ImageRepository;
import com.example.tinywiny.repository.ProductInBucketRepository;
import com.example.tinywiny.repository.ProductInOrderRepository;
import com.example.tinywiny.repository.ProductRepository;
import com.example.tinywiny.service.amazonService.AwsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
  @Mock
  private ProductRepository productRepository;
  @Mock
  private ProductInBucketRepository productInBucketRepository;
  @Mock
  private ProductInOrderRepository productInOrderRepository;
  @Mock
  private TypeProductService typeProductService;
  @Mock
  private ImageRepository imageRepository;
  @Mock
  private AwsService storageService;
  @InjectMocks
  private ProductService productService;

  @Test
  void save() {
    when(productRepository.save(prepareProduct())).thenReturn(prepareProduct());
    Product actual = productService.save(prepareProduct());
    assertEquals(prepareProduct(), actual);
  }

  @Test
  void updateProduct() {
    ProductDto productDto = new ProductDto(1L, "item", 125, "cool", 2, 1);
    when(productRepository.findProductByProductId(any(Long.class))).thenReturn(Optional.of(prepareProduct()));
    when(typeProductService.getType(any(Integer.class))).thenReturn(new TypeProduct(1, "name", new ArrayList<Product>()));
    when(productRepository.save(any(Product.class))).thenReturn(prepareProduct());
    productService.updateProduct(productDto, 1L);
  }

  @Test
  void findAllProductsByTypeAndPage() {
    when(typeProductService.getType(any(String.class))).thenReturn(new TypeProduct(1, "name", List.of(prepareProduct())));
    Page<Product> products = mock(Page.class);
    Mockito.when(productRepository.findAllByTypeProduct(any(TypeProduct.class), any(Pageable.class))).thenReturn(products);
    Page<Product> actual = productService.findAllProductsByTypeAndPage("toys", 1, 20);
    assertEquals(products, actual);

  }

  @Test
  void findProductByProductId() {
    when(productRepository.findProductByProductId(any(Long.class))).thenReturn(Optional.of(prepareProduct()));
    Product actual = productService.findProductByProductId(1L);
    assertEquals(prepareProduct(), actual);
  }

  @Test
  void findProductByProductName() {
    when(productRepository.findProductByProductName(any(String.class))).thenReturn(Optional.of(prepareProduct()));
    Product actual = productService.findProductByProductName("item");
    assertEquals(prepareProduct(), actual);
  }

  @Test
  void deleteProduct() {
    when(productInBucketRepository.findProductInBucketById(any(Long.class))).thenReturn(null);
    when(productInOrderRepository.findProductInOrderByProduct(any(Product.class))).thenReturn(null);
    when(productRepository.findProductByProductId(any(Long.class))).thenReturn(Optional.of(prepareProduct()));
    doNothing().when(productRepository).deleteProductByProductId(any(Long.class));
    productService.deleteProduct(1L);
    verify(productRepository).deleteProductByProductId(any(Long.class));
  }

  @Test
  void findAllProducts() {
    when(productRepository.findAll()).thenReturn(List.of(prepareProduct()));
    List<Product> actual = productService.findAllProducts();
    assertEquals(List.of(prepareProduct()), actual);
  }

  @Test
  void updateCountProductInStock() {
    when(productRepository.findProductByProductId(any(Long.class))).thenReturn(Optional.of(prepareProduct()));
    when(productRepository.save(any(Product.class))).thenReturn(prepareProduct());
    productService.updateCountProductInStock(prepareProductInOrder());
    verify(productRepository).save(any(Product.class));

}

  public Product prepareProduct() {
    return new Product(2L, "item", 12, "cool", 1, new TypeProduct(1, "name",
        new ArrayList<Product>()), new ArrayList<ProductInBucket>(), List.of(prepareProductInOrder()));
  }


  public ProductInOrder prepareProductInOrder() {
    return new ProductInOrder(1L, 1, new Order(1L, 125, "dsxjbdc", new Date(),
        "5488415", "2566785121", new User(),   new DeliveryType(), new DeliveryInformation(),
        new ArrayList<ProductInOrder>()),
        new Product(2L, "item", 12, "cool", 1, new TypeProduct(1,
            "name", new ArrayList<Product>()), new ArrayList<ProductInBucket>(), List.of(new ProductInOrder())));
  }

}