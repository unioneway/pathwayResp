package tutorial.sample.pmo;

import com.pathway.core.pmo.PathwayEmbeddedMessage;
import com.pathway.core.pmo.PathwayMessageField;
import com.pathway.core.pmo.TopMessage;

public class PointResponse extends TopMessage {
	
	@PathwayEmbeddedMessage
	public PointHeader header = new PointHeader();
	
	@PathwayMessageField(length=1,  comment="�ʵ屸����1")
	public byte[] fieldTag1;

	@PathwayMessageField(length=10,  pad="left", defaultFillValue=" ", comment="�ܸ��������ȣ")
	public int termNumber;
	
	@PathwayMessageField(length=1,  comment="�ʵ屸����2")
	public byte[] fieldTag2;
	
	@PathwayMessageField(length=2,  pad="left", defaultFillValue=" ", comment="�����ڵ�-> 00: ����, 01:����, 02:����Ʈ����")
	public int respCD;
	
	@PathwayMessageField(length=1,  comment="�ʵ屸����3")
	public byte[] fieldTag3;

	@PathwayMessageField(length=10,  pad="left", defaultFillValue=" ", comment="���ι�ȣ")
	public String authNo;

	@PathwayMessageField(length=1,  comment="�ʵ屸����4")
	public byte[] fieldTag4;

	@PathwayMessageField(length=10,  pad="right", defaultFillValue="0", comment="�ŷ�������ȣ-���μ�������")
	public int trNo;
	
	@PathwayMessageField(length=1,  comment="�ʵ屸����5")
	public byte[] fieldTag5;

	@PathwayMessageField(length=20,  pad="left", defaultFillValue=" ", comment="���Ի��")
	String corpName;
}
