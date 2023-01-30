package stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Set;
import java.util.stream.Collectors;

public class JosePaumard {

	public static void main(String[] args) throws IOException {

		Path papersPath = Paths.get("file/papers.lst");

		Set<Article> articles = null;
		articles = Files
				.lines(papersPath)
				.map(Article::of)
				.collect(Collectors.toSet());

		System.out.println("# Articles => " + articles.size());

		/* */
		long count = articles
				.stream()
				.count();
		System.out.println("count => " + count);

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

		System.out.println("Year => " + year + " Stat => " + statis);

		Article articleWithMaxAuthor = articles
				.stream()
				.max(Comparator.comparing(a -> a.getAuthors().size()))
				.get();
		System.out.println("Article with max authors = " + articleWithMaxAuthor.getTitle() + " : size = "
				+ articleWithMaxAuthor.getAuthors().size());
	}

}
