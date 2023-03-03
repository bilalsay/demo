package com.example.demo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.example.demo.model.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		var images1 = Arrays.asList(new ProductImages("image1", "Path1"), new ProductImages("image2", "Path2"));
		var images2 = Arrays.asList(new ProductImages("image1", "Path1"), new ProductImages("image2", "Path2"));
		var images3 = Arrays.asList(new ProductImages("image1", "Path1"), new ProductImages("image2", "Path2"));
		var images4 = Arrays.asList(new ProductImages("image1", "Path1"), new ProductImages("image2", "Path2"));
		var images5 = Arrays.asList(new ProductImages("image1", "Path1"), new ProductImages("image2", "Path2"));
		var images6 = Arrays.asList(new ProductImages("image1", "Path1"), new ProductImages("image2", "Path2"));
		var images7 = Arrays.asList(new ProductImages("image1", "Path1"), new ProductImages("image2", "Path2"));

		var product1 = new Product("Vehicle", new BigDecimal(12324), ProductType.Main, images1);
		var product2 = new Product("Sun Roof Package", new BigDecimal(34), ProductType.Sub, images2);
		var product3 = new Product("Sound System Pack 1 ", new BigDecimal(34), ProductType.Sub, images3);
		var product4 = new Product("Sound system Pack 2", new BigDecimal(34), ProductType.Sub, images4);
		var product5 = new Product("17' Aluminium Alloy Wheel", new BigDecimal(34), ProductType.Sub, images5);
		var product6 = new Product("18' Aluminium Alloy Wheel", new BigDecimal(34), ProductType.Sub, images6);
		var product7 = new Product("Electrical / Heated Front Row Seat", new BigDecimal(34), ProductType.Sub, images7);

		var productList1 = Arrays.asList(product1, product2, product3, product4, product5, product6, product7);
		var productList2 = Arrays.asList(product1, product2, product3, product4, product5, product6, product7);
		var customer1 = new Customer("Bilal", "05397844251", "bilalsay60@mail.com");
		var customer2 = new Customer("Bilal", "05397844251", "bilalsay60@mail.com");
		var offer1 = new Offer(customer1, productList1, LocalDateTime.now());
		var offer2 = new Offer(customer2, productList2, LocalDateTime.now());

		var offerList = Arrays.asList(offer1, offer2);

		// Use Case 1
		offerList.stream().flatMap(offer -> offer.getProducts().stream()).forEach(product -> {
			System.out.println("ProductName: " + product.getName());
			System.out.println("ProductPrice: " + product.getPrice());
			System.out.println("ProductType: " + product.getProductType().getValue());

			product.getImages().forEach(image -> {
				System.out.println("ImageName: " + image.getName());
			});
		});

		// Use Case 2
		var selectedCustomer = customer2;
		offerList.stream()
				.filter(offer -> offer.getCustomer().equals(selectedCustomer))
				.flatMap(offer -> offer.getProducts().stream())
				.forEach(product -> {
					System.out.println("ProductName: " + product.getName());
					System.out.println("ProductPrice: " + product.getPrice());
					System.out.println("ProductType: " + product.getProductType().getValue());

					product.getImages().forEach(image -> {
						System.out.println("ImageName: " + image.getName());
					});
				});
		// Use Case 3

		Map<Product, Long> groupingMap = offerList.stream().flatMap(offer -> offer.getProducts().stream())
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		Comparator<Map.Entry<Product, Long>> comparator =
				(Map.Entry<Product, Long> l1, Map.Entry<Product, Long> l2) -> Long.compare(l1.getValue(), l2.getValue());

		groupingMap.entrySet().stream()
				.sorted(comparator)
				.limit(5)
				.map(entry -> entry.getKey())
				.forEach(product -> {
					System.out.println("ProductName: " + product.getName());
					System.out.println("ProductPrice: " + product.getPrice());
					System.out.println("ProductType: " + product.getProductType().getValue());

					product.getImages().forEach(image -> {
						System.out.println("ImageName: " + image.getName());
					});
				});

	}

	@Override
	public void run(String... args) throws Exception {
		var text = "sharpener";
		try (var scanner = new Scanner(System.in)) {
			var rightCount = 0;
			var isSuccess = false;

			while (rightCount < 20) {
				var correctCount = 0;
				var wrongCount = 0;
				var correctStrBuilder = new StringBuilder();
				var wrongStrBuilder = new StringBuilder();
				var str = scanner.nextLine();

				for (int i = 0; i < str.length(); i++) {
					var s = str.substring(i, i + 1);
					for (int j = i; j < text.length(); j++) {
						var t = text.substring(j, j + 1);
						if (s.equals(t)) {
							correctCount++;
							correctStrBuilder.append(s);
							break;
						}

						if (!s.equals(t)) {
							if (text.indexOf(s, j) != -1) {
								wrongCount++;
								wrongStrBuilder.append(s);
								break;
							}
						}
					}
				}

				rightCount++;

				if (correctCount == text.length()) {
					isSuccess = true;
					break;
				}

				if (rightCount != 20) {
					var correctMessage = (correctCount != 0 ? correctCount + "/" + correctStrBuilder.toString() + "/"
							: "0");
					var wrongMessage = (wrongCount != 0 ? wrongCount + "/" + wrongStrBuilder.toString() + "/" : "0");
					System.out.println("guest word \"" + str + "\"");
					System.out.println("correctly guessed letters = " + correctCount);
					System.out.println("letter in the right place = " + correctMessage);
					System.out.println("letters that were guessed correctly but in the wrong place = " + wrongMessage);
				}
			}

			var message = "Congratulations, you know the word \"" + text + "\"!";

			if (!isSuccess) {
				message = "Sorry. You did not succeed. May the force be with you!";
			}

			System.out.println(message);
		}
	}

}
