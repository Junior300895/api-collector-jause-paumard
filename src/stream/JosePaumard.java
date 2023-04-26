package stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.Map.Entry;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class JosePaumard {

	public static void main(String[] args) throws IOException {

		Path papersPath = Paths.get("file/papers.lst");

		Set<Article> articles = null;
		articles = Files
				.lines(papersPath)
				.map(Article::of)
				.collect(Collectors.toSet());

		System.out.println("#Nbre Articles => " + articles.size());

		/* */
		long count = articles
				.stream()
				.count();
		System.out.println("\ncount => " + count);

		/* */
		int year = 0;
		// year = articles.stream().map(a -> a.getInceptionYear()).filter(y -> y >
		// 1900).min(Comparator.naturalOrder())
		// .get();
		year = articles
				.stream()
				.mapToInt(Article::getInceptionYear)
				.filter(y -> y > 1900).min().getAsInt();

		IntSummaryStatistics statis = articles.stream()
				// .mapToInt(Article::getInceptionYear)
				// .summaryStatistics().
				/* Ou bien */ .collect(Collectors.summarizingInt(Article::getInceptionYear));

		System.out.println("\nYear => " + year + " Stat => " + statis);

		Article articleWithMaxAuthor = articles
				.stream()
				.max(Comparator.comparing(a -> a.getAuthors().size()))
				.get();
		System.out.println("\nArticle with max authors = " + articleWithMaxAuthor.getTitle() + " *** : size = "
				+ articleWithMaxAuthor.getAuthors().size());

		String authorsName = articleWithMaxAuthor.getAuthors().stream()
				.map(aut -> aut.getLastName())
				.collect(Collectors.joining(", "));
		System.out.println("\nAuthorsNames = " + authorsName);

		// Articles par année
		Map<Integer, Long> map = articles.stream().collect(
				countByKey(Article::getInceptionYear));
		System.out.println("\nArtcicles par année = " + map);

		// Année avec le maximum d'article
		Entry<Integer, Long> map1 = articles.stream().collect(
				countByKey(Article::getInceptionYear)).entrySet().stream()
				.max(Comparator.comparing(ent -> ent.getValue())).get();
		System.out.println("\nAnnée avec le maximum d'article = " + map1);

		// Auteur avec le maximum d'article
		Entry<Author, Long> map2 = articles.stream().flatMap(art -> art.getAuthors().stream()).collect(
				countByKey(Function.identity()))
				.entrySet().stream()
				.max(Comparator.comparing(ent -> ent.getValue())).get();
		System.out.println("\nAuteur avec le maximum d'article = " + map2);

		// Article le plus apparu
		Entry<String, Long> m =  articles.stream().collect(
			Collectors.groupingBy(art -> art.getTitle(),
			Collectors.counting()
		)).entrySet().stream().max(Comparator.comparing(ent -> ent.getValue())).get();
		System.out.println("\nArticle le plus apparu : " + m);

		//
	}

	private static <T, K> Collector<T, ?, Map<K, Long>> countByKey(Function<T, K> keyExtractor) {
		return Collectors.groupingBy(keyExtractor, Collectors.counting());
	}


}