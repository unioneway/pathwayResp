package tutorial.sample.pmo;

import java.net.URISyntaxException;

import org.junit.Test;

import com.google.common.net.HostAndPort;


import com.pathway.core.pmo.PathwayEmbeddedMessage;
import com.pathway.core.pmo.PathwayMessageField;
import com.pathway.core.pmo.PathwayMessageHandler;
import com.pathway.core.pmo.TopMessage;
import com.pathway.sdk.exception.ErrorCode;
import com.pathway.sdk.exception.PathwayConfigException;
import com.pathway.sdk.net.URI;
import com.pathway.sdk.utils.Constants;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 망취소내역이 있는 경우 사용
 * @author Administrator   
 *
 */

@XStreamAlias("Point PointRequestB")
public class PointRequestB extends TopMessage {
	
	@PathwayEmbeddedMessage
	public PointHeader header = new PointHeader();
	
	@PathwayMessageField(length=1, defaultFillValue="0x1c", comment="필드구분자1")
	public byte[] fieldTag1;

	@PathwayMessageField(length=10,  pad="left", defaultFillValue=" ", comment="단말기고유번호")
	public int termNumber;
	
	@PathwayMessageField(length=1, defaultFillValue="0x1c", comment="필드구분자2")
	public byte[] fieldTag2;
	
	@PathwayMessageField(length=8,  pad="right", defaultFillValue="0", comment="요청금액")
	public int authMomey;

	
	@PathwayMessageField(length=1, defaultFillValue="0x1c", comment="필드구분자3")
	public byte[] fieldTag3;

	@PathwayMessageField(length=2,  pad="left",  defaultFillValue="0", comment="포인트거래구분 -> 01:현금적립, 02:신용적립, 03:직불적립")
	public int pointTrType;
	
	@PathwayMessageField(depends="getDynamicMemberSize",  comment="이전 요청전문 - 가변길이")
	public byte[] dynamicMember;
	
	@PathwayMessageField(length=8,  comment="원거래일자-YYYYMMDD")
	public int orgTrDT;
	
	@PathwayMessageField(length=12,  comment="원거래승인번호")
	public String orgTrAuthNo;
	
	@PathwayMessageField(length=1, defaultFillValue="0x03", comment="ETX-전문종료")
	public byte[] ETX;
	
    public PointRequestB() {
        this.setAcceptableMsg(new String[] { "P002" }); // 본 클래스 PMO가 처리하는 전문번호를 입력합니다.
    }

	/**
	 * [목적]
	 *    1. 동적멤버의 길이 얻기
	 *    2. 최종 생성되는 PMO클래스 지정
	 * 
	 * [설명]
	 *    네트웨크로 부터 읽어야 할 bytes수를 사용자의 비지니스업무에 맞추어 구현합니다.
	 *    또한 최종 생성되는 pmo클레스가 어떤것인지도 setTargetPMO를 이용하여 지정해야 합니다.
	 *    미지정시 본클래스의 인스던스가 전달됩니다.
	 * 
	 * [참고]
	 *    동적멤버가 여러개 사용될 경우 각 동적 멤버 마다 depends를 지정 사용 될 수 있음
	 *    이떄 최종 생성되는 PMO클래스 지정은 사용자 비지니스 코드에 따라 특정 depends Method에서 지정합니다. 
	 * 
	 * [관련 제공 몌소드]
	 *   getByteBufferPosition 지정한 멤버이전까지의 총길이 정보를 번환
	 *   setTargetPMO 최종 목적 pmo class 명 지정
	 * 
	 * @return 동적 길이
	 * @throws Exception
	 */
	public int getDynamicMemberSize() throws Exception {
		// 사용자 전문길이 멤버값에 포함되지 않은 멤버들 -> STX(1) + 전문길이(4) --> 주의: 전문길이에 자신 포함 유무에 따라 코드 변경요.
		int notIncludeSize = 1 + 4;
		
		// 사용자 전문 전체 길이 획득(header.msgLength는 사용자 전문에 존제하는 길이값입니다)
		int userDefineTotalSize = header.msgLength + notIncludeSize; 
		
		// 동적Parsing 대상 : 전체에서 attatch멤버 전까지 길이
		int remainSize = userDefineTotalSize - this.getByteBufferPosition("dynamicMember");  // 주의 : getByteBufferPosition 파라메터값이 잘못 전달될 경우 전체 길이 반환됨.
		
		int dynaSize = remainSize - ( 8 + 12 + 1);

		System.err.println("remain: " + remainSize + "  attach : " + getByteBufferPosition("dynamicMember") + " user define len : " + userDefineTotalSize);
		return dynaSize;
	}

	/**
	 * JUnit로 제공되는 getByteBufferPosition를 확인
	 */
	@Test
	public void testPosition() {
		try {
			System.out.println(getByteBufferPosition("dynamicMember"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

//	public static void main(String[] args) throws PathwayConfigException  {
//		String endPoint = "Point_A#1://wininfo.co.kr:10001/config/bbb";
//		
//		URI ss;
//		try {
//			ss = new URI(endPoint);
//			System.out.println(ss.toString());
//			
//			 if( ss.getPath().split("/").length < 3) {
//				 
//			 } else {
//				 
//				String dir = ss.getPath().split("/")[1];
//				String fname = ss.getPath().split("/")[2]; 
//				String fullname = Constants.RUNTIME_ROOT + Constants.FILE_SEPARATOR + dir +  Constants.FILE_SEPARATOR + fname;
//				System.out.println(fullname);
//			 }
//		} catch (URISyntaxException e) {
//				throw new PathwayConfigException(endPoint + ":  " + ErrorCode.getErrorMessage("E0014")); 
//			}
//
//		
//	}
}
