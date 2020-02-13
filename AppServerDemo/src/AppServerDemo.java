import java.time.LocalDate;
import java.util.ArrayList;

import org.json.JSONObject;

import sabre.Trams.AppServer.Client.ActivityObject;
import sabre.Trams.AppServer.Client.BaseObject;
import sabre.Trams.AppServer.Client.BookingItem;
import sabre.Trams.AppServer.Client.InvoiceItem;
import sabre.Trams.AppServer.Client.InvoiceObject;
import sabre.Trams.AppServer.Client.InvoiceSearchItem;
import sabre.Trams.AppServer.Client.InvoiceSearchObject;
import sabre.Trams.AppServer.Client.ProfileObject;
import sabre.Trams.AppServer.Client.ResCardItem;
import sabre.Trams.AppServer.Client.ResCardObject;
import sabre.Trams.AppServer.Client.ResvtnItem;
import sabre.Trams.AppServer.Client.ResvtnObject;
import sabre.Trams.AppServer.Client.Session.Session;

public class AppServerDemo {
		
	public static void main(String[] args) 
			throws Exception
	{
		String username = "SYSDBA";
		String password = "masterkey";
		String URL = "http://localhost:8085";
//		String URL = "http://ltxw1359.sgdcelab.sabre.com/tramsappserverbeta/tramsappserverwebisapi.dll/";
		String alias = "automate_cbb_117";
		String TBO_Access_Key = "000010-199000-02FF008";

		System.out.println(String.format("Connecting to server %s ...", URL));
		Session session = new Session(URL);
		System.out.println(String.format("Logging in, alias = %s, username = %s ...", alias, username));
		session.login(username, password, alias, 1, TBO_Access_Key, "");
		System.out.println(String.format("Login Successful, Session ID = %s", session.getSessionID()));
		
		InvoiceSearchObject invoiceSearch = new InvoiceSearchObject(session); 
		JSONObject queryParams = new JSONObject();
		queryParams.put( InvoiceSearchObject.Param_IssueDateFrom, LocalDate.of(2003, 1, 1));
		queryParams.put(InvoiceSearchObject.Param_IssueDateTo, LocalDate.of(2003, 12, 31));
		String[] cols = {
				InvoiceSearchObject.Col_Invoice_InvoiceNo, 
				InvoiceSearchObject.Col_Invoice_IssueDate
		};
		queryParams.put(InvoiceSearchObject.Param_IncludeCols, cols);
		System.out.println("Running Invoice Query... ");
		System.out.println(queryParams.toString());
		invoiceSearch.search(queryParams);
		
		ArrayList<InvoiceSearchItem> invoiceSearchItems = invoiceSearch.getInvoiceSearchItems();
		
		System.out.println(
				String.format(
					"Invoice Search Successful, resultcount=%s", 
					invoiceSearchItems.size()
				)
		);		

//		InvoiceObject invoice = new InvoiceObject(session);
		
//		for (InvoiceSearchItem invoiceSearchItem : invoiceSearchItems)
//		{
//			System.out.println("invoiceNo = "+invoiceSearchItem.getInvoiceNo());			
//			invoice.load(invoiceSearchItem.getInvoiceNo());
//			for (InvoiceItem invoiceItem : invoice.getInvoiceItems())
//			{
//				System.out.println("invoiceNo = "+invoiceItem.getInvoiceNo());
//				for (BookingItem bookingItem : invoiceItem.getBookingItems())
//				{
//					System.out.println("  bookingNo = "+bookingItem.getBookingNo());
//				}
//			}
//		}
		
//		invoice.load(4);
//		InvoiceObject newInvoice = new InvoiceObject(session);
//		newInvoice.load(4);
		
//		for (InvoiceItem invoiceItem : invoice.getInvoiceItems())
//		{
//			System.out.println("invoiceNo = "+invoiceItem.getInvoiceNo());
//			for (BookingItem bookingItem : invoiceItem.getBookingItems())
//			{
//				System.out.println("  bookingNo = "+bookingItem.getBookingNo());
//			}
//		}
		
//		newInvoice.getInvoiceItems().get(0).setInvoiceNumber(123456);
//		System.out.println(newInvoice.toJSONDeltaDataset(invoice));
		
//		BaseObject.generateJavaClass(invoice.getClassName(), "BaseUpdateObject", invoice.getMetadata());
//		ProfileObject profile = new ProfileObject(session);
//		BaseObject.generateJavaClass(profile.getClassName(), "BaseUpdateObject", profile.getMetadata());
//		ActivityObject activity = new ActivityObject(session);
//		BaseObject.generateJavaClass(activity.getClassName(), "BaseUpdateObject", activity.getMetadata());
//		ResCardObject resCard = new ResCardObject(session);
//		BaseObject.generateJavaClass(resCard.getClassName(), "BaseUpdateObject", resCard.getMetadata());
		
//		System.out.println(invoice.toJSONDataset(invoice.getMetadata()));

		ResCardObject resCard = new ResCardObject(session);
		resCard.load(10);
//		ResCardItem resCardItem = resCard.addItem(); // add a new rescard
//		resCardItem.setCreateDate(LocalDate.now());
//		resCardItem.setProfile_LinkNo(4);
		ResCardItem resCardItem = resCard.getItem(0);
		ResvtnObject resvtn = resCardItem.getResvtnObject();
//		ResvtnItem resvtnItem = resvtn.addItem(); // add a new reservation
		ResvtnItem resvtnItem = resvtn.getItem(0); 
		resvtnItem.setVendor_LinkNo(66);
		resvtnItem.setTravelCategory_LinkNo(1);
//		resvtn.removeItem(resvtnItem);
		resCard.Save();
		
		System.out.println("");
		System.out.println("Logging Out");
		session.logout();
		System.out.println("Logout Successful");
	}
}
