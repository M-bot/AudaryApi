package org.audary.api.translate;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.audary.api.azure.StringArrayResponse;
import org.audary.api.azure.StringResponse;

public class TranslateClientTest {
	static TranslateClient t;
	
	public static void mainEx2() throws Exception {
		for(StringResponse sr : t.retrieveLocales().string) {
			System.out.println(sr);
		}
		StringArrayResponse sar = new StringArrayResponse();
		sar.string = new StringResponse[]{new StringResponse("en")};
		for(StringResponse sr : t.retrieveLanguages(sar, "es").string) {
			System.out.println(sr);
		}
	}
	
	public static void mainEx1() throws Exception {
		String text = " can I take a quick look at the filter playground method where you been hiding your filters and hear you say I added the filter for months equals 6 which basically says give me the conference is in June and I deployed that to appspot I went to API to Explorer for my project actually out on apps for this is not in localhost chose my set my playground method executed it an error.  Lisa back and error we can do at this point is to go look at the end next year and admin console to get there you go to the console. Developers. Google.com you slap your project and then over here you choose cloud datastore and then you choose indexes you see me in Texas that have already been created and these are what we'd expect we already searched for his ancestor query which is basically give me all conferences for a user weather user was the ancestor of the conference and then we have this other one we saw at the city Topix and name of you never actually deployed your app with this filter to a spot you won't see this felt up but that's okay that's fine not what I want you to do is to go back to Eclipse I don't want you to look at the ultimately generated index configuration file so to get to that so I just a little hard to see but you go into the target directory you drill down into Conference - 1.0.  10 into web passionate ins then into a pension generated and you see datastore Dash indexes Dash Auto.  I talk to you what you see might be different to what I've got hair but it's perfectly fine if it's Bank it may already have something that's configurations in it now what I'm going to do is run conference Central on localhost.  can my dad POP servers not running so I'm going to go to the Abbeys Explorer on localhost make sure you're at in the Aggies Explorer on localhost not back in your iPhone app spot and gain we drilled out when we got off at the playground I said I got a response I don't actually have any items that match that query but this doesn't matter at this point so now I go back to Eclipse I'm going to look at my index configuration file again and you see that this index configurations and tree has been automatically added for me I didn't write this app engine rotate when it run the query on localhost so I'm searching for kind conference no ancestor I'm searching for the filtering by Citi Field from by month Hilton by topics filtering by name I turn off filtering by name I'm sorting by 9 tall intensive purposes of a sort order is a filter so now I'm going to go run my app again on hotspot.  we need to pull your application trap spot the updated index configuration file will be deployed with it and a pension will start to build when you index back in the index fuel page in the admin console from running on that spot I see that my new index.  is preparing I've actually run this query again I'll still got to naira I don't keep getting an error until it's finished preparing needs to be ready to serve before I can run the query.  did you get ready for the query I'm just going to go ahead and make sure I've got some conferences that will satisfy the query so I'm searching for conferences in London at Medical Innovations happening in June if you made any changes in your query properly to create a conference satisfies your query criteria.  hey that's going to see how index is doing see if it's bills yet.  okay good it has it's no longer preparing so it's ready to serve so over here in the ati's Explorer for my app on App spot I will run McCreery again execute it oh good and it found the conference I just created which match is all the filters on the query good one thing to mention is it when you deploy an app that needs a new index the request to update the index actually goes into a q along with other similar request so your index may not get updated immediately and that could be why it may take a little bit longer to prepare than you think it might need to it's not because it takes a long time to build the index and sometimes it can but generally the wait time is just waiting for your request to get to the top of the queue so another thing that you could do instead of running your query on localhost is it you can create your own index configuration called a the store Dash in Texas Star XML so whatever index is a defined.  the dinosaur Dash indexes file that you created or in the auto generated dinosaur tires in Texas - Auto we need to pull your app to app spot both those index configuration files will go up any index is defined in either of those files will be created and you'll be able to serve query that use the filters to find in those index configuration files.";
		Writer unicodeFileWriter = new OutputStreamWriter(
			    new FileOutputStream("out"), "UTF-8");
		unicodeFileWriter.write(t.translate(text, "ja").toString());
		unicodeFileWriter.close();
	}
	
	public static void main(String[] args) throws Exception {
		t = new TranslateClient(System.getenv("MICROSOFT_APPLICATION_CREDENTIALS"));
		//mainEx1();
		mainEx2();
	}
}
