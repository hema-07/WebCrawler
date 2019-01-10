
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class WebCrawlerSearch {
	
	HashSet<String> set =  new HashSet<String>();
	HashSet<String> result =  new HashSet<String>();
	
	public String find() {
		getURL("http://www.wipro.com");
		return "check your console";
	}
	
	public void getURL(String a) {
		Document doc = null;
		String link = null;
		try {
			System.out.println(a);
			doc = Jsoup.connect(a).get();
			org.jsoup.select.Elements links = doc.select("a");
			
			for(Element e: links) {
				
				if(e.attr("abs:href")!=null && e.attr("abs:href").trim()!="") {
					result.add(a);
					set.add(e.attr("abs:href"));
					set.remove(a);
				}
				
			}

			Iterator it = set.iterator();
			while(it.hasNext()) {
				
				link = (String) it.next();
				if(link != null && link.trim() != "" && !result.contains(link)) {
					getURL(link);
				}
				else {
					String skipLink = link;
					if(!(result).contains(skipLink)) {
						set.remove(link);
						getURL(skipLink);
					}
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		
		WebCrawlerSearch web = new WebCrawlerSearch();
		web.find();
		
	}


	
}

