package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Test {

	static int age;

	public static void main(String[] args) {

		Consumer<String> afficher = (String param) -> System.out.println(param);
		afficher.accept("aza");

		BiFunction<Integer, Integer, Long> additionner = (val1, val2) -> (long) val1 + val2;
		System.out.println("add avec interface fonctionnelle => " + additionner.apply(2, 5));

		Comparator<String> comparator = (String chaine1, String chaine2) -> Integer.compare(chaine1.length(),
				chaine2.length());
		System.out.println("comparator => " + comparator.compare("aV", "cc"));

		var i = 0;
		System.out.println("inférence de type i => " + i);

//		List<Integer> list = List.of(1, 2); // immuable (qui ne change pas d'état après création
//		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5); // immuable 
		List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4)); // non immuable (modifiable)
//		List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4)); // non immuable (modifiable)

		list.add(5);
		list.stream().forEach(d -> System.out.println(d));

		/**  */
		List<Integer> numbers = Arrays.asList(1, 2, 3);
		numbers.stream().mapToInt(Integer::intValue).forEach(System.out::println);

		/** AnyMatch */
		Stream.of("d2", "a2", "b1", "b3", "c").map(s -> {
			System.out.println("map: " + s);
			return s.toUpperCase();
		}).anyMatch(s -> {
			System.out.println("anyMatch: " + s);
			return s.startsWith("A");
		});

		/** Methode Sum */
		List<Employe> employes = new ArrayList<>(6);
		employes.add(new Employe("e1", 176, 1500));
		employes.add(new Employe("e2", 190, 2700));
		employes.add(new Employe("e3", 172, 1850));
		employes.add(new Employe("e4", 162, 3300));
		employes.add(new Employe("e5", 176, 1280));
		employes.add(new Employe("e6", 168, 2850));

		Double total = employes.stream().mapToDouble(e -> e.getSalaire()).sum();
		System.out.println("\ntotal salaire = " + total + "\n");

		/** Method FindFirst */
		Optional<Employe> uneGrandePersonne = employes.stream().filter(p -> p.getTaille() >= 100).findFirst();

		if (uneGrandePersonne.isPresent()) {
			System.out.println("Grande personne trouvee : " + uneGrandePersonne);
		} else {
			System.out.println("Aucune grande personne trouvee");
		}
		/** TEST 3 */
//		int[] tab = {3, 4, 5};
//		int[] tab = {-12, -10, -3};
		// int[] tab = {-12, -10, 3, 4};
//		int[] tab = {-12, -12};
		int[] tab = {-12, 12};
		
		System.out.println("\nValeur plus proche de 0 : " + value(tab));
		
		
	}

	
	
	
	public static int value(int[] ts) {
		if (ts.length == 0) {
			return 0;
		}

		int minValuePositif = Arrays.stream(ts).filter(t -> t > 0).min().orElse(0);

		int minValueNegatif = Arrays.stream(ts).filter(t -> t < 0).max().orElse(0);

		if (minValuePositif == 0) {
			minValuePositif = minValueNegatif;
		} else if (minValueNegatif == 0) {
			minValueNegatif = minValuePositif;
		}

		return minValuePositif <= Math.abs(minValueNegatif) ? minValuePositif : minValueNegatif;

	}
}
