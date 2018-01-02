
public class Items implements Comparable<Items> {

	public int frequency;
	public int docId;
	public String term;
	
	public Items(String term, int frecuency, int docId){
		this.frequency = frecuency;
		this.term = term;
		this.docId = docId;
	}

	
	public int compareTo(Items v) {
		int termComp = term.compareTo(v.term);
		if( termComp == 0){
			if(docId <= v.docId){
				return 1;
			} else {
				return -1;
			}
		}
		else if(termComp < 0){
			return -1;
		} 
		else{
			return 0;
		}
		 
	}

	public String toString(){
		return term + " " + frequency + " " + docId;		
	}
}
