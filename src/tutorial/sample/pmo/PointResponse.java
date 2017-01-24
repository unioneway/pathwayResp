package tutorial.sample.pmo;

import com.pathway.core.pmo.PathwayEmbeddedMessage;
import com.pathway.core.pmo.PathwayMessageField;
import com.pathway.core.pmo.TopMessage;

public class PointResponse extends TopMessage {
	
	@PathwayEmbeddedMessage
	public PointHeader header = new PointHeader();
	
	@PathwayMessageField(length=1,  comment="필드구분자1")
	public byte[] fieldTag1;

	@PathwayMessageField(length=10,  pad="left", defaultFillValue=" ", comment="단말기고유번호")
	public int termNumber;
	
	@PathwayMessageField(length=1,  comment="필드구분자2")
	public byte[] fieldTag2;
	
	@PathwayMessageField(length=2,  pad="left", defaultFillValue=" ", comment="응답코드-> 00: 정상, 01:거절, 02:포인트부족")
	public int respCD;
	
	@PathwayMessageField(length=1,  comment="필드구분자3")
	public byte[] fieldTag3;

	@PathwayMessageField(length=10,  pad="left", defaultFillValue=" ", comment="승인번호")
	public String authNo;

	@PathwayMessageField(length=1,  comment="필드구분자4")
	public byte[] fieldTag4;

	@PathwayMessageField(length=10,  pad="right", defaultFillValue="0", comment="거래고유번호-승인서버발행")
	public int trNo;
	
	@PathwayMessageField(length=1,  comment="필드구분자5")
	public byte[] fieldTag5;

	@PathwayMessageField(length=20,  pad="left", defaultFillValue=" ", comment="매입사명")
	String corpName;
}
