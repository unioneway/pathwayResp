package tutorial.sample.pbo;

import tutorial.sample.pmo.PointRequestC;

import com.pathway.booster.Pathway;
import com.pathway.core.pbo.AbstractPBO;
import com.pathway.core.pmo.PathwayMessage;
import com.pathway.core.pmo.PathwayMessageHandler;

public class PointPBO extends AbstractPBO {

	@PathwayMessageHandler(messageNo={"P001"})
	public void onRequestA(PathwayMessage pMessage) {

		try {
			Pathway.getAPI().pathReply(pMessage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		PointRequestC newReq = new PointRequestC();
//		newReq.header.setMsgNo("P003");
//		
//		
//		PathwayMessage request = Pathway.getAPI().makeRequest(pMessage, newReq );
//
//		try {
//			
//			PathwayMessage externRet = (PathwayMessage) Pathway.getAPI().ASyncCall("MM:M11", request, 1000);
//			
//
//			externRet.setSessionID(pMessage.getSessionID() );
//			externRet.setGcd(pMessage.getGcd() );
//			externRet.setPcd(pMessage.getPcd() );
//			externRet.setTraceNumber(pMessage.getTraceNumber() );
//
//			Pathway.getAPI().pathReply(externRet);
//		} catch (Exception e) {
//			System.out.println( " timeout 발생....");
//		}			
		
	}

/*	
	@PathwayMessageHandler(messageNo={"P100"})
	public void onRequestAB(PathwayMessage pMessage) {
		System.out.println("P100 수신" + pMessage.getTraceNumber());

		try {
			Pathway.getAPI().pathReply(pMessage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
*/	
	@PathwayMessageHandler(messageNo={"P002"})
	public void onRequestB(PathwayMessage pMessage) {
		try {
			Pathway.getAPI().pathReply(pMessage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@PathwayMessageHandler(messageNo={"P003"})
	public void onRequestC(PathwayMessage pMessage) {
		
		try {
			Thread.sleep(1000 * 10);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			Pathway.getAPI().pathReply(pMessage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void onStoreAndForward(PathwayMessage pMessage) {
		System.out.println( "on SAF ");
	} 

}
