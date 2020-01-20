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
import sabre.Trams.AppServer.Client.ResCardObject;
import sabre.Trams.AppServer.Client.Session;

public class AppServerDemo {
		
	public static void main(String[] args) 
			throws Exception
	{
		String username = "SYSDBA";
		String password = "masterkey";
		String URL = "http://localhost:8085";
//		String URL = "http://ltxw1359.sgdcelab.sabre.com/tramsappserverbeta/tramsappserverwebisapi.dll/";
        //const string alias = "automate_cbb_117";
		String alias = "automate_cbb_117_c";

		Session session = new Session(URL);
		session.login(username, password, alias, 0, "", "");
		System.out.println(String.format("Login Successful, Username = %s, Alias = %s, Session ID = %s", username, alias, session.getSessionID()));
		
//		InvoiceSearchObject invoiceSearch = new InvoiceSearchObject(session); 
//		JSONObject queryParams = new JSONObject();
//		queryParams.put( InvoiceSearchObject.Param_IssueDateFrom, LocalDate.of(2003, 1, 1));
//		queryParams.put(InvoiceSearchObject.Param_IssueDateTo, LocalDate.of(2003, 12, 31));
//		String[] cols = {
//				InvoiceSearchObject.Col_Invoice_InvoiceNo, 
//				InvoiceSearchObject.Col_Invoice_IssueDate
//		};
//		queryParams.put(InvoiceSearchObject.Param_IncludeCols, cols);
//		System.out.println("Running Invoice Query... ");
//		System.out.println(queryParams.toString());
//		invoiceSearch.search(queryParams);
//		
//		ArrayList<InvoiceSearchItem> invoiceSearchItems = invoiceSearch.getInvoiceSearchItems();
//		
//		System.out.println(
//				String.format(
//					"Invoice Search Successful, resultcount=%s", 
//					invoiceSearchItems.size()
//				)
//		);		

		InvoiceObject invoice = new InvoiceObject(session);
		
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
		
		invoice.load(4);
		for (InvoiceItem invoiceItem : invoice.getInvoiceItems())
		{
			System.out.println("invoiceNo = "+invoiceItem.getInvoiceNo());
			for (BookingItem bookingItem : invoiceItem.getBookingItems())
			{
				System.out.println("  bookingNo = "+bookingItem.getBookingNo());
			}
		}
		
//		newInvoice.getInvoiceItems().get(0).setInvoiceNumber(123456);
//		System.out.println(newInvoice.toJSONDeltaDataset(invoice));
		
		System.out.println("");
		System.out.println("Logging Out");
		session.logout();
		System.out.println("Logout Successful");
	}
}
