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
 * ����ҳ����� �ִ� ��� ���
 * @author Administrator   
 *
 */

@XStreamAlias("Point PointRequestB")
public class PointRequestB extends TopMessage {
	
	@PathwayEmbeddedMessage
	public PointHeader header = new PointHeader();
	
	@PathwayMessageField(length=1, defaultFillValue="0x1c", comment="�ʵ屸����1")
	public byte[] fieldTag1;

	@PathwayMessageField(length=10,  pad="left", defaultFillValue=" ", comment="�ܸ��������ȣ")
	public int termNumber;
	
	@PathwayMessageField(length=1, defaultFillValue="0x1c", comment="�ʵ屸����2")
	public byte[] fieldTag2;
	
	@PathwayMessageField(length=8,  pad="right", defaultFillValue="0", comment="��û�ݾ�")
	public int authMomey;

	
	@PathwayMessageField(length=1, defaultFillValue="0x1c", comment="�ʵ屸����3")
	public byte[] fieldTag3;

	@PathwayMessageField(length=2,  pad="left",  defaultFillValue="0", comment="����Ʈ�ŷ����� -> 01:��������, 02:�ſ�����, 03:��������")
	public int pointTrType;
	
	@PathwayMessageField(depends="getDynamicMemberSize",  comment="���� ��û���� - ��������")
	public byte[] dynamicMember;
	
	@PathwayMessageField(length=8,  comment="���ŷ�����-YYYYMMDD")
	public int orgTrDT;
	
	@PathwayMessageField(length=12,  comment="���ŷ����ι�ȣ")
	public String orgTrAuthNo;
	
	@PathwayMessageField(length=1, defaultFillValue="0x03", comment="ETX-��������")
	public byte[] ETX;
	
    public PointRequestB() {
        this.setAcceptableMsg(new String[] { "P002" }); // �� Ŭ���� PMO�� ó���ϴ� ������ȣ�� �Է��մϴ�.
    }

	/**
	 * [����]
	 *    1. ��������� ���� ���
	 *    2. ���� �����Ǵ� PMOŬ���� ����
	 * 
	 * [����]
	 *    ��Ʈ��ũ�� ���� �о�� �� bytes���� ������� �����Ͻ������� ���߾� �����մϴ�.
	 *    ���� ���� �����Ǵ� pmoŬ������ ��������� setTargetPMO�� �̿��Ͽ� �����ؾ� �մϴ�.
	 *    �������� ��Ŭ������ �ν������� ���޵˴ϴ�.
	 * 
	 * [����]
	 *    ��������� ������ ���� ��� �� ���� ��� ���� depends�� ���� ��� �� �� ����
	 *    �̋� ���� �����Ǵ� PMOŬ���� ������ ����� �����Ͻ� �ڵ忡 ���� Ư�� depends Method���� �����մϴ�. 
	 * 
	 * [���� ���� ��ҵ�]
	 *   getByteBufferPosition ������ ������������� �ѱ��� ������ ��ȯ
	 *   setTargetPMO ���� ���� pmo class �� ����
	 * 
	 * @return ���� ����
	 * @throws Exception
	 */
	public int getDynamicMemberSize() throws Exception {
		// ����� �������� ������� ���Ե��� ���� ����� -> STX(1) + ��������(4) --> ����: �������̿� �ڽ� ���� ������ ���� �ڵ� �����.
		int notIncludeSize = 1 + 4;
		
		// ����� ���� ��ü ���� ȹ��(header.msgLength�� ����� ������ �����ϴ� ���̰��Դϴ�)
		int userDefineTotalSize = header.msgLength + notIncludeSize; 
		
		// ����Parsing ��� : ��ü���� attatch��� ������ ����
		int remainSize = userDefineTotalSize - this.getByteBufferPosition("dynamicMember");  // ���� : getByteBufferPosition �Ķ���Ͱ��� �߸� ���޵� ��� ��ü ���� ��ȯ��.
		
		int dynaSize = remainSize - ( 8 + 12 + 1);

		System.err.println("remain: " + remainSize + "  attach : " + getByteBufferPosition("dynamicMember") + " user define len : " + userDefineTotalSize);
		return dynaSize;
	}

	/**
	 * JUnit�� �����Ǵ� getByteBufferPosition�� Ȯ��
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
