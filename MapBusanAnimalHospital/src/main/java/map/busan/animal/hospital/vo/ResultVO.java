package map.busan.animal.hospital.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultVO {

	private GetTblAnimalHospital getTblAnimalHospital;
	
	@Getter
	@Setter
	public class GetTblAnimalHospital{
		private Header header;
		private Body body;
		
		@Getter
		@Setter
		public class Header {
			private String resultCode;
			private String resultMsg;
		}
		
		@Getter
		@Setter
		public class Body {
			private Items items;
			private int numOfRows;
			private int pageNo;
			private int totalCount;
			
			@Getter
			@Setter
			public class Items {
				private List<ItemVO> item;
			}
		}
	}
	
}