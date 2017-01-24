package tutorial.sample.pmo;

import com.pathway.core.pmo.PathwayEmbeddedMessage;
import com.pathway.core.pmo.PathwayMessageField;
import com.pathway.core.pmo.PathwayMessageHandler;
import com.pathway.core.pmo.TopMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 
 * Header : 18
 * Dara    :  
 *   
 *  |<-----------------------Header-------------------------- -->| <------------------------------- Data ----------------------------------------------------------------------------------->|
 *  | STX(1) | LEN(4) | msgNo(4) | termType(3) | termTrNo(6)  |  filedTag1(1) |  termNumber(10) | fieldTag2(1) | authMomey(8) |  fieldTag3(1) | pointTrType(2) | EXT(1)   |
 *  ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 *  
 *  @author netstar
 *
 */

@XStreamAlias("Point PointRequestA")
public class PointRequestA extends TopMessage {
	
	@PathwayEmbeddedMessage
	public PointHeader header = new PointHeader();
	
	@PathwayMessageField(length=1,  defaultFillValue="0x1c", comment="필드구분자1")
	public byte[] fieldTag1;

	@PathwayMessageField(length=10,  pad="left", defaultFillValue="0", comment="단말기고유번호")
	public int termNumber;
	
	@PathwayMessageField(length=1,  defaultFillValue="0x1c", comment="필드구분자2")
	public byte[] fieldTag2;
	
	@PathwayMessageField(length=8,  pad="left", defaultFillValue="0", comment="요청금액")
	public int authMomey;

	
	@PathwayMessageField(length=1,  defaultFillValue="0x1c", comment="필드구분자3")
	public byte[] fieldTag3;

	@PathwayMessageField(length=2,  pad="left",  defaultFillValue="0", comment="포인트거래구분 -> 01:현금적립, 02:신용적립, 03:직불적립")
	public int pointTrType;

	@PathwayMessageField(length=1, defaultFillValue="0x03", comment="ETX-전문종료")
	public byte[] ETX;
	
    public PointRequestA() {
        this.setAcceptableMsg(new String[] { "P001", "P00X" }); // 본 클래스 PMO가 처리하는 전문번호를 입력합니다.
    }
}
