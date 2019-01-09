package webcrawler_project;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;



public class WebCrawlerSearch {
	
	HashSet<String> set =  new HashSet<String>();
	HashSet<String> images =  new HashSet<String>();
	HashSet<String> imports =  new HashSet<String>();
	public String find() {
		getURL("http://E:/pcf/LBG/Project/WebContent/index.html");
		System.out.println(set.size());
		Iterator it1 = set.iterator();
		while(it1.hasNext()) {
				System.out.println((String) it1.next());
		}
		Iterator it2 = set.iterator();
		while(it2.hasNext()) {
				System.out.println((String) it2.next());
		}
		Iterator it3 = set.iterator();
		while(it3.hasNext()) {
				System.out.println((String) it3.next());
		}
		return "check your console";
	}
	
	public void getURL(String a) {
		
		Document doc = null;
		try {
			System.out.println("a value"+a);
			doc = Jsoup.connect(a).get();
			org.jsoup.select.Elements links = doc.select("a[href]");
			org.jsoup.select.Elements src = doc.select("[src]");
			org.jsoup.select.Elements imports = doc.select("link[href]");
			for(Element e: links) {
				set.add(e.attr("abs:href"));
			}
			for(Element e: src) {
				set.add(e.attr("abs:src"));
			}
			for(Element e: imports) {
				set.add(e.attr("abs:href"));
			}
			Iterator it = set.iterator();
			while(it.hasNext()) {
				String link = (String) it.next();
				if(link != null && link.trim() != "") {
					getURL(link);
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

