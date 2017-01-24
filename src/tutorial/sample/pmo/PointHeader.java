package tutorial.sample.pmo;

import com.pathway.core.pmo.HeaderTopMessage;
import com.pathway.core.pmo.PathwayMessageField;
import com.pathway.decoder.Acceptable;

/**
 * 포인트 예제에서 사용되는 공통헤더입니다.
 * @author Pathway Dev
 *
 *  Length : 18
 *  |---------------------------------------------------------------------
 *  | STX(1) | LEN(4) | msgNo(4) | termType(3) | termTrNo(6)  |
 *  ----------------------------------------------------------------------
 */
public class PointHeader extends HeaderTopMessage {
	
	@PathwayMessageField(length=1, defaultFillValue="0x02", comment="STX-전문시작")
	public byte[] STX;

	@PathwayMessageField(length=4, pad="left", defaultFillValue="0", comment="전문길이")
	public int msgLength;

	
	@PathwayMessageField(length=4,  pad="left", defaultFillValue="0", keyField = true, comment="거래구분코드(전문번호)")
	public String msgNo;

	@PathwayMessageField(length=3,  defaultFillValue=" ", keyField = true, comment="단말기유형")
	public String termType;

	@PathwayMessageField(length=6,  defaultFillValue=" ", keyField = true, comment="단말거래일련번호")
	public String termTrNo;

	public PointHeader() {
		
		acceptableMsg = new String[]{"P001", "P002", "P003", "P004"};
	}

	public String getMsgNo() {
		// TODO Auto-generated method stub
		return msgNo;
	}

	@Override
	public void setMsgNo(String msgNo) {
		this.msgNo = msgNo;
		
	}
	
//	public String[] allMessage = {"P001", "P002", "P003", "P004"};
	
//	@Override
//	public boolean acceptable() {
//		if( getMsgNo() == null ) return false;
//		
//		for(String msg: allMessage) {
//			if ( getMsgNo().equals(msg) )  return true;
//			
//		}
//		
//		return false;
//	}

}
