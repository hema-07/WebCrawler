package webcrawler_project;

import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class WebCrawlerSearch {
	
	HashSet<String> set =  new HashSet<String>();
	HashSet<String> result =  new HashSet<String>();
	HashSet<String> webImages =  new HashSet<String>();
	HashSet<String> webExternal =  new HashSet<String>();
	String url = "http://www.wipro.com"; 
	
	public String find() {
		getURL(url);
		//---------------------------print images ------------------------------
		System.out.println("Images from ->"+url);
		Iterator img = webImages.iterator();
		while(img.hasNext()) {
			System.out.println(img.next());
		}
		
		//---------------------------print external links------------------------------
		System.out.println("External links from ->"+url);
		Iterator ext = webExternal.iterator();
		while(ext.hasNext()) {
			System.out.println(ext.next());
		}
		System.out.println("completed");

		return "check your console";
	}
	
	public void getURL(String a) throws ConcurrentModificationException {
		Document doc = null;
		String link = null;
		try {
			
			doc = Jsoup.connect(a).get();
			org.jsoup.select.Elements links = doc.select("a[href]");
			org.jsoup.select.Elements images = doc.select("[src]");
			org.jsoup.select.Elements external = doc.select("link[href]");
			
			//------------------------links---------------------------------------------
			for(Element e: links) {
				if(e.attr("abs:href")!=null && e.attr("abs:href").trim()!="") {
					result.add(a);
					set.add(e.attr("abs:href"));
					set.remove(a);
				}
				
			}
			
			//------------------------images---------------------------------------------
			for (Element e : images) {
	            if (e.attr("abs:src")!=null && e.attr("abs:src").trim()!="") {
	            	webImages.add(e.attr("abs:src"));
	            }
	            	
			}
			
			//------------------------external links---------------------------------------------
			for (Element e : external) {
				if (e.attr("abs:href")!=null && e.attr("abs:href").trim()!="") {
					webExternal.add(e.attr("abs:href"));
	            }
				
			}

			//---------------------iterator for links----------------------------------------------
			Iterator it = set.iterator();
			while(it.hasNext()) {
				
				link = (String) it.next()+"";
				if(link != null && link.trim() != "" && !result.contains(link) && link.contains(".html")) {
					System.out.println(link);
					getURL(link);
				}
				else {
					String skipLink = link;
					if(!(result).contains(skipLink)) {
						set.remove(link);
						System.out.println(skipLink);
						getURL(skipLink);
					}
					
				}
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {		
		WebCrawlerSearch web = new WebCrawlerSearch();
		web.find();		
	}
}
