package webcrawler_project;

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
	
	public String find() {
		String url = "http://www.wipro.com"; 
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
		
		return "check your console";
	}
	
	public void getURL(String a) {
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
			for (Element src : images) {
	            if (src.attr("abs:src")!=null && src.attr("abs:src").trim()!="") {
	            	webImages.add(src.attr("abs:src"));
	            }
	            	
			}
			
			//------------------------external links---------------------------------------------
			for (Element externalLink : external) {
				if (externalLink.attr("abs:href")!=null && externalLink.attr("abs:href").trim()!="") {
					webExternal.add(externalLink.attr("abs:href"));
	            }				
			}			
			set.removeAll(webImages);
			set.removeAll(webExternal);

			//---------------------iterator for links----------------------------------------------
			Iterator it = set.iterator();
			while(it.hasNext()) {
				
				link = (String) it.next();
				if(link != null && link.trim() != "" && !result.contains(link) && link.contains(".html")) {
					System.out.println("if :"+link);
					getURL(link);
				}
				else {
					String skipLink = link;
					if(!(result).contains(skipLink) && skipLink.contains(".html")) {
						set.remove(link);
						System.out.println("else :"+skipLink);
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
