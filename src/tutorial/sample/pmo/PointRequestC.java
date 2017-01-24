package tutorial.sample.pmo;

import com.pathway.core.pmo.PathwayEmbeddedMessage;
import com.pathway.core.pmo.PathwayMessageField;
import com.pathway.core.pmo.PathwayMessageHandler;
import com.pathway.core.pmo.TopMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Point PointRequestC")
public class PointRequestC extends TopMessage {
	
	@PathwayEmbeddedMessage
	public PointHeader header = new PointHeader();
	
	@PathwayMessageField(length=1,  defaultFillValue="0x1c", comment="�ʵ屸����1")
	public byte[] fieldTag1;

	@PathwayMessageField(length=10,  pad="left", defaultFillValue="0", comment="�ܸ��������ȣ")
	public int termNumber;
	
    public PointRequestC() {
      this.setAcceptableMsg(new String[] { "P003", "P004" });
    }
    

}
