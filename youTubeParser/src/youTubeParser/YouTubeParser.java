package youTubeParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;




public class YouTubeParser {
	
	public static List<String> parsedLinks = new ArrayList<>();
	
	public static void parse(int amount, int nestingLevel, String HTTPLink) {
		
		List<String> result = new ArrayList<>();
		Document doc = null;
		
		try {
			doc = Jsoup.connect(HTTPLink).get();
			Elements allElements = doc.select("div.watch-sidebar-body");
			
			for (Element block : allElements) {
				Elements linkBlocks = block.getElementsByTag("a");
				int countLinks = 1;
				for (Element linkBlock : linkBlocks) {
					if (countLinks < amount) {
						if (linkBlock.nextElementSibling() == null) {
							result.add("https://www.youtube.com" + linkBlock.attr("href"));	
							countLinks++;
						}
					}
				}
			}
			
			//System.out.println(nestingLevel + "||" + result);
			parsedLinks.addAll(result);
			
			if (nestingLevel > 1) {
				for (String link : result) {	
					//System.out.println("node link: " + link);
					parse(amount, nestingLevel-1, link);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	

}
